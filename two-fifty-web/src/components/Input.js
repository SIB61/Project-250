export function FormInput({name,type,className,placeholder,setFormState}){
  return (<input className={className} type={type} name={name} placeholder={placeholder} onChange={(e)=>{
     setFormState(state=>({...state,[name]:e.target.value}))
  }}/>)
}
