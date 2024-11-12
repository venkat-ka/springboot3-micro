import { useState } from 'react';
import UserDetailComponent from './UserDetailComponent.jsx';
import LoginComponent from '../../src/Auth/LoginComponent.js';
import AuthContext  from '../ContextData/AuthContext.ts';
function UserManagment(){
    const [isLoggedIn, setIsloggedIn] = useState();

    const getLoggInDetails = (credDtls)=>{
        setIsloggedIn({uid:credDtls.uid, token:credDtls.token, isLoggedIn:true})
    }
    
    return <>
    <AuthContext.Provider value={{isLoggedIn, getLoggInDetails}} >
    <LoginComponent  />
    <UserDetailComponent />
   </AuthContext.Provider> </>
   
}
export default UserManagment;