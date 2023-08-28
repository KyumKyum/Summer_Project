import React, { useState } from 'react';
import './Generator.css';
import {Cookies} from "react-cookie";
import {sendGenerateQuery} from "./request/sendGenerateQuery";

function Generator() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [avatarUrl, setAvatarUrl] = useState('');

    const cookies = new Cookies();

    const sendRequest = async () => {
        const userId = cookies.get("Login");
        const username = cookies.get("Username");

        const url = await sendGenerateQuery(userId, username, "TEST");
        setAvatarUrl(url);
        openModal();
    }

    const save = () => {
        navigator.clipboard.writeText(avatarUrl).then(() =>
            alert("Profile Url is successfully saved on your clipboard!")
        );
    }

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div className="generator">
            <div className='generator_row1'>
                <h1>방아쇠를 당겨 RPG를 발사하세요!</h1>

            </div>

            <div className='generatorContainer'>
                <button className='generatorButton' onClick={async (e) => {
                  e.preventDefault();
                  await sendRequest();
                }}>딸깍</button>
            </div>

            {isModalOpen && (
                  <div className="modalOverlay">
                  <div className="modal">
                      <h1>성공적으로 RPG가 생성되었습니다!</h1>
                      <img className="generator_img" src={avatarUrl} alt="" />
                      <div className="modalButtonContainer">
                          <button className="modalActionButton"
                           onClick={async (e) => {
                               e.preventDefault();
                               await sendRequest();
                           }}>재생성하기</button>
                          <button className="modalActionButton" onClick={(e) => {
                              e.preventDefault();
                              save();
                          }}>저장하기</button>
                          <button className="modalActionButton" onClick={closeModal}>닫기</button>
                      </div>
                  </div>
              </div>
            )}
        </div>
    );
}

export default Generator;
