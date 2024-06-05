package shane.blog.domain.post;

import shane.blog.domain.common.dto.MessageDto;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.file.FileUtils;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.feedback.FeedbackRequest;
import shane.blog.domain.feedback.FeedbackResponse;
import shane.blog.domain.feedback.FeedbackService;
import shane.blog.domain.file.FileRequest;
import shane.blog.domain.file.FileResponse;
import shane.blog.domain.member.MemberResponse;
import shane.blog.dto.request.board.BoardUpdateDto;
import shane.blog.dto.request.board.BoardWriteDto;
import shane.blog.dto.request.board.SearchData;
import shane.blog.dto.response.board.ResBoardDetailsDto;
import shane.blog.dto.response.board.ResBoardListDto;
import shane.blog.dto.response.board.ResBoardWriteDto;
import shane.blog.entity.Member;
import shane.blog.service.BoardService;
import shane.blog.domain.file.FileApiService;
import lombok.RequiredArgsConstructor;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PostService postService;
    private final FeedbackService feedbackService;
    private final FileApiService fileApiService;
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

    // 게시글 리스트 페이지
    // @GetMapping("/list")
    // public String postList(@ModelAttribute("params") final SearchDto params, Model model) {
    //     PagingResponse<PostResponse> response = postService.findAllPost(params);
    //     model.addAttribute("response", response);
    //     return "post/list";
    // }
    @RequestMapping(value = "/list.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<PagingResponse<PostResponse>> postList(@ModelAttribute("params") final SearchDto params) {

        logger.debug("/list.do 시작. \t {}", new Date());

        // 1. 회원 정보 조회
        PagingResponse<PostResponse> response = postService.findList(params);
        logger.debug("/list.do 종료. \t {}", response);

        // 2. 조회 결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // // 게시글 상세 페이지
    // @GetMapping("/{id}")
    // public String postDetail(@RequestParam final Long id, Model model) {
    //     PostResponse post = postService.findPostById(id);
    //     model.addAttribute("post", post);
    //     return "post/view";
    // }

    // 게시글 작성 페이지
    @PostMapping("/write.do")
    public ResponseEntity<?> postWrite(
            @RequestBody PostRequest postRequest,
            @AuthenticationPrincipal Member member) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());
        Long id = postService.write(postRequest, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    // 게시글 상세 페이지
    @GetMapping("/detail.do/{id}")
    public ResponseEntity<PostResponse> postDetail(@PathVariable("id") Long id) {
        // PostList.getPostDetail()에서 이렇게 호출한다.
        // @GetMapping("/detail.do")
        // public ResponseEntity<PostResponse> postDetail(@ModelAttribute("params") final SearchDto params) {
        
        log.info("PostController.postDetail() : {}", id);

        PostResponse postResponse = postService.detail(id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }

    // 게시글 상세, 답글쓰기
    @PostMapping("/answer.do")
    public ResponseEntity<List<FeedbackResponse>> postAnswer(@RequestBody PostRequest postRequest) {
        // public ResponseEntity<PostResponse> postAnswer(@PathVariable("parentSeq") Long parentSeq) {
       
        log.info("PostController.postAnswer() : {}", postRequest);

        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setPostId(postRequest.getId());
        feedbackRequest.setTitle(postRequest.getTitle());
        feedbackRequest.setContent(postRequest.getContent());
        feedbackRequest.setWriter(postRequest.getWriter());

        List<FeedbackResponse> feedbackResponse = feedbackService.write2(feedbackRequest);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackResponse);
    }

    // // 상세보기 -> 수정
    // @PatchMapping("/update.do")
    // public ResponseEntity<Long> update(
    //         // @PathVariable Long id,
    //         @RequestBody PostRequest postRequest) {

    //     log.info("PostController.update() : {}", postRequest);

    //     // postRequest.setId(id);
    //     Long id = postService.update(postRequest);
    //     return ResponseEntity.status(HttpStatus.OK).body(id);
    // }

    // 상세보기 -> 삭제
    @DeleteMapping("/{id}/delete.do")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            postService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception e) {
            String errorMessage = "Failed to delete post with id: " + id + ". " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    
    // // 게시글 삭제 실패 시 오류 원인을 front로 전달하는 메서드
    // @ExceptionHandler(NotFoundException.class)
    // public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }
    
    // 신규 게시글 생성
    @PostMapping("/save.do")
    public String savePost(final PostRequest params, @RequestPart("multipartFiles") MultipartFile[] multipartFiles, Model model) {
        Long id = postService.savePost(params);
        // List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(multipartFiles);
        fileApiService.saveFiles(id, uploadFiles);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 기존 게시글 수정
    @PostMapping("/update.do")
    public ResponseEntity<PagingResponse<PostResponse>> updatePost(
        @RequestPart("params") PostRequest params,
        @RequestPart("multipartFiles") MultipartFile[] multipartFiles,
        final SearchDto queryParams,
        Model model) {

        log.info("PostController.updatePost() : {}", params);

        // 1. 게시글 정보 수정
        postService.update(params);

        // 2. 파일 업로드 (to disk)
        // List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(multipartFiles);

        // 3. 파일 정보 저장 (to database)
        fileApiService.saveFiles(params.getId(), uploadFiles);

        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileResponse> deleteFiles = fileApiService.findAllFileByIds(params.getRemoveFileIds());

        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);

        // 6. 파일 삭제 (from database)
        fileApiService.deleteAllFileByIds(params.getRemoveFileIds());

        // MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        // return showMessageAndRedirect(message, model);
        SearchDto params2 = new SearchDto();
        params2.setPage(1);
        params2.setRecordSize(10);
        params2.setPageSize(10);
        params2.setKeyword("");
        params2.setId(params.getId());

        PagingResponse<PostResponse> response = postService.findList(params2);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/upload.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<PagingResponse<PostResponse>> postUpload(@ModelAttribute("params") final SearchDto params) {

        logger.debug("/upload.do 시작. \t {}", new Date());

        // 1. 회원 정보 조회
        PagingResponse<PostResponse> response = postService.findList(params);
        logger.debug("/upload.do 종료. \t {}", response);

        // 2. 조회 결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 게시글 삭제
    @GetMapping("/delete.do")
    public String deletePost(@RequestParam final Long id, final SearchDto queryParams, Model model) {
        fileApiService.delete(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

}