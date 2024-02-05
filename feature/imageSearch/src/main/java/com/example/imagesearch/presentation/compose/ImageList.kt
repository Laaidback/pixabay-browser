package com.example.imagesearch.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.core.presentation.compose.TagList
import com.example.imagesearch.R
import com.example.imagesearch.presentation.ImageSearchResultModel

@Composable
fun ImageList(items: List<ImageSearchResultModel>) {
    LazyColumn {
        items(
            items = items,
            key = { item -> item.id }
        ) { item ->
            ImageRow(item)
        }
    }
}

@Composable
private fun ImageRow(
    item: ImageSearchResultModel
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(size = 16.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 16.dp),
            )
            .clickable { item.onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            painter = rememberAsyncImagePainter(model = item.imageUrl),
            contentDescription = stringResource(id = R.string.image_content_description)
        )
        Column(
            modifier = Modifier
                .padding(all = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = "User: ${item.userName}"
            )
            TagList(tags = item.tags)
        }
    }
}
