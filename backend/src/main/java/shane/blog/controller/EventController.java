package shane.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import shane.blog.dto.request.event.EventCreateRequest;
import shane.blog.dto.request.event.EventListRequest;
import shane.blog.entity.Event;
import shane.blog.repository.EventRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// 온라인 예약 관리 시스템
@Slf4j
@RestController
@RequestMapping("/online")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @PostMapping("/list")
    public List<Event> getList(@RequestBody EventListRequest params) {
        log.debug("EventController.getEvents() start");

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        LocalDate startDate = LocalDate.parse(params.getStartTime(), formatter);
        LocalDate endDate = LocalDate.parse(params.getEndTime(), formatter);
        
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        
        return eventRepository.findByStartTimeBetween(startDateTime, endDateTime);
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody EventCreateRequest request) {
        log.debug("EventController.createEvent() start");

        Event event = new Event();
        event.setTitle(request.getTitle());
        // event.setStart(LocalDateTime.parse(request.getStart(), DateTimeFormatter.ISO_DATE_TIME));
        // event.setEnd(LocalDateTime.parse(request.getEnd(), DateTimeFormatter.ISO_DATE_TIME));
        LocalDate startDate = LocalDate.parse(request.getStartTime(), formatter);
        LocalDate endDate = LocalDate.parse(request.getEndTime(), formatter);
        
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        event.setStartTime(startDateTime);
        event.setEndTime(endDateTime);
        event.setDescription(request.getDescription());

        return eventRepository.save(event);
    }

    // 수정, 삭제 메서드 추가
}