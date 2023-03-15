import Message from "@/schemas/message.schema";

export async function deleteMessagesForUser(userId){
  await Message.deleteMany({
    $or:[{senderId:userId},{receiverId:userId}],
    forUser:userId
  }) 
}
