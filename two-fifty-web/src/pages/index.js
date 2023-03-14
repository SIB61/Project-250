import Head from "next/head";
import { Inter } from "next/font/google";
import { useEffect, useMemo, useState } from "react";
import { getSocket } from "@/lib/socket";
const inter = Inter({ subsets: ["latin"] });
export default function Home() {
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState([]);
  const [receiverid,setReceiverId] = useState([])
  const socket = useMemo(() => getSocket(), []);
  useEffect(() => {
    socket.on("connect", () => {
      console.log("connected", socket.id);
    });

    socket.on("newMessage", (msg) => {
      console.log(msg);
      setMessages((state) => [...state, msg]);
    });
    return () => {
      socket.off("newMessage");
    };
  }, [socket]);

  return (
    <>
      <Head>
        {" "}
        <title>Create Next App</title>
        <meta name="description" content="Generated by create next app" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <main>
        <div>
          {messages.map((m, i) => (
            <div key={i}>{m}</div>
          ))}
          <input placeholder="id" onChange={e=>{setReceiverId(e.target.value)}}/>
          <input
            value={message}
            placeholder="msg"
            onChange={(e) => {
              setMessage(e.target.value);
            }}
          />
          <button
            onClick={() => {
              socket.emit("sendMessage", message);
              setMessages(state=>[...state,message]) 
            }}
          >
            send
          </button>
        </div>
      </main>
    </>
  );
}
