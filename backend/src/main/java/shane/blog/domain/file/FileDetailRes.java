package shane.blog.domain.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDetailRes {
    
    private Long fileId;
    private String originFileName;
    private String fileType;
}
