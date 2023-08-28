import React, {useState} from 'react';
import './Signup.css';
import {Link, useNavigate} from 'react-router-dom';
import {sendTestQuery} from "./request/sendTestQuery";
import {sendSignUpQuery} from "./request/sendSignUpQuery";

function Signup() {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState('');

    const register = async () => {
        if(email.length === 0|| password.length === 0 || username.length === 0){
            alert("Need to fill all required fields!");
        }else{
            const res = await sendSignUpQuery(email,password,username);
            if(res){
                navigate('/', {replace:true});
            }
        }
    }

    return (
        <div className = "login">
            
            <Link to ="/">
                <img  className = "login-logo" src = "https://www.tornado-studios.com/sites/default/files/styles/slider_full/public/products/1934/gallery/rpg_7_thumbnail_square_0000.jpg?itok=EV122ywr" alt =""/>
            </Link>


            <div className = "login_container">

                <h1>회원가입</h1>

                <form>
                    <h5>이메일</h5>
                    <input value={email} onChange= {e => setEmail(e.target.value)} type = "text"/>
                    <h5>비밀번호</h5>
                    <input value={password} onChange = {e => setPassword(e.target.value)} type = "password"/>
                    <h5>닉네임</h5>
                    <input value={username} onChange = {e => setUsername(e.target.value)} type = "text"/>

                    <p>이용 약관 동의하십니까?</p>

                    <button type="submit" className='login_registerButton' onClick={async (e) => {
                        e.preventDefault()
                        await register();
                    }}>회원가입</button>
                </form>
            </div>
            
        </div>
    );
}

export default Signup;