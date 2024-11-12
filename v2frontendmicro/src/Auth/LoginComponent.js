import { useEffect, useState, useContext} from "react";
import  {getUserLogin} from '../service/AuthService.ts'
import AuthContext, {IAuthRes } from '../ContextData/AuthContext.ts';
function LoginComponent(){
    
    const cdd = useContext(AuthContext);
    const {isLoggedIn, getLoggInDetails} = useContext(AuthContext);
    useEffect(()=>{
       // getUserList('/checkapi', {data:{first:'one'}})
       let reqData =  {"email":"admin@test.com", "password":"12345678"}
       
       const getRes =  getUserLogin('/users-ws/users/login', reqData)
       .then((data)=>{
        console.log(data.headers['access-token'])
        console.log(data.headers['uid'])
        const getCrediential = {uid:data.headers['uid'], token:data.headers['access-token'], isLoggedIn:true}
        getLoggInDetails(getCrediential)
           
        }).catch(err=>{
            console.log(err)
        })
       console.log(getRes)
    }, [])
    console.log(cdd,'cdd')
    return isLoggedIn ? 'logged' : 'not logged in';
}
export default LoginComponent;