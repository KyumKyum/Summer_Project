import React from 'react';
import './Maker.css';

function Maker() {

    return (


        <div className="profile">
    

            <div className="profile-container">
                <div className='job'>개발 총괄</div>
                <div className="profile_row">

                    <div className='profile_col1'>
                        <img className = "profile_img" src ="http://is.hanyang.ac.kr/wp-content/uploads/2021/03/%EC%9D%B4%EC%9A%B1_%EA%B5%90%EC%88%98-%EC%82%AC%EC%A7%84.jpg" alt="" />
                    </div>
                    <div className='profile_col2'>
                        <span className="name">임규민</span>
                        <span className="history    ">한양대 정보시스템학과 19학번</span>
                    </div>
                </div>

                <div className='job'>프론트엔드</div>
                <div className="profile_row">

                    <div className='profile_col1'>
                        <img className = "profile_img" src ="http://is.hanyang.ac.kr/wp-content/uploads/2021/03/%EC%95%88%EC%A2%85%EC%B0%BD_%EB%B6%80%EA%B5%90%EC%88%98-%EC%82%AC%EC%A7%84.jpg" alt="" />
                    </div>
                    <div className='profile_col2'>
                        <span className="name">강무성</span>
                        <span className="history">한양대 정보시스템학과 19학번</span>
                    </div>

                    <div className='profile_col1'>
                        <img className = "profile_img" src ="http://is.hanyang.ac.kr/wp-content/uploads/2021/03/Kutzner.jpg" alt="" />
                    </div>
                    <div className='profile_col2'>
                        <span className="name">박석원</span>
                        <span className="history">한양대 정보시스템학과 19학번</span>
                    </div>

                     <div className='profile_col1'>
                        <img className = "profile_img" src ="http://is.hanyang.ac.kr/wp-content/uploads/2021/03/%EC%9B%90%EC%98%81%EC%A4%80_%EB%B6%80%EA%B5%90%EC%88%98-%EC%82%AC%EC%A7%84.jpg" alt="" />
                    </div>
                    <div className='profile_col2'>
                        <span className="name">하성우</span>
                        <span className="history">한양대 정보시스템학과 19학번</span>
                    </div>
                </div>


                <div className='job'>백엔드</div>
                <div className="profile_row">

                    <div className='profile_col1'>
                        <img className = "profile_img" src ="http://is.hanyang.ac.kr/wp-content/uploads/2021/03/%EC%98%A4%ED%98%84%EC%98%A5_%EA%B5%90%EC%88%98-%EC%82%AC%EC%A7%84.jpg" alt="" />
                    </div>
                    <div className='profile_col2'>
                        <span className="name">권혁태</span>
                        <span className="history">한양대 정보시스템학과 19학번</span>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default Maker;
