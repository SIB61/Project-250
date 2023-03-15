import handleRequest from "@/lib/req-handler";

const { default: withAuth } = require("@/middleware/auth.middleware");

export default withAuth(handler)
async function handler(req,res){
  handleRequest({
    req:req,
    res:res,
    async get(){

    }
  }) 
}
