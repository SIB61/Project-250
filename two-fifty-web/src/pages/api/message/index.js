import withAuth from "@/middleware/auth.middleware";
import Connection from "@/schemas/connection.schema";
import Message from "@/schemas/message.schema";
import { Server } from "socket.io";
export default withAuth(handler);
async function handler(req, res) {
  const { withUserId } = req.query;

  const { id } = req.user;

  console.log(withUserId, id)

  const connection = await Connection.findOne({
    userOne: { $in: [id, withUserId] },
    userTwo: { $in: [id, withUserId] },
  });

  if (!connection) {
    console.log("dskl")
    res.status(404).send("connection not found.");
    return;
  }

  createSocketConnection(res);

  res.json({status:"connected"});
}

function createSocketConnection(res) {
  let io = res.socket.server.io;
  console.log("creating")
  console.log(io)
  if (!io) {
    console.log("not io")
    const io = new Server(res.socket.server);
    io.on("connection", (socket) => {
      console.log("connected")
      socket.on("sendMessage", async (msg) => {
        console.log("message: " + msg);
        socket.broadcast.emit("newMessage", msg);
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
