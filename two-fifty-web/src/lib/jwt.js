import jwt from 'jsonwebtoken'
export function createJwt(prop){
   return jwt.sign(prop,process.env.JWT_SECRET) 
}

export function varifyJwt(token){
  return jwt.verify(token,process.env.JWT_SECRET)
}
