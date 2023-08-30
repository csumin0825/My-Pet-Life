package com.example.mypetlife.controller.calendar;

import com.example.mypetlife.dto.MessageResponse;
import com.example.mypetlife.dto.calendar.alarm.AlarmScheduleListDto;
import com.example.mypetlife.dto.calendar.schedule.*;
import com.example.mypetlife.service.calendar.CalendarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public MessageResponse create(HttpServletRequest request, @Valid @RequestBody ScheduleRequestDto dto)
            throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        calendarService.create(request, dto);
        return responseDto("일정이 등록되었습니다.");
    }

    @GetMapping("/readList")
    public List<ScheduleListResponseDto> readDate(HttpServletRequest request, @RequestParam("date") String date) {
        return calendarService.readDateSchedule(request, date);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto readSchedule(HttpServletRequest request, @PathVariable("scheduleId") Long scheduleId){
        return calendarService.readSchedule(request, scheduleId);
    }

    @GetMapping("/readall")
    public List<ScheduleAllListResponseDto> readAll(HttpServletRequest request) {
        return calendarService.readAllSchedule(request);
    }

    @GetMapping("/readall/alarm")
    public List<AlarmScheduleListDto> readAllAlarmSchedule(HttpServletRequest request) {
        return calendarService.readAllAlarmSchedule(request);
    }

    @PutMapping("/{scheduleId}")
    public MessageResponse updateSchedule(HttpServletRequest request, @PathVariable("scheduleId") Long scheduleId, @RequestBody UpdatedScheduleDto dto){
        calendarService.updateSchedule(request, scheduleId, dto);
        return responseDto("수정이 완료되었습니다.");
    }

    @DeleteMapping("/{scheduleId}")
    public MessageResponse deleteSchedule(HttpServletRequest request, @PathVariable("scheduleId") Long scheduleId) {
        calendarService.deleteSchedule(request, scheduleId);
        return responseDto("삭제가 완료되었습니다.");
    }

    public MessageResponse responseDto(String message) {
        MessageResponse responseDto = new MessageResponse(message);
        return responseDto;
    }
}