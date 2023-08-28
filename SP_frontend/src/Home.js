import React, {useEffect, useState} from "react";
import "./Home.css";
import {Link, useNavigate} from "react-router-dom";
import {Cookies} from "react-cookie";

export const Home = ({
    checkLogin,
    loggedIn
}) => {
  const cookies = new Cookies();
  const navigation = useNavigate();

  const proceed = () => {
    if(!loggedIn){
      alert("You need to be logged in first!")
      navigation("/login", {replace: true});
    }else{
      navigation("/generator", {replace: true});
    }
  }

  useEffect(() => {
    if(cookies.get("Login")?.length > 0){
      checkLogin(true);
    }
  },[cookies]);

  return (
    <div className="home">
      <div className="home-container">
        <div className="home_row1">
          <div className="background_image"></div>
          <h1>Random Profile Generator</h1>
            <div className="generator_button">
              <button style={{display: "flex"}}
              onClick={(e) => {
                e.preventDefault();
                proceed();
              }}>{`지금 발사하기`}</button>
            </div>
        </div>

        <div className="home_row2">
          <small>
            RPG는 Random Profile Genrator의 약어로, AI를 이용하여 랜덤 프로필
            이미지를 생성해주는 서비스 입니다. 우리는 이 프로젝트를
            진행함으로써, 2023-2학기의 원영준을 성공적으로 물리치길 바랍니다.
            우리 팀은 임규민을 필두로 하여 권혁태, 강무성, 하성우 그리고
            박석원으로 구성되어 있습니다.
          </small>
        </div>

        <img
          className="home_img"
          src="https://blog.kakaocdn.net/dn/bxdtFz/btqAfmWl2Zl/anXjXtfAfFUC0OOgkIcMWk/img.jpg"
          alt=""
        />
      </div>
    </div>
  );
}

export default Home;
