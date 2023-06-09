import handleRequest from "@/lib/req-handler";
import withAuth from "@/middleware/auth.middleware";
import Connection from "@/schemas/connection.schema";
import User from "@/schemas/user.schema";
export default withAuth(handler)
async function handler(req, res) {
  handleRequest({
    req,
    res,
    async get() {
      const { username } = req.query;
      const { id } = req.user;
      const user = await User.findOne({ userName: username });
      if (user) {
        let connection = await Connection.findOne({
          userOne: { $in: [user._id, id] },
          userTwo: { $in: [user._id, id] },
        });
        if(!connection) connection = {status:'disconnected'}
        else if(connection.userTwo == id) connection.status = 'requested'
        console.log(connection.status)
        console.log(connection)

        res.json({id:user._id, name:user.name, email:user.email, userName: user.userName, connectionStatus:connection.status});
      } else res.status(404).send("user not found");
    },
  });
}


