import React from "react";
import "./Home.css";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="home">
      <div className="home-container">
        <div className="home_row1">
          <div className="background_image"></div>
          <h1>Random Profile Generator</h1>
          <Link to="/generator">
            <div className="generator_button">
              <button>지금 발사하기</button>
            </div>
          </Link>
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
