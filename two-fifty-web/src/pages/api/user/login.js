import dbConnect from "@/lib/mongo-connect"
import handleRequest from "@/lib/req-handler"
import User from "@/schemas/user.schema"
export default async function handler(req, res) {
  await handleRequest({
    req:req,
    res:res,
    async post(){
       const {email,password} = req.body
       const user = new User({name:name,email:email,userName:userName,passwordHash:password,publicKey:"dslk"})  
       const savedUser = await user.save()
       res.json(savedUser)
    }
  })
}