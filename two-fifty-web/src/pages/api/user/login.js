import { compare } from "@/lib/hashing";
import { createJwt } from "@/lib/jwt";
import handleRequest from "@/lib/req-handler";
import { deleteMessagesForUser } from "@/lib/services/message.service";
import User from "@/schemas/user.schema";
export default async function handler(req, res) {
   await handleRequest({
    req: req,
    res: res,
    async post() {
      const { email, password, publicKey } = req.body;
      const user = await User.findOne({ email:email });
      if (!user){
      return res.status(404).send();
      } 
      if (!compare(password, user.passwordHash)) {
        res.status(400).send();
      }
      await deleteMessagesForUser(user._id)
      await user.updateOne({publicKey:publicKey})
      const accessToken = createJwt({ id: user._id, userName: user.userName });
      res.json({ name:user.name,userName:user.userName, email:user.email, accessToken, id:user._id });
    },
  });
}
