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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  // 첨부파일 업로드할 경로를 변수값으로 가져옴. application.yaml에 빈으로 등록되어있음.
  @Value("${property.app.uploadPath}")
  private String uploadPath;

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
  public @ResponseBody MsgResponse commonCodeTranAjax(@RequestBody CommonResponse commonCode) {
    logger.debug("commonCodeTranAjax page Start");

    MsgResponse msg = new MsgResponse();
    if (commonCode.getDoTran().equals("add")) {
      msg = codeService.doInsertCode(commonCode);
    } else if (commonCode.getDoTran().equals("update")) {
      msg = codeService.doUpdateCode(commonCode);
    } else if (commonCode.getDoTran().equals("delete")) {
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

  public ArrayList<String> getCheckImgArray() {
    return checkImgArray;
  }

  public void setCheckImgArray(ArrayList<String> checkImgArray) {
    this.checkImgArray = checkImgArray;
  }

}
