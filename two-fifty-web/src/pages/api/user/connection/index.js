import handleRequest from "@/lib/req-handler";
import withAuth from "@/middleware/auth.middleware";
const { default: Connection } = require("@/schemas/connection.schema");
const { default: User } = require("@/schemas/user.schema");
export default withAuth(handler);
async function handler(req, res) {
  handleRequest({
    req: req,
    res: res,
    async get() {
      const {withUserId} = req.query;
      const { id } = req.user;
      if(!withUserId){
      const myConnections = await getUserConnections(id);
      res.json(myConnections);
      return
      } 
      const connection = await Connection.findOne({
        userOne:{$in:[withUserId,id]},
        userTwo:{$in:[withUserId,id]}
      })
    },

    async post() {
      const { id } = req.user;
      const { withUserId } = req.query;
      const { status } = req.body;
      const connection = await Connection.findOne({
        userOne: { $in: [id, withUserId] },
        userTwo: { $in: [id, withUserId] },
      });
      if (connection) {
        connection.updateOne({ status: status });
      } else {
        connection = new Connection({
          userOne: id,
          userTwo: withUserId,
          status: status,
        });
        await connection.save();
      }
      res.json(connection);
    },
  });

  res.json({ connectedUsers });
}

async function getUserConnections(id) {
  const connections = await Connection.find({
    $or: [{ userOne: id }, { userTwo: id }],
    status: "connected",
  });
  const connectedUserIds = connections.map((conn) => getSecondUserId(conn, id));
  const connectedUsers = await User.find({
    _id: connectedUserIds,
  });
  return connectedUsers;
}

function getSecondUserId(connection, id) {
  return connection.userOne === id ? connection.userTwo : connection.userOne;
}
