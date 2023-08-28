import axios from "axios";

export const sendSignUpQuery = async (
    ident,
    password,
    username
) => {
    const user = {
        id: null,
        username: username,
        ident: ident,
        password: password,
        keyVal: null
    };

    const res = await axios.post("http://localhost:8080/members/signUp", user);

    if(res.data.ok){
        //* Sign Up Successful!
        alert(`Welcome, ${res.data?.data.username}`);
        return true
    }else{
        alert(`${res.data?.message}`);
    }
}