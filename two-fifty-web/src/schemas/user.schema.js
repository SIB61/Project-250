import mongoose, { mongo } from "mongoose"
const userSchema = new mongoose.Schema({
    email:{
        type:String,
        unique:true,
        required:true
    },
    userName:{
        type:String,
        unique:true,
        required:true
    },
    name:{
        type:String,
        required:true
    },
    passwordHash:{
        type:String,
        required:true
    },
    publicKey:{
        type:String,
        required:true
    }
})
const User = mongoose.models.User || mongoose.model("User",userSchema)
export default User