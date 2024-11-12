import  {useContext, useEffect, useState} from 'react'
import  useGetUserList from '../service/AuthService.ts'
import AuthContext from '../ContextData/AuthContext.ts';
const UserDetailComponent = () => {
   const {isLoggedIn, token} = useContext(AuthContext)
  const {getUserList, createUserApi} = useGetUserList();
  const [userList, setUserList] = useState();
  const [userCreate, setUserCreate] = useState();

  const getAllUser = () => {
    getUserList('/users-ws/users/getAll').
    then((res)=>{console.log(res);
      setUserList(res.data)
    }).catch(err=>{
   console.log(err)
   
})
  }
    useEffect(()=>{
        console.log(isLoggedIn?.token)
      
        if(isLoggedIn?.token)
            {
               
                getAllUser() 

         getUserList('/role-ws/apis/rolebyuserid/c1d6d047-09f9-4a52-af10-0e5a0c1dcfa3').
         then((res)=>{console.log("ROle by user");
             console.log(res);
            
             console.log("ROle by user End")
            
            }).catch(err=>{
        console.log(err)
        
    })
         
        }
        //console.log(dc)
       
     }, [isLoggedIn])


    const renderTitle = () => {
        return <h4>User Manament</h4>
    }

    const useerlist = () => userList && userList.map((uDt, index)=>
    <div key={index} className='userdtls'>
        <div className='fl'>FirstName</div>
    <div className='fr b'>{uDt.firstName}</div>
    <div className='fl'>LastName</div><div className='fr b'>{uDt.lastName}</div>
    <div className='fl'>Email</div><div className='fr b'>{uDt.email}</div>
   <div className="clr"></div>
    </div>) 
     
     const createUser = () => {
        return <>
         <div className='userdtls'>
        <div className='fl'>FirstName</div>
    <div className='fr b'><input type="text" name="fstnm" value={userCreate?.firstName ? userCreate.firstName: '' } onChange={(event)=>setUserCreate({...userCreate, firstName: event.target.value})}  /></div>
    <div className='fl'>LastName</div><div className='fr b'><input type="text" name="lstnm" value={userCreate?.lastName ? userCreate.lastName: '' } onChange={(event)=>setUserCreate({...userCreate, lastName: event.target.value})} /></div>
    <div className='fl'>Email</div><div className='fr b'><input type="text" name="eml" onChange={(event)=>setUserCreate({...userCreate, email: event.target.value})} /></div>
    <div className='fl'>Password</div><div className='fr b'><input type="text" name="psw" onChange={(event)=>setUserCreate({...userCreate, password: event.target.value})} /></div>
   <div className="clr"></div>
    </div></>
     }

    
     const callCreateApi = ()=>{
        setUserList([])
        createUserApi('/users-ws/users', userCreate).
        then((res)=>{
            setTimeout(()=>{ getAllUser()}, 1000)
           
        }).catch(err=>{
            console.error(err)
          
            if(err.response.status == 500){
                alert("email already exists")
            }
            setTimeout(()=>{ getAllUser()}, 1000)
       console.log(err)
       
   })
     }
     

    const saveUser = () => {
        if(userCreate.firstName && userCreate.lastName && userCreate.email ){
            callCreateApi();
        }else{
            alert('Fields are missing')
        }
    }
    const addUser = () => {
        return <button onClick={()=>saveUser()}>Add Button </button>
    }


console.log(userCreate, 'userList')
    return <>
    {isLoggedIn + " ==> login checking"}
    {createUser()}
    {renderTitle()}
    {addUser()}
    {useerlist()}</>
    
}
export default UserDetailComponent;