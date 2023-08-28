import React, {useState} from 'react';
import './Login.css';
import {Link, useNavigate} from 'react-router-dom';
import {sendSignInQuery} from "./request/sendSignInQuery";
import {Cookies} from "react-cookie";

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const cookies = new Cookies();
    const navigation = useNavigate();

    const signIn = async() => {
        if(email.length === 0 || password.length === 0){
            alert("Need to fill all required fields!");
        }else{
            const data = await sendSignInQuery(email,password);
            if(data !== null){
                //* Save login data to cookie.
                cookies.set("Login", data.id);
                cookies.set("Username", data.name);
                navigation("/", {replace: true});
            }
        }
    }
    return (
        <div className = "login">
            
            <Link to ="/">
                <img  className = "login-logo" src = "https://www.tornado-studios.com/sites/default/files/styles/slider_full/public/products/1934/gallery/rpg_7_thumbnail_square_0000.jpg?itok=EV122ywr" alt =""/>
            </Link>


            <div className = "login_container">

                <h1>로그인</h1>

                <form>
                    <h5>이메일</h5>
                    <input value={email} onChange= {e => setEmail(e.target.value)} type = "text"/>
                    <h5>비밀번호</h5>
                    <input value={password} onChange = {e => setPassword(e.target.value)} type = "password"/>

                    <button onClick = {(e) => {
                        e.preventDefault();
                        signIn();
                    }} className='login_signInButton'>로그인 하기</button>

                </form>
            </div>
            
        </div>
    );
}

export default Login;