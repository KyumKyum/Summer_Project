import React from "react";
import "./Header.css";
import { motion } from "framer-motion";
import {Link, useNavigate} from "react-router-dom";
import { headerVariants } from "./motion";
import {Cookies} from "react-cookie";

export const Header = ({
    loggedIn
                       }) => {

  const cookies = new Cookies();
  const navigation = useNavigate();
  const logout = () => {
    alert("Proceed Logout!")
    cookies.remove("Login");
    cookies.remove("Username");
    window.location.reload();
  }

  return (
    <motion.div initial="hidden" whileInView="show" variants={headerVariants}>
      <div className="header">
        <Link to="/">
          <img
            className="header_logo"
            src="https://www.tornado-studios.com/sites/default/files/styles/slider_full/public/products/1934/gallery/rpg_7_thumbnail_square_0000.jpg?itok=EV122ywr"
            alt=""
          />
        </Link>

        <div class="header_nav">
          <div class="header_left_buttons">
            <Link to="/generator">
              <div className="header_generator">
                <button>프로필 생성하기</button>
              </div>
            </Link>
            <Link to="/maker">
              <div className="header_maker">
                <button>만든이들</button>
              </div>
            </Link>
          </div>
          {!loggedIn ? (
              <div className="header_right_buttons">
                <Link to="/login">
                  <div className="header_login">
                    <button>로그인</button>
                  </div>
                </Link>
                <Link to="/signup">
                  <div className="header_singup">
                    <button>회원가입</button>
                  </div>
                </Link>
              </div>
          ): (
              <div style={{
                marginRight: 20
              }}
                  className="header_right_buttons">
                  <div className="header_login">
                    <button onClick={(e) => {
                      e.preventDefault();
                      logout();
                    }}>로그아웃</button>
                  </div>
              </div>
          )}
        </div>
      </div>
    </motion.div>
  );
}

export default Header;
