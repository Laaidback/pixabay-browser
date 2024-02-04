package com.example.imagedatasource.data.repository

import com.example.imagedatasource.data.db.ImageDao
import com.example.imagedatasource.data.db.ImageEntity
import com.example.imagedatasource.data.db.SearchResultEntity
import com.example.imagedatasource.data.mapper.ImageMapper
import com.example.imagedatasource.data.remote.model.ImageDto
import com.example.imagedatasource.data.remote.model.SearchImageResponse
import com.example.imagedatasource.data.remote.service.ImageService
import com.example.imagedatasource.domain.model.Image
import com.example.imagedatasource.domain.repository.ImageRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.URLEncoder
import kotlin.test.assertFailsWith

class ImageRepositoryImplTest {

    @MockK
    private lateinit var imageService: ImageService

    @MockK
    private lateinit var imageDao: ImageDao

    @MockK
    private lateinit var imageMapper: ImageMapper

    private lateinit var testedUnit: ImageRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        testedUnit = ImageRepositoryImpl(
            imageService = imageService,
            imageDao = imageDao,
            imageMapper = imageMapper,
        )
    }

    @Test
    fun `should return data fetched from service and save in dao`() = runBlocking {
        // Given
        val query = "query"
        val encodedQuery = "encodedQuery"
        val mockImageDto1 = mockk<ImageDto>()
        val mockImageDto2 = mockk<ImageDto>()
        val mockImage1 = mockk<Image>()
        val mockImage2 = mockk<Image>()
        val mockImageEntity1 = mockk<ImageEntity>()
        val mockImageEntity2 = mockk<ImageEntity>()
        val mockImageEntities = listOf(mockImageEntity1, mockImageEntity2)
        val mockSearchImageResponse = mockk<SearchImageResponse>().apply {
            every { images } returns listOf(mockImageDto1, mockImageDto2)
        }
        val mockSearchResultEntity = mockk<SearchResultEntity>()

        mockkStatic(URLEncoder::class)
        every { URLEncoder.encode(query, "utf-8") } returns encodedQuery
        coEvery { imageService.getImages(query = encodedQuery) } returns mockSearchImageResponse
        every { imageMapper.mapDtoToDomain(mockImageDto1) } returns mockImage1
        every { imageMapper.mapDtoToDomain(mockImageDto2) } returns mockImage2
        every { imageMapper.mapDomainToEntity(mockImage1) } returns mockImageEntity1
        every { imageMapper.mapDomainToEntity(mockImage2) } returns mockImageEntity2
        coEvery { imageDao.saveImages(mockImageEntities) } just runs
        every {
            imageMapper.mapToSearchResult(query, mockImageEntities)
        } returns mockSearchResultEntity
        coEvery { imageDao.saveSearchResult(mockSearchResultEntity) } just runs

        // When
        val result = testedUnit.getImagesForQuery(query)

        // Then
        assert(result.first() == listOf(mockImage1, mockImage2))
        coVerifyOrder {
            imageService.getImages(query = encodedQuery)
            imageMapper.mapDtoToDomain(mockImageDto1)
            imageMapper.mapDtoToDomain(mockImageDto2)
            imageMapper.mapDomainToEntity(mockImage1)
            imageMapper.mapDomainToEntity(mockImage2)
            imageDao.saveImages(mockImageEntities)
            imageMapper.mapToSearchResult(query, mockImageEntities)
            imageDao.saveSearchResult(mockSearchResultEntity)
        }
    }

    @Test
    fun `should fetch from db when service returns error`() = runBlocking {
        // Given
        val query = "query"
        val encodedQuery = "encodedQuery"
        val mockImage1 = mockk<Image>()
        val mockImage2 = mockk<Image>()
        val mockImageEntity1 = mockk<ImageEntity>()
        val mockImageEntity2 = mockk<ImageEntity>()

        val id1 = "id1"
        val id2 = "id2"
        val mockSearchResultEntity = SearchResultEntity(
            query = query,
            ids = listOf(id1, id2)
        )

        mockkStatic(URLEncoder::class)
        every { URLEncoder.encode(query, "utf-8") } returns encodedQuery
        coEvery { imageService.getImages(query = encodedQuery) } throws IOException("failed")
        coEvery { imageDao.getSearchResultsFromQuery(query = query) } returns mockSearchResultEntity
        coEvery { imageDao.getImageById(id1) } returns mockImageEntity1
        coEvery { imageDao.getImageById(id2) } returns mockImageEntity2
        every { imageMapper.mapEntityToDomain(mockImageEntity1) } returns mockImage1
        every { imageMapper.mapEntityToDomain(mockImageEntity2) } returns mockImage2


        // When
        val result = testedUnit.getImagesForQuery(query)

        // Then
        assert(result.first() == listOf(mockImage1, mockImage2))
        coVerifyOrder {
            imageService.getImages(query = encodedQuery)
            imageDao.getSearchResultsFromQuery(query = query)
            imageDao.getImageById(id1)
            imageDao.getImageById(id2)
            imageMapper.mapEntityToDomain(mockImageEntity1)
            imageMapper.mapEntityToDomain(mockImageEntity2)
        }
    }

    @Test
    fun `should throw error when service returns error and db returns no result`() = runBlocking {
        // Given
        val query = "query"
        val encodedQuery = "encodedQuery"

        mockkStatic(URLEncoder::class)
        every { URLEncoder.encode(query, "utf-8") } returns encodedQuery
        coEvery { imageService.getImages(query = encodedQuery) } throws IOException("failed")
        coEvery { imageDao.getSearchResultsFromQuery(query = query) } returns null

        // When
        val result = testedUnit.getImagesForQuery(query)

        // Then
        assertFailsWith<IOException>("failed") {
            result.collect {}
        }
        coVerifyOrder {
            imageService.getImages(query = encodedQuery)
            imageDao.getSearchResultsFromQuery(query = query)
        }
    }

    @After
    fun teardown() {
        unmockkStatic(URLEncoder::class)
        confirmVerified(
            imageService,
            imageDao,
            imageMapper,
        )
    }
}
