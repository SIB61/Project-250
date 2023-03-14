import { createJwt } from "@/lib/jwt";
import handleRequest from "@/lib/req-handler";
import User from "@/schemas/user.schema";
import { hash } from "bcryptjs";
export default async function handler(req, res) {
  await handleRequest({
    req: req,
    res: res,
    async post() {
      const { email, userName, password, name, publicKey } = req.body;
      console.log(req.body, req.body.password);
      const existingUser =
        (await User.findOne({ email })) || (await User.findOne({ userName }));
      if (existingUser) {
        res.status(400).send("user exists");
      }
      const passwordHash = await hash(password, 8);
      const user = new User({
        name: name,
        email: email,
        userName: userName,
        passwordHash,
        publicKey: publicKey,
      });
      try {
        const savedUser = await user.save();
        const accessToken = createJwt({ id: savedUser._id, userName });
        res.json({ id: savedUser._id, accessToken, ...savedUser._doc });
      } catch (err) {
        res.status(500).send();
      }
    },
  });
}
