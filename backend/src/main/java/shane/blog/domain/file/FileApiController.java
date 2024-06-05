package shane.blog.domain.file;

import shane.blog.domain.common.dto.MessageDto;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.file.FileUtils;
import shane.blog.domain.post.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileApiController {

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

    // 파일 리스트 조회
    @GetMapping("/file/{postId}")
    public List<FileResponse> findAllFileByPostId(@PathVariable final Long postId) {
        log.debug("/findAllFileByPostId 시작.");

        return fileApiService.findAllFileByPostId(postId);
    }

    // 첨부파일 다운로드
    @PostMapping("/file/{postId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        log.debug("/downloadFile 시작.");

        Long id = postRequest.getId();
        FileResponse file = fileApiService.findFileById(id);
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            ResponseEntity<Resource> response = ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);

            // Log the headers
            HttpHeaders headers = response.getHeaders();
            headers.forEach((key, value) -> {
                log.debug("Header '{}': {}", key, value);
            });

            return response;

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }

    // 첨부파일 삭제
    @GetMapping("/file/delete.do")
    public String deleteFile(@RequestParam final Long id) {
        boolean isDeleted = fileApiService.delete(id);
        String message = null;
        if(!isDeleted)
            message = "첨부파일 삭제에 실패했습니다.";
        else
            message = "첨부파일 삭제가 완료되었습니다.";
        return message;
    }

}
