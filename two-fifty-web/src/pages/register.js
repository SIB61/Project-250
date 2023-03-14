import { FormInput } from "@/components/Input";
import Link from "next/link";
import { useState } from "react";

export default function register() {
  const [formState, setFormState] = useState({});
  return (
    <div className="w-screen h-screen flex justify-center items-center">
      <form className="p-4 glass rounded-xl flex gap-4 flex-col justify-center items-center w-max m-auto">

        <FormInput
          type="text"
          name="userName"
          setFormState={setFormState}
          placeholder="enter your email"
          className="input input-bordered input-info w-full max-w-xs"
        />
        <FormInput
          type="text"
          name="name"
          setFormState={setFormState}
          placeholder="enter your password"
          className="input input-bordered input-info w-full max-w-xs"
        />

        <FormInput
          type="text"
          name="email"
          setFormState={setFormState}
          placeholder="enter your password"
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
          onClick={() => {}}
        >
          Button
        </button>
       <Link href="/login" className="btn btn-link">login</Link> 
      </form>
    </div>
  );
}
