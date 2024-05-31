package shane.blog.domain.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadRes {
    
    private Long fileId;
    private String originFileName;
    private String filePath;
    private String fileType;

}
