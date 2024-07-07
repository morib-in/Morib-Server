package org.sopt.jaksim.task.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.sopt.jaksim.task.domain.UserTimer;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.dto.TotalTimeTodayResponse;
import org.sopt.jaksim.task.service.TaskTimerService;
import org.sopt.jaksim.task.service.UserTimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimerApiController {

    private final TaskTimerService taskTimerService;
    private final UserTimerService userTimerService;

    @GetMapping("/timer")
    public ResponseEntity<BaseResponse<?>> getTotalTimeToday(@RequestParam("targetDate") String targetDate) {
        TotalTimeTodayResponse response = userTimerService.getTotalTimeToday(targetDate);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
    }

    @PostMapping("/timer/stop/{taskId}")
    public ResponseEntity<BaseResponse<?>> stopTimerAndFetchAccumulatedTime(@PathVariable Long taskId, @RequestBody StopTimerRequest stopTimerRequest) {
        taskTimerService.calculateTaskTimerOnStop(taskId, stopTimerRequest);
        userTimerService.calculateUserTimerOnStop(stopTimerRequest);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }
}
