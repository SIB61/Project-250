import handleRequest from "@/lib/req-handler";
import withAuth from "@/middleware/auth.middleware";
import Connection from "@/schemas/connection.schema";
import User from "@/schemas/user.schema";
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
      res.json({status:connection.status,requestedBy:connection.userOne})
    },

    async post() {
      const { id } = req.user;
      const { withUserId } = req.query;
      const { status } = req.body;
      let connection = await Connection.findOne({
        userOne: { $in: [id, withUserId] },
        userTwo: { $in: [id, withUserId] },
      });
      if (connection) {
       await connection.updateOne({ status: status });
      } else {
        connection = new Connection({
          userOne: id,
          userTwo: withUserId,
          status: status,
        });
        await connection.save();
      }
      res.json(connection.status);
    },
  });
}

async function getUserConnections(id) {
  const connections = await Connection.find({
    $or: [{ userOne: id }, { userTwo: id }],
    status: "connected",
  });
  const connectedUserIds = connections.map((conn) => getSecondUserId(conn, id));
  const connectedUsers = await User.find({
    _id: {$in:connectedUserIds},
  });
  return connectedUsers.map(con=>({id:con._id,email:con.email,userName:con.userName,name:con.name}));
}

function getSecondUserId(connection, id) {
  return connection.userOne === id ? connection.userTwo : connection.userOne;
}
