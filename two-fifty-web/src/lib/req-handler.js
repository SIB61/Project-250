const  handleRequest= async({req,res,get,post,remove,update})=>{
  let method;
  await dbConnect()
  switch(req.method){
    case 'GET':
    method = get
    break;
    case 'POST':
    method = post
    break;
    case 'DELETE':
    method = remove
    break;
    case 'PATCH':
    method = update
    break;
  } 
  if(method)
  method(req,res)
}

export default handleRequest
