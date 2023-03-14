import bcrypt from 'bcryptjs'
export async function encrypt(text){
  return await bcrypt.hash(text,"jksdfl")
}

export async function compare(text,hash){
  return await bcrypt.compare(text,hash)
}
