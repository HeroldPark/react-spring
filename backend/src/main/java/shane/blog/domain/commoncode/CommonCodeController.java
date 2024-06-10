package shane.blog.domain.commoncode;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shane.blog.utils.CustomUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

@Controller
public class CommonCodeController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private CodeService codeService;

  // @Autowired
  // private SessionService sessionService;

  // @Autowired
  // private HistoryService historyService;

  // 첨부파일 업로드할 경로를 변수값으로 가져옴. application.yaml에 빈으로 등록되어있음.
  @Value("${property.app.uploadPath}")
  private String uploadPath;

  // // 위협대응
  // @RequestMapping(value = "/commoncode/setting", method = { RequestMethod.GET,
  // RequestMethod.POST })
  // public String setting(Model model, CommonResponse commonResponse, HttpSession
  // session) throws Exception {

  // logger.debug("react_list page Start");

  // // return "common.setting.setting";
  // return "redirect:/react/react_list";
  // }

  /**
   * commonCode 페이지 호출
   * 
   * @param session
   * @param model
   * @param commonCode
   * @return
   */
  @RequestMapping(value = "/commoncode/setting_list", method = { RequestMethod.GET, RequestMethod.POST })
  @ResponseBody
  public List<CommonResponse> commonCode(Model model, CommonResponse commonResponse, HttpSession session)
      throws Exception {

    logger.debug("commonCode page Start");

    if (commonResponse.getGroupCode() == null || commonResponse.getGroupCode().equals("")) {
      commonResponse.setGroupCode("PUB");
    }
    commonResponse.setUseYN(null);
    List<CommonResponse> response = codeService.getFullCodes(commonResponse);
    // String responseJson = new CustomUtil().objectToJson(response);
    // model.addAttribute("codes", responseJson);
    // int codeCount = codeService.getCodesOrderMax(commonResponse);
    // model.addAttribute("rootOrderMax", codeCount);

    // model.addAttribute("topMenu", "public");
    // model.addAttribute("leftMenu", "code");

    // return "commoncode/SettingList";
    return response;
  }

  @RequestMapping(value = "/commoncode/commonCodeAjax", method = { RequestMethod.GET, RequestMethod.POST })
  public @ResponseBody CommonResponse commonCodeAjax(CommonResponse commonCode) {
    logger.debug("commonCodeAjax page Start");
    return codeService.commonCodeDetail(commonCode);
  }

  /**
   * 코드 정보 등록, 수정, 삭제
   * 
   * @param session
   * @param model
   * @param commonCode
   * @return
   */
  @RequestMapping(value = "/commoncode/commonCodeTranAjax", method = { RequestMethod.GET, RequestMethod.POST })
  public @ResponseBody MsgResponse commonCodeTranAjax(CommonResponse commonCode) {
    logger.debug("commonCodeTranAjax page Start");

    MsgResponse msg = new MsgResponse();
    if (commonCode.getDoTran().equals("I")) {
      msg = codeService.doInsertCode(commonCode);
    } else if (commonCode.getDoTran().equals("U")) {
      msg = codeService.doUpdateCode(commonCode);
    } else if (commonCode.getDoTran().equals("D")) {
      msg = codeService.doDeleteCode(commonCode);
    }
    return msg;
  }

  /**
   * 기초 코드 중복 체크
   * 
   * @param commonCode
   * @return
   */
  @RequestMapping(value = "/commoncode/dupleCodeCheck", method = { RequestMethod.GET, RequestMethod.POST })
  public @ResponseBody MsgResponse dupleCodeCheck(CommonResponse commonCode) {
    logger.debug("dupleCodeCheck page Start");
    return codeService.dupleCodeCheck(commonCode);
  }

  @SuppressWarnings("serial")
  private ArrayList<String> checkImgArray = new ArrayList<String>() { // 변수 생성 후 바로 리스트 3개 입력처리.
    {
      add("gif");
      add("jpg");
      add("jpeg");
      add("png");
      add("bmp");
    }
  };

  public String getUploadPath() {
    return uploadPath;
  }

  public void setUploadPath(String uploadPath) {
    this.uploadPath = uploadPath;
  }

  /**
   * 프로필 png파일 업로드 전용 메서드 구현
   */
  public void profile_upload(String userId, HttpServletRequest request, MultipartFile file) throws Exception {
    // 프로필 첨부파일 처리(아래)
    // 직접 접근이 가능한 경로에 프로필 업로드 폴더 생성
    String folderPath = request.getServletContext().getRealPath("/resources/profile");
    File makeFolder = new File(folderPath);
    if (!makeFolder.exists()) {
      // 프로필 폴더가 존재하지 않으면,
      makeFolder.mkdir(); // 프로필 폴더 생성
    }
    if (file.getOriginalFilename() != null) {
      // jsp에서 전송 받은 파일이 null이 아니라면,
      byte[] in = file.getBytes();
      String uploadFile = folderPath + "/" + userId + "."
          + StringUtils.getFilenameExtension(file.getOriginalFilename());
      File out = new File(uploadFile);
      FileCopyUtils.copy(in, out); // 중복 파일명(확장자포함)은 덮어쓰는 메드
    }
  }

  /**
   * 게시물 첨부파일 이미지보기 메서드 구현(크롬에서는 문제없고, IE에서 스프링시큐리티적용 후 IE에서 지원가능)
   * 에러메시지 처리: getOutputStream() has already been called for this respons
   * 
   * @throws IOException
   */
  // @RequestMapping(value = "/image_preview", method = RequestMethod.GET) //
  // produces = MediaType.IMAGE_JPEG_VALUE
  // @ResponseBody
  // public ResponseEntity<byte[]>
  // getImageAsByteArray(@RequestParam("saveFileName") String saveFileName,
  // HttpServletResponse response) throws IOException {
  // FileInputStream fis = null;
  // ByteArrayOutputStream baos = new ByteArrayOutputStream();
  // fis = new FileInputStream(uploadPath + "/" + saveFileName); // 업로드된 이미지를
  // fis변수에 저장
  // int readCount = 0;
  // byte[] buffer = new byte[1024]; // 1k바이트 단위로 읽어들이기 위해
  // byte[] fileArray = null;
  // while ((readCount = fis.read(buffer)) != -1) {
  // baos.write(buffer, 0, readCount);
  // }
  // fileArray = baos.toByteArray(); // 바이트 단위로 되어있는 변수에 아웃풋스트림내용을 저장해서 return으로
  // 반환
  // fis.close(); // 고전 자바프로그램에서는 메모리 관리를 위해서 fis 파일인풋스트림변수 생성 후 소멸시키는 작업이 필수
  // baos.close(); // 스프링프레임워크 기반의 프로그램 구조에서는 close와 같은 메모리관리가 필요없다. = 스프링에는
  // 가비지컬렉터기능 내장

  // final HttpHeaders headers = new HttpHeaders(); // 크롬개발자도구에서 확인가능
  // String ext = FilenameUtils.getExtension(saveFileName);// 파일 확장자 구하기
  // switch (ext.toLowerCase()) { // 확장자명 소문자로 변환 후, 분리
  // case "png":
  // headers.setContentType(MediaType.IMAGE_PNG);
  // break;
  // case "jpg":
  // headers.setContentType(MediaType.IMAGE_JPEG);
  // break;
  // case "gif":
  // headers.setContentType(MediaType.IMAGE_GIF);
  // break;
  // case "jpeg":
  // headers.setContentType(MediaType.IMAGE_JPEG);
  // break;
  // case "bmp":
  // headers.setContentType(MediaType.parseMediaType("image/bmp"));
  // break;
  // default:
  // break;
  // }
  // return new ResponseEntity<byte[]>(fileArray, headers, HttpStatus.CREATED);

  // }

  // 파일 다운로드 구현한 메소드(아래)
  @RequestMapping(value = "/download", method = RequestMethod.GET)
  @ResponseBody // 이 어노테이션으로 지정된 메소드는 페이지 이동처리 아니고, RestAPI처럼 현재 페이지에서 구현 결과 내용을 전송 받음
  public FileSystemResource download(
      @RequestParam("saveFileName") String saveFileName,
      @RequestParam("realFileName") String realFileName,
      HttpServletResponse response) throws Exception { // 파일시스템리소스로 현재 페이지에서 반환받음.
    File file = new File(uploadPath + "/" + saveFileName); // 다운받을 파일 경로 지정
    response.setContentType("application/download; utf-8"); // 파일"내용" 중 한글이 깨지는 것 방지
    realFileName = URLEncoder.encode(realFileName, "UTF-8").replaceAll("\\+", "%20");
    // 위의 URLEncoder는 파일"명"이 한글(일본어, 베트남어)일 떄, 깨지는 것 방지.
    response.setHeader("Content-Disposition", "attachment; filename=" + realFileName);
    return new FileSystemResource(file); // 실제 다운로드 시작
  }

  // 파일 업로드= xml에서 지정한 폴더에 실제파일 저장을 구현한 메소드(아래)
  public String fileUpload(MultipartFile file) throws IOException {
    String realFileName = file.getOriginalFilename(); // jsp에서 전송한 파일명 -> 확장자 구할 때 사용
    // 만약 파일이 여러개면 아래 부분에 변수 처리 로직이 더 들어가야 한다.
    // 폴더에 저장할 PK용 파일명 만들기(아래)
    UUID uid = UUID.randomUUID(); // unique id 생성 : 폴더에 저장할 파일명으로 사용
    // String saveFileName = uid.toString()+"."+realFileName.split("\\.")[1];
    // //문제발생하여 아래코드로 대체
    String saveFileName = uid.toString() + "." + StringUtils.getFilenameExtension(realFileName);
    // realFileName.split("\\."); realFileName을 .으로 분할해서 파일변수로 만드는 메소드
    // ex. abc.jpg -> realFileName[0]=abc, realFileName[1]=jpg
    // String[] files = new String[] {saveFileName}; //saveFileName 스트링형을 배열변수
    // files로 형변환
    byte[] fileData = file.getBytes(); // jsp폼에서 전송된 파일이 fileData변수(메모리)에 저장된다.

    // uploadPath경로의 saveFileName이름을 가진 파일이 타겟이 된다.
    File target = new File(uploadPath, saveFileName); // 파일 저장하기 바로 전 설정 저장
    FileCopyUtils.copy(fileData, target); // 실제로 target폴더에 파일로 저장되는 메소드 = 업로드끝

    return saveFileName; // copy로 업로드 이후에 저장된 realFileName 스트링 문자열값 1개 반환
  }

  // REST-API서비스로 사용할 때, @ResponseBody 어노테이션으로 json텍스트데이터를 반환함 (아래)
  // 아래는 RestAPI백엔드단, Ajax(jsp)부분은 Rest-API의 프론트 엔드단.
  // @RequestMapping(value = "/id_check", method = RequestMethod.GET)
  // @ResponseBody
  // public String id_check(@RequestParam("userId") String userId) {
  // String result = "0"; // 아이디 중복값을 체크하는 변수. 기본값은 중복값 없음(0)
  // // Rest-API 서비스에서는 스프링을 통해 Ajax로 에러메세지를 받을 수 없기 때문에, 여기서 예외처리해야한다
  // // throws Exception 사용하지 않고 try-catch문 사용하는 이유(위)
  // try {
  // MemberResponse memberVO = memberService.readMember(userId);
  // if (memberVO != null) {
  // result = "1";
  // } // 아이디 중복값이 있을 경우
  // else {
  // result = "0";
  // } // 아이디 중복값이 없을 경우
  // } catch (Exception e) {
  // // 위 readMember 메소드에서 에러발생시
  // result = e.toString();
  // }
  // return result; // 결과값은 0, 1, 또는 에러메세지 중 한가지
  // }

  // @Transactional
  // @RequestMapping(value = "/file_delete", method = RequestMethod.POST)
  // @ResponseBody // 메소드 응답을 내용만 반환받겠다고 명시. RestAPI 사용
  // public String file_delete(@RequestParam("saveFileName") String saveFileName)
  // {
  // String result = "";
  // try {
  // boardDAO.deleteAttach(saveFileName); // DB에서만 지워짐.
  // // 실제 폴더에서 파일 지우기
  // File target = new File(uploadPath, saveFileName);
  // if (target.exists()) {
  // target.delete();// 폴더에서 기존첨부파일 지우기
  // }

  // result = "success";
  // } catch (Exception e) {
  // result = "fail :" + e.toString();
  // }
  // return result;
  // }

  public ArrayList<String> getCheckImgArray() {
    return checkImgArray;
  }

  public void setCheckImgArray(ArrayList<String> checkImgArray) {
    this.checkImgArray = checkImgArray;
  }

  // @RequestMapping(value = "/commoncode/user_list", method = {
  // RequestMethod.GET,
  // RequestMethod.POST })
  // public String user_list(Model model, MemberResponse memberVO,
  // HttpServletRequest request)
  // throws Exception {

  // logger.debug("/commoncode/user_list page Start");

  // // if (sessionVO.getKeyword() != null && sessionVO.getKeyword() != "") {
  // // model.addAttribute("keyword", sessionVO.getKeyword());
  // // model.addAttribute("searchType", sessionVO.getSearchType());
  // // }

  // // // 검색된 전체 화면 명수 구하기 서비스 호출
  // int countMember = memberService.countMember(memberVO);
  // memberVO.setTotalCount(countMember); // 전체 회원 수를 구한 변수값을 매개변수로 입력

  // List<MemberResponse> members_list = memberService.selectMember(memberVO);
  // model.addAttribute("members_list", members_list);// members_list 2차원 배열을
  // members_array 클래스 오브젝트로 변경

  // return "common.user.user_list";
  // }

  // 이력 리스트 검색
  // @RequestMapping(value = "/commoncode/history_list", method = {
  // RequestMethod.GET,
  // RequestMethod.POST })
  // public String history_list(Model model, HistoryVO historyVO, HttpSession
  // session) throws Exception {

  // logger.debug("CommonCodeController /commoncode/history_list 시작. \t {}", new
  // Date());

  // if (historyVO.getKeyword() != null && historyVO.getKeyword() != "") {
  // model.addAttribute("keyword", historyVO.getKeyword());
  // model.addAttribute("searchType", historyVO.getSearchType());
  // }

  // int count = historyService.countHistory(historyVO);
  // historyVO.setTotalCount(count);
  // model.addAttribute("historyVO", historyVO);

  // List<HistoryVO> historyList = historyService.selectHistory(historyVO);
  // model.addAttribute("historyList", historyList);

  // return "common.user.history_list";
  // }
}
