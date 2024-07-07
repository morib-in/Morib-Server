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
import org.sopt.jaksim.task.dto.FetchTitleRequest;
import org.sopt.jaksim.task.dto.FetchTitleResponse;
import org.sopt.jaksim.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

import static org.sopt.jaksim.global.exception.IOException.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TaskApiController {
    private final TaskService taskService;

    @GetMapping("/fetch-title")
    public ResponseEntity<BaseResponse<?>> fetchTitle(@RequestParam("requestUrl") String requestUrl) {
        try {
            Document doc = Jsoup.connect(requestUrl).get();
            FetchTitleResponse response = FetchTitleResponse.of(doc.title());
            return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
        } catch (IOException | java.io.IOException e) {
            throw new IOException(ErrorMessage.INVALID_URL);
        }
    }
}
