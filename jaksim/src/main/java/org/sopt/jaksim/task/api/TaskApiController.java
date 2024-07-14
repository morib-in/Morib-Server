package org.sopt.jaksim.task.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.exception.IOException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.dto.FetchTitleRequest;
import org.sopt.jaksim.task.dto.FetchTitleResponse;
import org.sopt.jaksim.task.dto.TaskCreateRequest;
import org.sopt.jaksim.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

import static org.sopt.jaksim.global.exception.IOException.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TaskApiController implements TaskApi {
    private final TaskService taskService;

    @GetMapping("/fetch-title")
    @Override
    public ResponseEntity<BaseResponse<?>> fetchTitle(@RequestParam("requestUrl") String requestUrl) {
        try {
            Document doc = Jsoup.connect(requestUrl).get();
            FetchTitleResponse response = FetchTitleResponse.of(doc.title());
            return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
        } catch (IOException | java.io.IOException e) {
            throw new IOException(ErrorMessage.INVALID_URL);
        }
    }

    @PostMapping("/categories/{categoryId}")
    @Override
    public ResponseEntity<BaseResponse<?>> create(@PathVariable("categoryId") Long categoryId,
                                                  @RequestBody TaskCreateRequest taskCreateRequest) {
        taskService.create(categoryId, taskCreateRequest);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @PatchMapping("/tasks/{taskId}/status")
    @Override
    public ResponseEntity<BaseResponse<?>> toggleTaskStatus(@PathVariable("taskId") Long taskId) {
        taskService.toggleTaskCompletionStatus(taskId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

}
