import handleRequest from "@/lib/req-handler";
import withAuth from "@/middleware/auth.middleware";
import Message from "@/schemas/message.schema";
export default withAuth(handler)
function handler(req,res){
  handleRequest({
    req:req,
    res:res,
    async get(){
      const {secondUserId} = req.query
      const {id} = req.user
      const messages = await Message.find({
        senderId:{$in:[id,secondUserId]},
        receiverId:{$in:[id,secondUserId]}
      })
      res.json(messages)
    }
  }) 
}
