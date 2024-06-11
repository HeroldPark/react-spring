import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Modal from 'react-modal';
import "../../css/SettingList.css";
import "../../css/content.css";
import TreeMenu from 'react-simple-tree-menu';
import 'react-simple-tree-menu/dist/main.css';

Modal.setAppElement('#root');

const SettingList = () => {
  const [searchType, setSearchType] = useState('');
  const [keyword, setKeyword] = useState('');
  const [commonCodes, setCommonCodes] = useState([]); // Initialize as an empty array
  const [selectedNode, setSelectedNode] = useState(null);
  const [modalIsOpen, setIsOpen] = useState(false);
  const [formData, setFormData] = useState({
    commonCode: '',
    commonName: '',
    parentsCode: '',
    comment: '',
    codeOrder: '',
    referId1: '',
    referId2: '',
    useYN: 'Y'
  });

  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}`
    }
  });

  useEffect(() => {
    axiosInstance.get('/commoncode/setting_list')
      .then(response => {
        setCommonCodes(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the data!", error);
      });
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    // Implement search functionality
  };

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = () => {
    setIsOpen(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Implement form submission logic
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleNodeClick = (node) => {
    console.log('Node clicked:', node);

    if (node.parentsCode === '') {
      node.parentsCode = "ROOT";
    }
    const params = `groupCode=PUB&commonCode=${node.commonCode}&parentsCode=${node.parentsCode}`;
    console.log("handleNodeClick: " + params);
    axiosInstance.get('/commoncode/commonCodeAjax?' + params)
      .then(response => {
        const commonCodeData = response.data;

        setFormData({
          commonCode: commonCodeData.commonCode || '',
          commonName: commonCodeData.commonName || '',
          parentsCode: commonCodeData.parentsCode || '',
          comment: commonCodeData.comment || '',
          codeOrder: commonCodeData.codeOrder || '',
          referId1: commonCodeData.referId1 || '',
          referId2: commonCodeData.referId2 || '',
          useYN: commonCodeData.useYN || 'Y'
        });
      })
      .catch(error => {
        console.error("There was an error fetching the data!", error);
      });

    console.log("handleNodeClick: " + node);
  };

  const buildTreeData = (nodes) => {
    if (!Array.isArray(nodes)) {
      return [];
    }

    const map = {};
    const roots = [];

    // 모든 노드를 맵에 저장하고 children 배열 초기화
    nodes.forEach(node => {
        map[node.commonCode] = { 
            ...node, 
            key: node.commonCode,
            label: `${node.commonCode} - ${node.commonName}`, 
            nodes: [] 
        };
    });

    // 각 노드를 부모에 추가
    nodes.forEach(node => {
        if (node.parentsCode === "ROOT") {
            roots.push(map[node.commonCode]);
        } else if (map[node.parentsCode]) {
            map[node.parentsCode].nodes.push(map[node.commonCode]);
        }
    });

    // 빈 nodes 배열이 아닌 경우에만 nodes 속성을 포함
    roots.forEach(root => {
        if (root.nodes.length === 0) {
            delete root.nodes;
        } else {
            root.nodes.sort((a, b) => a.codeOrder - b.codeOrder);
        }
    });

    return roots;
  };

  const treeData = buildTreeData(commonCodes);

  return (
    <div className="SettingList">
      <header className="page_tits">
        <h3>시스템</h3>
        <p className="path"><strong>현재 위치 :</strong> <span>웹서버 관리</span> <span>공통코드</span> <span>설정</span></p>
      </header>
  
      <section>
        <div className="search_box">
          <form id="searchForm" onSubmit={handleSearch} autoComplete="off">
            <div className="sch-group fl">
              <select id="searchType" name="searchType" value={searchType} onChange={(e) => setSearchType(e.target.value)} title="검색 유형 선택">
                <option value="">전체 검색</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="writer">작성자</option>
              </select>
              <input type="text" id="keyword" name="keyword" value={keyword} onChange={(e) => setKeyword(e.target.value)} placeholder="키워드를 입력해 주세요." title="키워드 입력" />
              <button type="submit" className="bt-search"><i className="fas fa-search"></i><span className="skip-info">검색</span></button>
            </div>
          </form>
        </div>

        <div className="both-wrap">
          <div className="both-left">
            <div className="tree-wrap">
              <div className="tree">
                <TreeMenu
                  data={treeData}
                  onClickItem={({ key, label, ...props }) => {
                    console.log('Key:', key);
                    handleNodeClick(props);
                  }}
                />
              </div>
              <div className="btn-group right">
                <button type="button" className="btn-basic blue-small" onClick={openModal}>등록</button>
              </div>
            </div>
          </div>

          <div className="both-right">
            <div className="btn-group right">
              <a href="#" onClick={() => handleSubmit('U')} className="btn-basic outline-small" style={{ marginRight: '5px' }}>수정</a> 
              <a href="#" onClick={() => handleSubmit('D')} className="btn-basic outline-small">삭제</a>
            </div>
            <div className="view-table">
              <form id="frm" name="frm" onSubmit={handleSubmit}>
                <input type="hidden" name="groupCode" value="PUB" />
                <input type="hidden" name="doTran" />
                <input type="hidden" id="checkModCode" value="Y" />
                <input type="hidden" className="form-control" name="commonCodeVal" value={formData.commonCode} />
                <input type="hidden" className="form-control" name="orderMax" value="" />

                <table>
                  <colgroup>
                    <col style={{ width: '20%' }} />
                    <col />
                    <col style={{ width: '20%' }} />
                    <col />
                  </colgroup>
                  <tbody>
                    <tr>
                      <th>공용코드<em>*</em></th>
                      <td><input type="text" className="form-control" name="commonCode" value={formData.commonCode} readOnly required title="공용코드" /></td>
                      <th>상위공용코드</th>
                      <td><input type="text" className="form-control" id="parentsCode" name="parentsCode" value={formData.parentsCode} readOnly /></td>
                    </tr>
                    <tr>
                      <th>코드명<em>*</em></th>
                      <td><input type="text" className="form-control" name="commonName" value={formData.commonName} onChange={handleChange} required title="코드명" /></td>
                      <th>상위코드명</th>
                      <td><input type="text" className="form-control" id="parentsName" value="최상위" readOnly /></td>
                    </tr>
                    <tr>
                      <th>참조명</th>
                      <td><input type="text" className="form-control" id="referId1" name="referId1" value={formData.referId1} onChange={handleChange} /></td>
                      <th>참조값</th>
                      <td><input type="text" className="form-control" id="referId2" name="referId2" value={formData.referId2} onChange={handleChange} /></td>
                    </tr>
                    <tr>
                      <th>첨부설명</th>
                      <td colSpan="3"><textarea rows="5" id="comment" name="comment" className="form-control" value={formData.comment} onChange={handleChange} required title="첨부설명"></textarea></td>
                    </tr>
                    <tr>
                      <th>순서</th>
                      <td><input type="text" name="codeOrder" value={formData.codeOrder} onChange={handleChange} className="form-control" /></td>
                      <th>사용유무</th>
                      <td>
                        <div className="form-group d-inline-flex margin-0">
                          <div className="custom-control custom-radio padding-r-10">
                            <input className="custom-control-input" type="radio" id="usedY" name="useYN" value="Y" checked={formData.useYN === 'Y'} onChange={handleChange} />
                            <label htmlFor="usedY" className="custom-control-label">사용</label>
                          </div>
                          <div className="custom-control custom-radio">
                            <input className="custom-control-input" type="radio" id="usedN" name="useYN" value="N" checked={formData.useYN === 'N'} onChange={handleChange} />
                            <label htmlFor="usedN" className="custom-control-label">미사용</label>
                          </div>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </form>
            </div>
            <div className="btn-group right">
              <a href="#" onClick={() => handleSubmit('U')} className="btn-basic outline-small" style={{ marginRight: '5px' }}>수정</a>
              <a href="#" onClick={() => handleSubmit('D')} className="btn-basic outline-small">삭제</a>
            </div>
          </div>
        </div>
      </section>

      <Modal isOpen={modalIsOpen} onRequestClose={closeModal} contentLabel="Code Registration Modal">
        <h2>공용코드 등록</h2>
        <form id="regFrm" name="regFrm" onSubmit={handleSubmit}>
          <input type="hidden" name="groupCode" value="PUB" />
          <input type="hidden" name="doTran" value="I" />
          <input type="hidden" id="checkRegCode" value="Y" />
          <table>
            <colgroup>
              <col style={{ width: '20%' }} />
              <col />
              <col style={{ width: '20%' }} />
              <col />
            </colgroup>
            <tbody>
              <tr>
                <th className="common_code">공용코드<em>*</em></th>
                <td><input type="text" className="form-control" name="commonCode" value={formData.commonCode} maxLength="20" onChange={handleChange} required title="공용코드" /></td>
                <th>상위공용코드</th>
                <td><input type="text" className="form-control" name="parentsCode" value="" readOnly /></td>
              </tr>
              <tr>
                <th>코드명<em>*</em></th>
                <td><input type="text" className="form-control" name="commonName" value={formData.commonName} onChange={handleChange} required title="공용코드명" /></td>
                <th>상위코드명</th>
                <td><input type="text" className="form-control" name="parentsName" value="" readOnly /></td>
              </tr>
              <tr>
                <th>참조명</th>
                <td><input type="text" className="form-control" id="referId1" name="referId1" value={formData.referId1} onChange={handleChange} /></td>
                <th>참조값</th>
                <td><input type="text" className="form-control" id="referId2" name="referId2" value={formData.referId2} onChange={handleChange} /></td>
              </tr>
              <tr>
                <th>첨부설명<em>*</em></th>
                <td colSpan="3"><textarea rows="5" name="comment" className="form-control" value={formData.comment} onChange={handleChange} required title="첨부설명"></textarea></td>
              </tr>
              <tr>
                <th>순서</th>
                <td><input type="text" name="codeOrder" value={formData.codeOrder} onChange={handleChange} className="form-control" /></td>
                <th>사용유무</th>
                <td>
                  <div className="form-group d-inline-flex margin-0">
                    <div className="custom-control custom-radio padding-r-10">
                      <input className="custom-control-input" type="radio" id="mUsedY" name="useYN" value="Y" checked={formData.useYN === 'Y'} onChange={handleChange} />
                      <label htmlFor="mUsedY" className="custom-control-label">사용</label>
                    </div>
                    <div className="custom-control custom-radio">
                      <input className="custom-control-input" type="radio" id="mUsedN" name="useYN" value="N" checked={formData.useYN === 'N'} onChange={handleChange} />
                      <label htmlFor="mUsedN" className="custom-control-label">미사용</label>
                    </div>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
        <button onClick={closeModal}>닫기</button>
      </Modal>
    </div>
  );
}

export default SettingList;
