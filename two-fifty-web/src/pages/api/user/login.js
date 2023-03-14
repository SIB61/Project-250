import { compare } from "@/lib/hashing";
import { createJwt } from "@/lib/jwt";
import dbConnect from "@/lib/mongo-connect";
import handleRequest from "@/lib/req-handler";
import User from "@/schemas/user.schema";
export default async function handler(req, res) {
   await handleRequest({
    req: req,
    res: res,
    async post() {
      const { email, password } = req.body;
      const user = await User.findOne({ email });
      if (!user) res.status(404).send();
      if (!compare(password, user.passwordHash)) {
        res.status(400).send();
      }
      const accessToken = createJwt({ id: user._id, userName: user.userName });
      res.json({ ...user, accessToken });
    },
  });
}
