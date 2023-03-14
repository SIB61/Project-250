const http = require('http');
const io = require('socket.io')(http);

io.on('connection', (socket) => {
  console.log('User connected');

  socket.on('message', (message) => {
    console.log('Received message:', message);
    // Decrypt the message here
    io.emit('message', message);
  });

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

http.listen(3000, () => {
  console.log('Server listening on port 3000');
});
