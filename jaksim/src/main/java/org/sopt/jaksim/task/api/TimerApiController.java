package org.sopt.jaksim.task.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.sopt.jaksim.task.domain.UserTimer;
import org.sopt.jaksim.task.dto.StartTimerRequest;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.dto.TodoCardResponse;
import org.sopt.jaksim.task.dto.TotalTimeTodayResponse;
import org.sopt.jaksim.task.service.TaskTimerService;
import org.sopt.jaksim.task.service.TodoService;
import org.sopt.jaksim.task.service.UserTimerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimerApiController implements TimerApi {

    private final TaskTimerService taskTimerService;
    private final UserTimerService userTimerService;
    private final TodoService todoService;

    @GetMapping("/timer")
    @Override
    public ResponseEntity<BaseResponse<?>> getTotalTimeToday(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate) {
        TotalTimeTodayResponse response = userTimerService.getTotalTimeToday(targetDate);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
    }

    @PostMapping("/timer/stop/{taskId}")
    @Override
    public ResponseEntity<BaseResponse<?>> stopTimerAndFetchAccumulatedTime(@PathVariable Long taskId, @RequestBody StopTimerRequest stopTimerRequest) {
        taskTimerService.calculateTaskTimerOnStop(taskId, stopTimerRequest);
        userTimerService.calculateUserTimerOnStop(stopTimerRequest);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @PostMapping("/timer/start")
    @Override
    public ResponseEntity<BaseResponse<?>> startTimer(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate,
                                                      @RequestBody StartTimerRequest startTimerRequest) {
        todoService.startTimer(targetDate, startTimerRequest);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @GetMapping("/timer/todo-card")
    @Override
    public ResponseEntity<BaseResponse<?>> getTodoCards(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate) {
        TodoCardResponse response = todoService.getTodoCard(targetDate);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
    }
}
