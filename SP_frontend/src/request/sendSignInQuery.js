import axios from "axios";


export const sendSignInQuery = async (
    ident,
    password
) => {
    const signIn = {
        id:ident,
        pw:password
    }

    const res = await axios.post("http://localhost:8080/members/signIn", signIn)

    if(res.data.ok){
        //* Sign In Successful!
        alert(`Welcome Back, ${res.data?.data?.username}`)
        return {
            id: res.data?.data?.id,
            name: res.data?.data?.username
        };
    }else{
        alert(`Login Failed! ${res.data?.message}`)
        return null
    }
}