import jwt from 'jsonwebtoken'
export function createJwt(prop){
   return jwt.sign(prop,process.env.JWT_SECRET,{expiresIn:1000}) 
}

export function varifyJwt(token){
  return jwt.verify(token,process.env.JWT_SECRET)
}
