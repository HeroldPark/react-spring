package shane.blog.domain.file;

import shane.blog.domain.common.file.FileUtils;
import shane.blog.domain.post.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileApiService fileService;
    private final FileUtils fileUtils;

    // 파일 리스트 조회
    @GetMapping("/posts/{postId}/files")
    public List<FileResponse> findAllFileByPostId(@PathVariable final Long postId) {
        return fileService.findAllFileByPostId(postId);
    }

    // 첨부파일 다운로드
    @GetMapping("/posts/{postId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable final Long postId, @PathVariable final Long fileId) {
        FileResponse file = fileService.findFileById(fileId);
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/post/upload.do")
    public ResponseEntity<List<FileUploadRes>> upload (
            @RequestParam("id") Long id,
            @RequestParam("file") List<MultipartFile> files
        ) throws IOException, java.io.IOException {
        
        log.debug("FileApiController.upload: 시작");

        List<FileUploadRes> saveFile = fileService.upload(id, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveFile);
    }

    @GetMapping("/post/download.do")
    public ResponseEntity<Resource> download (
            @RequestParam("fileId") Long fileId) throws IOException, java.io.IOException {

        log.debug("FileApiController.download: 시작");

        FileDownloadRes downloadDto = fileService.download(fileId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(downloadDto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + downloadDto.getFilename() + "\"")
                .body(new ByteArrayResource(downloadDto.getContent()));
    }

    @DeleteMapping("/post/delete.do")
    public ResponseEntity<Long> delete (@RequestParam("fileId") Long fileId) {

        log.debug("FileApiController.download: 시작");

        fileService.delete(fileId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
