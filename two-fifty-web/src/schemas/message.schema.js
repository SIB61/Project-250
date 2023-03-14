import mongoose, { mongo } from "mongoose"
const messageSchema =new mongoose.Schema({
    senderId:{
       type:String,
       required:true
    },
    receiverId:{
        type:String,
        required:true
    },
    encryptedMsg:{
        type:String,
        required:true
    },
    type:String,
    time:{
        type:Date,
        default:Date.now
    }
})

const Message = mongoose.models.Message || mongoose.model("Message",messageSchema)
export default Message
