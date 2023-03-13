import mongoose, { mongo } from "mongoose"
const messageSchema =new mongoose.Schema({
    senderId:{
       type:ObjectId,
       required:true
    },
    receiverId:{
        type:ObjectId,
        required:true
    },
    encryptedMsg:{
        type:String,
        required:true
    },
    time:{
        type:Date,
        default:Date.now
    }
})

const Message = mongoose.models.Message || mongoose.model("Message",messageSchema)
export default Message