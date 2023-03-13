package com.sibtech.twofifty

import com.sibtech.twofifty.models.ChatMessage
import com.sibtech.twofifty.models.User

val chatMessages = listOf(
    ChatMessage(
        sender = "John Doe",
        message = "Hello, how are you?",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
    ),
    ChatMessage(
        sender = "Jane Smith",
        message = "I'm good, thanks! How about you?",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/women/2.jpg"
    ),
    ChatMessage(
        sender = "John Doe",
        message = "I'm doing well, thanks!",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
    ),
    ChatMessage(
        sender = "Jane Smith",
        message = "That's great to hear!",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/women/2.jpg"
    ),
    ChatMessage(
        sender = "John Doe",
        message = "How's work been going for you?",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
    ),
    ChatMessage(
        sender = "Jane Smith",
        message = "It's been busy, but I'm managing.",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/women/2.jpg"
    ),
    ChatMessage(
        sender = "John Doe",
        message = "Glad to hear it.",
        timestamp = System.currentTimeMillis(),
        senderImageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
    )
)
val userList = listOf(
    User(
        id = 1,
        name = "Alice",
        email = "alice@example.com",
        profileImageUrl = "https://i.imgur.com/ztvTVI8.jpg"
    ),
    User(
        id = 2,
        name = "Bob",
        email = "bob@example.com",
        profileImageUrl = "https://i.imgur.com/YjUMR65.jpg"
    ),
    User(
        id = 3,
        name = "Charlie",
        email = "charlie@example.com",
        profileImageUrl = "https://i.imgur.com/6zKf0pB.jpg"
    ),
    User(
        id = 4,
        name = "Dave",
        email = "dave@example.com",
        profileImageUrl = "https://i.imgur.com/e7KjJVC.jpg"
    ),
    User(
        id = 5,
        name = "Eve",
        email = "eve@example.com",
        profileImageUrl = "https://i.imgur.com/yS9Kd7n.jpg"
    )
)