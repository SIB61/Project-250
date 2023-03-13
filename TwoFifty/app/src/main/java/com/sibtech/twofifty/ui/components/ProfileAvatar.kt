package com.sibtech.twofifty.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileAvatar(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(2.dp)
    )
}
