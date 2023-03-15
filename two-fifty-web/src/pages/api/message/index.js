import withAuth from "@/middleware/auth.middleware";
import Connection from "@/schemas/connection.schema";
import Message from "@/schemas/message.schema";
import { Server } from "socket.io";
export default withAuth(handler);
async function handler(req, res) {
  const { withUserId } = req.query;

  const { id } = req.user;

  console.log(withUserId, id);

  const connection = await Connection.findOne({
    userOne: { $in: [id, withUserId] },
    userTwo: { $in: [id, withUserId] },
  });

  if (!connection) {
    console.log("dskl");
    res.status(404).send("connection not found.");
    return;
  }

  createSocketConnection(res);

  res.json({ status: "connected" });
}

function createSocketConnection(res) {
  let io = res.socket.server.io;
  console.log("creating");
  console.log(io);
  if (!io) {
    console.log("not io");
    const io = new Server(res.socket.server);
    io.on("connection", (socket) => {

      const customId = socket.handshake.query.custom_id;
      console.log(`New connection with custom id ${customId}`);
      socket.customId = customId;

      socket.on("sendMessage", async (senderId,receiverId,encryptedMsg,forUser) => {
        console.log("message: " + encryptedMsg);
        socket.emit("newMessage", senderId,receiverId,encryptedMsg,forUser);
        const message = new Message({senderId:senderId,receiverId:receiverId,forUser:forUser,encryptedMsg:encryptedMsg});
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
