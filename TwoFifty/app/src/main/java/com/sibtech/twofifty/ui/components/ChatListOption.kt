package com.sibtech.twofifty.ui.components

import android.text.format.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sibtech.twofifty.models.ChatMessage
import java.sql.Date
import java.text.DateFormat.getTimeInstance

    @Composable
    fun ChatMessageItem(message: ChatMessage) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
                .padding(16.dp)
        ) {
            ProfileAvatar(
                imageUrl = message.senderImageUrl,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = message.sender,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = getTimeInstance().format(Date(message.timestamp)),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
