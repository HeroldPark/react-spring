import axios from 'axios';
import React, { useState, useEffect } from 'react';
import TreeComponent from './TreeComponent';

import "../../css/commoncode.css";

const SettingList = () => {
  const [form, setForm] = useState({
    searchType: '',
    keyword: '',
    commonCode: '',
    parentsCode: '',
    commonName: '',
    referId1: '',
    referId2: '',
    comment: '',
    codeOrder: '',
    useYN: 'Y',
    rootOrderMax: ''
  });

  const [zNodes, setZNodes] = useState([]);
  const [selectedNode, setSelectedNode] = useState(null);

  // 트리 데이터 초기 설정
  const treeData = {
    name: 'root',
    toggled: true,
    children: [
        { name: 'parent', commonCode: '001', children: [{ name: 'child1', commonCode: '002' }] }
    ]
  };

  // 노드 선택 처리
  const handleNodeSelect = (node) => {
      console.log('Selected node:', node);
      setSelectedNode(node);
      // 추가적인 동작 (예: 세부 정보 표시)
  };

  useEffect(() => {
    // Fetch the zNodes data (replacing Thymeleaf variable with an API call or static data)
    axios.get('/api/commoncodes') // Replace with your API endpoint
      .then(response => {
        setZNodes(response.data);
        // Initialize the zTree here if using a library
      });
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm(prevState => ({ ...prevState, [name]: value }));
  };

  const handleSearch = () => {
    // Implement search logic
    console.log('Search clicked', form);
  };

  const handleNodeClick = (node) => {
    setSelectedNode(node);
    // Fetch node details if needed
  };

  const handleSubmit = (tran) => {
    if (tran !== 'D' && !formRequiredCheck()) {
      return;
    }
    if (validateCode(tran)) {
      const data = { ...form, doTran: tran };
      axios.post('/api/commoncodes/transaction', data) // Replace with your API endpoint
        .then(response => {
          alert(response.data.msgMent);
          // Handle success, maybe refresh the list or reset the form
        });
    }
  };

  const formRequiredCheck = () => {
    // Implement form validation
    return true;
  };

  const validateCode = (tran) => {
    if (tran === 'D' && !window.confirm('삭제하시겠습니까?')) {
      return false;
    }
    if (tran === 'I' && form.commonCode === 'N') {
      alert('중복된 코드값이 입력되었습니다.');
      return false;
    }
    return true;
  };

  return (
    <div className="content">
      <div className="page_tits">
        <h3>시스템</h3>
        <p className="path"><strong>현재 위치 :</strong> <span>웹서버 관리</span> <span>공통코드</span> <span>설정</span></p>
      </div>

      <section>
        <div className="search_box">
          <form id="searchForm" onSubmit={(e) => e.preventDefault()} autoComplete="off">
            <div className="sch_group fl">
              <select id="searchType" name="searchType" title="검색 유형 선택" value={form.searchType} onChange={handleInputChange}>
                <option value="">전체 검색</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="writer">작성자</option>
              </select>
              <input type="text" id="keyword" name="keyword" placeholder="키워드를 입력해 주세요." title="키워드 입력" value={form.keyword} onChange={handleInputChange} />
              <button type="button" className="bt_search" onClick={() => handleSearch()}><i className="fas fa-search"></i><span className="skip_info">검색</span></button>
            </div>
          </form>
        </div>

        <div>
            <h1>Tree View Example</h1>
            <TreeComponent data={treeData} onNodeSelect={handleNodeSelect} />
            {selectedNode && <div>Selected Node: {selectedNode.name}</div>}
        </div>
        
        <div className="container-fluid">
          <div className="card card-default">
            <div className="card-body">
              <div className="both-wrap">
                <div className="both-left">
                  <div className="tree-wrap">
                    <div className="tree">
                      <div className="tree_list">
                        <ul id="treeDemo" className="ztree">
                          {/* Render zTree nodes here */}
                          {zNodes.map(node => (
                            <li key={node.commonCode} onClick={() => handleNodeClick(node)}>{node.commonName}</li>
                          ))}
                        </ul>
                      </div>
                    </div>
                    <div className="btn-group right">
                      <button type="button" className="btn-basic blue-small" onClick={() => handleSubmit('I')}>등록</button>
                    </div>
                  </div>
                </div>

                <div className="both-right">
                  <div className="btn-group right">
                    <button onClick={() => handleSubmit('U')} className="btn-basic outline-small">수정</button>
                    <button onClick={() => handleSubmit('D')} className="btn-basic outline-small">삭제</button>
                  </div>
                  <div className="view-table">
                    <form id="frm" name="frm">
                      <input type="hidden" name="groupCode" value="PUB" />
                      <input type="hidden" name="doTran" value="" />
                      <input type="hidden" id="checkModCode" value="Y" />
                      <input type="hidden" className="form-control" name="commonCodeVal" value={form.commonCode} />
                      <input type="hidden" className="form-control" name="orderMax" value={form.orderMax} />

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
                            <td><input type="text" className="form-control" name="commonCode" value={form.commonCode} readOnly required title="공용코드" onChange={handleInputChange} /></td>
                            <th>상위공용코드</th>
                            <td><input type="text" className="form-control" id="parentsCode" name="parentsCode" value={form.parentsCode} readOnly onChange={handleInputChange} /></td>
                          </tr>
                          <tr>
                            <th>코드명<em>*</em></th>
                            <td><input type="text" className="form-control" name="commonName" value={form.commonName} required title="코드명" onChange={handleInputChange} /></td>
                            <th>상위코드명</th>
                            <td><input type="text" className="form-control" id="parentsName" value={form.parentsName} readOnly /></td>
                          </tr>
                          <tr>
                            <th>참조명</th>
                            <td><input type="text" className="form-control" id="referId1" name="referId1" value={form.referId1} onChange={handleInputChange} /></td>
                            <th>참조값</th>
                            <td><input type="text" className="form-control" id="referId2" name="referId2" value={form.referId2} onChange={handleInputChange} /></td>
                          </tr>
                          <tr>
                            <th>첨부설명</th>
                            <td colSpan="3"><textarea rows="5" id="comment" name="comment" className="form-control" required title="첨부설명" value={form.comment} onChange={handleInputChange}></textarea></td>
                          </tr>
                          <tr>
                            <th>순서</th>
                            <td><input type="text" name="codeOrder" value={form.codeOrder} className="form-control" onChange={handleInputChange} /></td>
                            <th>사용유무</th>
                            <td>
                              <div className="form-group d-inline-flex margin-0">
                                <div className="custom-control custom-radio padding-r-10">
                                  <input className="custom-control-input" type="radio" id="usedY" name="useYN" value="Y" checked={form.useYN === 'Y'} onChange={handleInputChange} />
                                  <label className="custom-control-label" htmlFor="usedY">Y</label>
                                </div>
                                <div className="custom-control custom-radio">
                                  <input className="custom-control-input" type="radio" id="usedN" name="useYN" value="N" checked={form.useYN === 'N'} onChange={handleInputChange} />
                                  <label className="custom-control-label" htmlFor="usedN">N</label>
                                </div>
                              </div>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </form>
                  </div>
                </div>
              </div>

              <div className="btn_group text-center">
                <button type="button" className="btn-basic blue-large" onClick={() => handleSubmit('I')}>등록</button>
                <button type="button" className="btn-basic outline-large" onClick={() => handleSubmit('U')}>수정</button>
                <button type="button" className="btn-basic outline-large" onClick={() => handleSubmit('D')}>삭제</button>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default SettingList;
