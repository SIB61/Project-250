import {io} from 'socket.io-client'
let socket
export const getSocket= () => {
  if(!socket){
    socket = io()
    console.log("creating socket")
    console.log(socket.id)
  }
  return socket;
}







