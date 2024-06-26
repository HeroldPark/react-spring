package shane.blog.dto.request.event;

import lombok.Data;

@Data
public class EventCreateRequest {
    private String title;
    private String startTime;
    private String endTime;
    private String description;
    // getters and setters
}