import axios from "axios";
import  {useContext} from 'react'
import AuthContext from '../ContextData/AuthContext.ts';


    const baseUsr:string = 'http://localhost:8082';
    export const  getUserLogin = (endpoint:string, reqData:any)=>{
        //  reqData = {
        //     a: 10,
        //     b: 20,
        //   };
         const headerData = { headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
            "Access-Control-Allow-Headers":"Content-Type, Authorization, X-Requested-With",
            "Access-Control-Expose-Headers":"*",
            
          },}
       return  axios.post(baseUsr+endpoint, reqData, headerData);
    }

    function useGetUserList(){
      const {isLoggedIn} = useContext(AuthContext);
    
      // axios.interceptors.request.use(request => {
      //   console.log('Generated Request Headers:', request.headers);
      //   return request;
      // }, error => {
      //   return Promise.reject(error);
      // });
      
      const  createUserApi = (endpoint:string, reqData:any)=>{
        //  reqData = {
        //     a: 10,
        //     b: 20,
        //   };
         const headerData = { headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods":"POST, PUT, PATCH, GET, DELETE, OPTIONS",
          "Access-Control-Allow-Headers":"Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization, Access-Token, Uid",
          "Access-Control-Expose-Headers":"*",
          "withCredentials":true,
          "Authorization":"Bearer "+isLoggedIn?.token
            
          },}
       return  axios.post(baseUsr+endpoint, reqData, headerData);
    }




     const  getUserList = (endpoint:string)=>{

    
      //  reqData = {
      //     a: 10,
      //     b: 20,
      //   };
      const headerUData = { headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods":"POST, PUT, PATCH, GET, DELETE, OPTIONS",
            "Access-Control-Allow-Headers":"Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization, Access-Token, Uid",
            "Access-Control-Expose-Headers":"*",
            "withCredentials":true,
            "Authorization":"Bearer "+isLoggedIn?.token
          },}
       const headerData = { headers: {
          "Authorization":"Bearer "+isLoggedIn?.token
        },}
     return  axios.get(baseUsr+endpoint, headerUData);
    
  }
  return {getUserList, createUserApi}
    }
  
export default useGetUserList;

    // export const  useGetUserList = (endpoint:string)=>{

    //   const {token} = useContext(AuthContext);
    //   //  reqData = {
    //   //     a: 10,
    //   //     b: 20,
    //   //   };
    //    const headerData = { headers: {
    //       Accept: "application/json",
    //       "Content-Type": "application/json",
    //       "Access-Control-Allow-Origin": "*",
    //       "Access-Control-Allow-Methods":"DELETE, POST, GET, OPTIONS",
    //       "Access-Control-Allow-Headers":"Content-Type, Authorization, X-Requested-With",
    //       "Access-Control-Expose-Headers":"*",
    //       "Authorization":"Bearer "+token
    //     },}
    //  return  axios.post(baseUsr+endpoint, headerData);
    //   }
    export const getCheck  = () => {
      return "chcek"
    }
   