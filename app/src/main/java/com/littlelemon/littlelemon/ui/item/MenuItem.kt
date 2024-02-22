package com.littlelemon.littlelemon.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.littlelemon.data.MenuItemRoom
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor
import com.littlelemon.littlelemon.ui.theme.LittleLemonType

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth(.75F),) {
            Text(
                text = menuItem.title,
                style = LittleLemonType.cardTitle,
                color = LittleLemonColor.black,
            )
            Text(
                text = menuItem.description,
                style = LittleLemonType.paragraphText,
                color = LittleLemonColor.black,
            )
            Text(
                text = "$%.2f".format(menuItem.price),
                style = LittleLemonType.highlightText,
                color = LittleLemonColor.black,

                )
        }
        GlideImage(
            model = menuItem.image,
            contentDescription = "${menuItem.title} image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth)
    }
    Divider(
        thickness = 1.dp,
        color = LittleLemonColor.black)
}
