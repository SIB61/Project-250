import withAuth from "@/middleware/auth.middleware";
import Connection from "@/schemas/connection.schema";
import Message from "@/schemas/message.schema";
import { Server } from "socket.io";
export default withAuth(handler);
async function handler(req, res) {
  const { withUserId } = req.query;

  const { id } = req.user;

  const connection = await Connection.findOne({
    userOne: { $in: [id, withUserId] },
    userTwo: { $in: [id, withUserId] },
  });

  if (!connection) {
    res.status(404).send("connection not found.");
    return;
  }

  createSocketConnection(res);

  res.end();
}

function createSocketConnection(res) {
  let io = res.socket.server.io;
  if (!io) {
    const io = new Server(res.socket.server);
    io.on("connection", (socket) => {
      socket.on("sendMessage", async (msg, receiverId) => {
        console.log("message: " + msg);
        socket.to(receiverId).emit("newMessage", msg);
        const message = new Message(msg);
        await message.save();
      });
      socket.on("disconnect", () => {
        res.socket.server.io = undefined;
      });
    });
    res.socket.server.io = io;
  }
}

export const config = {
  api: {
    bodyParser: false,
  },
};
