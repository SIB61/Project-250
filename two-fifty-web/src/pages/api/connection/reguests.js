import withAuth from "@/middleware/auth.middleware";
import Connection from "@/schemas/connection.schema";
import User from "@/schemas/user.schema";

export default withAuth(getRequests);
async function getRequests(req, res) {
  const { id } = req.user;
  try {
    const requests = await Connection.find({
      userTwo: id,
      status: "pending",
    });
    const requestIds = requests.map((r) => r.userOne);
    const requestUsers = User.find({
      _id: { $in: requestIds },
    });
    res.json(
      requestUsers.map((request) => ({
        id: request._id,
        publicKey: request.publicKey,
        userName: request.userName,
        email: request.email,
        name:request.name
      }))
    );
  } catch (err) {
    res.status(500).json(err.message);
  }
}
