import { FormInput } from "@/components/Input";
import myAxios from "@/lib/Axios";
import axios from "axios";
import Link from "next/link";
import { useState } from "react";

export default function login() {
  const [formState, setFormState] = useState({});
  async function onSubmit(){
    const user = await axios.post("/api/user/login",formState)
    console.log(user)
  }
  return (
    <div className="w-screen h-screen flex justify-center items-center">
      <form className="p-4 glass rounded-xl flex gap-4 flex-col justify-center items-center w-max m-auto">
        <FormInput
          type="text"
          name="email"
          setFormState={setFormState}
          placeholder="enter your email"
          className="input input-bordered input-info w-full max-w-xs"
        />
        <FormInput
          type="text"
          name="password"
          setFormState={setFormState}
          placeholder="enter your password"
          className="input input-bordered input-info w-full max-w-xs"
        />
        <button
          className="btn w-full"
          onClick={() => {
            onSubmit()
          }}
        >
          Button
        </button>
       <Link href="/register" className="btn btn-link">register</Link> 
      </form>
    </div>
  );
}
