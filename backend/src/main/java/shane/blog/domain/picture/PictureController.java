package shane.blog.domain.picture;

import shane.blog.domain.common.dto.MessageDto;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.file.FileUtils;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.file.FileRequest;
import shane.blog.domain.file.FileResponse;
import shane.blog.domain.member.MemberResponse;
import shane.blog.domain.file.FileApiService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PictureController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PictureService pictureService;
    private final FileApiService fileService;
    private final FileUtils fileUtils;

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }


    // 쿼리 스트링 파라미터를 Map에 담아 반환
    private Map<String, Object> queryParamsToMap(final SearchDto queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }


    // 게시글 작성 페이지
    @GetMapping("/picture/write.do")
    public String pictureWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {
            PictureResponse picture = pictureService.findPictureById(id);
            model.addAttribute("picture", picture);
        }
        return "picture/write";
    }


    // 게시글 리스트 페이지
    // @GetMapping("/picture/list")
    // public String pictureList(@ModelAttribute("params") final SearchDto params, Model model) {
    //     PagingResponse<PictureResponse> response = pictureService.findAllPicture(params);
    //     model.addAttribute("response", response);
    //     return "picture/list";
    // }
    @RequestMapping(value = "/picture/list", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<PagingResponse<PictureResponse>> pictureList(@ModelAttribute("params") final SearchDto params) {

        logger.debug("/list 시작. \t {}", new Date());

        // 1. 회원 정보 조회
        PagingResponse<PictureResponse> response = pictureService.findList(params);
        logger.debug("/list 종료. \t {}", response);

        // 2. 조회 결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 게시글 상세 페이지
    @GetMapping("/picture/view.do")
    public String pictureView(@RequestParam final Long id, Model model) {
        PictureResponse picture = pictureService.findPictureById(id);
        model.addAttribute("picture", picture);
        return "picture/view";
    }


    // 신규 게시글 생성
    @PostMapping("/picture/save.do")
    public String savePicture(final PictureRequest params, Model model) {
        Long id = pictureService.savePicture(params);
        // List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());
        // fileService.saveFiles(id, files);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/picture/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }


    // 기존 게시글 수정
    @PostMapping("/picture/update.do")
    public String updatePicture(final PictureRequest params, final SearchDto queryParams, Model model) {

        // 1. 게시글 정보 수정
        pictureService.updatePicture(params);

        // // 2. 파일 업로드 (to disk)
        // List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());

        // // 3. 파일 정보 저장 (to database)
        // fileService.saveFiles(params.getId(), uploadFiles);

        // // 4. 삭제할 파일 정보 조회 (from database)
        // List<FileResponse> deleteFiles = fileService.findAllFileByIds(params.getRemoveFileIds());

        // // 5. 파일 삭제 (from disk)
        // fileUtils.deleteFiles(deleteFiles);

        // // 6. 파일 삭제 (from database)
        // fileService.deleteAllFileByIds(params.getRemoveFileIds());

        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/picture/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }


    // 게시글 삭제
    @PostMapping("/picture/delete.do")
    public String deletePicture(@RequestParam final Long id, final SearchDto queryParams, Model model) {
        pictureService.deletePicture(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/picture/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

}
