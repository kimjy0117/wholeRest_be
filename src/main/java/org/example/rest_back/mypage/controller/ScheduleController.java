package org.example.rest_back.mypage.controller;

import lombok.AllArgsConstructor;
import org.example.rest_back.mypage.dto.ScheduleDto;
import org.example.rest_back.mypage.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    //get all schedules
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getAllSchedules(){
        List<ScheduleDto> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    //get schedule by calendarId
    @GetMapping("/{memberId}")
    public ResponseEntity<List<ScheduleDto>> getScheduleByMemberId(@PathVariable String memberId){
        List<ScheduleDto> schedules = scheduleService.getSchedulesByMemberId(memberId);
        return ResponseEntity.ok(schedules);
    }

    //get schedule by date
    @GetMapping("/date")
    public ResponseEntity<List<ScheduleDto>> getScheduleByMemberIdAndDate(@RequestParam String memberId, @RequestParam int year, @RequestParam int month){
        List<ScheduleDto> schedules = scheduleService.getSchedulesByMemberIdAndDate(memberId, year, month);
        return ResponseEntity.ok(schedules);
    }

    //create schedule
    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.createSchedule(scheduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //patch schedule
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable int scheduleId, @RequestBody ScheduleDto scheduleDto){
        scheduleService.updateSchedule(scheduleId, scheduleDto);
        return ResponseEntity.ok().build();
    }

    //delete schedule
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok().build();
    }
}
