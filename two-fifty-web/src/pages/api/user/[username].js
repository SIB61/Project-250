import handleRequest from "@/lib/req-handler";
import User from "@/schemas/user.schema";
export default async function handler(req,res){
   handleRequest({
    req,
    res,
    async get(){
       const {username} = req.query
       console.log(username)
       const user =await User.findOne({userName:username})
       if(user)
       res.json({user})
       else
       res.status(404).send("user not found")
    }
  })
}
