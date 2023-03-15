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
    forUser:String,
})

const Message = mongoose.models.Message || mongoose.model("Message",messageSchema)
export default Message
