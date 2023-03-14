import { Server } from "socket.io";
export default function handler(req, res) {
  let io = res.socket.server.io;
  if (!io) {
    const io = new Server(res.socket.server);
    io.on("connection", (socket) => {
      socket.on("sendMessage", (msg) => {
        console.log("message: " + msg);
        socket.broadcast.emit("newMessage",msg)
      });
      socket.on("disconnect", () => {});
    });
    res.socket.server.io = io;
  }
  res.end();
}

export const config = {
  api: {
    bodyParser: false,
  },
};
