import Message from "@/schemas/message.schema";
import { Server } from "socket.io";
export default function handler(req, res) {
  let io = res.socket.server.io;
  if (!io) {
    const io = new Server(res.socket.server);
    io.on("connection", (socket) => {
      socket.on("sendMessage", async(msg,receiverId) => {
        console.log("message: " + msg);
        socket.to(receiverId).emit("newMessage",msg)
        const message = new Message(msg)
        await message.save()
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
