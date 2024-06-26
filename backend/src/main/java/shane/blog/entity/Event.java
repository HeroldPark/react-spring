package shane.blog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Event.java
@Data
@Entity
@Table(name = "tb_event")
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long memberId;
    
    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;
    
    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;
    
    private String description;

    // getters, setters
}