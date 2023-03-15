import mongoose, { mongo } from "mongoose"
const connectionSchema =new mongoose.Schema({
    userOne:{
       type:String,
       required:true
    },
    userTwo:{
        type:String,
        required:true
    },
    status:String
})

const Connection = mongoose.models.Connection || mongoose.model("Connection",connectionSchema)
export default Connection
