// LayerPopup.js
import React from 'react';
import { ResizableBox } from 'react-resizable';
import 'react-resizable/css/styles.css';
import '../../css/LayerPopup.css'; // Add your CSS styles for the layer popup

const LayerPopup = ({ isOpen, closeModal, onSubmit, onDelete, mode, children }) => {
  if (!isOpen) return null;

  console.log("LayerPopup.js mode=" + mode );

  return (
    <div className="layer-popup-overlay" onClick={closeModal}>
      <ResizableBox
        width={650}
        height={450}
        minConstraints={[300, 200]}
        maxConstraints={[1200, 800]}
        className="layer-popup-content"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="resizable-content">
          {children}
          <div className="button-group">
            {mode === 'edit' && <button className="btn-basic" onClick={onDelete}>삭제</button>}
            <button className="btn-basic" onClick={onSubmit}>{mode === 'edit' ? '수정' : '등록'}</button>
            <button className="btn-basic cancel" onClick={closeModal}>취소</button>
          </div>
        </div>
      </ResizableBox>
    </div>
  );
};

export default LayerPopup;
