import { varifyJwt } from "@/lib/jwt";

export default function withAuth(handler){
  return (req,res)=>{
    try{
    const token = req.headers.authorization.split(' ')[1];
    const user = varifyJwt(token)
    if(!user) res.status(401).send()
    req.user = user
    return handler(req,res)
    }
    catch(err){
    return res.status(500).send()
    }
  } 
}
