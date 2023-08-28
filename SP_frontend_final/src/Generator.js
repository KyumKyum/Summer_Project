import React, { useState } from 'react';
import './Generator.css';

function Generator() {
    const [isModalOpen, setIsModalOpen] = useState(false);

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
                <button className='generatorButton' onClick={openModal}>딸깍</button>
            </div>

            {isModalOpen && (
                  <div className="modalOverlay">
                  <div className="modal">
                      <h1>성공적으로 RPG가 생성되었습니다!</h1>
                      <img className="generator_img" src="https://mblogthumb-phinf.pstatic.net/MjAyMzA1MjVfMTIx/MDAxNjg1MDIzMDc0ODUz.uJreGdrOfvEICaIIVptiSxLekLUsU_MGjAqdCOwjR48g.A-JEXPGfxpb8WBf5bxYiaRbmCOrSi0yjKMkiJ_SxANMg.JPEG.urone1378/%EC%9C%88%ED%84%B001.jpg?type=w800" alt="" />
                      <div className="modalButtonContainer">
                          <button className="modalActionButton">재생성하기</button>
                          <button className="modalActionButton">저장하기</button>
                          <button className="modalActionButton" onClick={closeModal}>닫기</button>
                      </div>
                  </div>
              </div>
            )}
        </div>
    );
}

export default Generator;
