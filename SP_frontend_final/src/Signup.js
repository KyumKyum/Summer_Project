import React, {useState} from 'react';
import './Signup.css';
import { Link } from 'react-router-dom';

function Signup() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const register = e => {
        e.preventDefault();
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

                    <p>이용 약관 동의하십니까?</p>

                    <button onClick = {register} className='login_registerButton'>회원가입</button>
                </form>
            </div>
            
        </div>
    );
}

export default Signup;