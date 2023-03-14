
export function Layout({children,router}){
  if(localStorage.getItem('token'))
  router.push("/login")
  return <div>
   {children} 
  </div>
}
