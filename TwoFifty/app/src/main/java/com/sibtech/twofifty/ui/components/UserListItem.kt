package com.sibtech.twofifty.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sibtech.twofifty.models.User
import java.sql.Date
import java.text.DateFormat

@Composable
fun UserListItem(
    user: User
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(16.dp)
    ) {
        ProfileAvatar(
            imageUrl = user.profileImageUrl,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}