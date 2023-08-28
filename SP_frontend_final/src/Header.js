import React from "react";
import "./Header.css";
import { motion } from "framer-motion";
import { Link } from "react-router-dom";
import { headerVariants } from "./motion";

function Header() {
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
          <div class="header_right_buttons">
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
        </div>
      </div>
    </motion.div>
  );
}

export default Header;
