package org.sopt.jaksim.task.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.task.dto.StartTimerRequest;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.dto.TodoCardResponse;
import org.sopt.jaksim.task.dto.TotalTimeTodayResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Tag(name = "타이머 관련 API")
@SecurityRequirement(name = "Authorization")
public interface TimerApi {


    @Operation(
            summary = "오늘 나의 작업시간 조회 API",
            description = "사용자가 현재 날짜에 대한 자신의 작업 시간을 조회하는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "요청이 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = TotalTimeTodayResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰의 값이 올바르지 않습니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "403",
                            description = "유효하지 않은 날짜 형식입니다. yyyyMMdd 형식으로 작성해주세요.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "targetDate가 오늘 날짜가 아닙니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "405",
                            description = "잘못된 HTTP method 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류입니다.",
                            content = @Content)})
    ResponseEntity<BaseResponse<?>> getTotalTimeToday(@Parameter(hidden = true)
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate);

    @Operation(
            summary = "타이머 정지 액션 API",
            description = "타이머 정지 액션 시, 해당 태스크의 경과 시간을 보내주는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "요청이 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰의 값이 올바르지 않습니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "대상을 찾을 수 없습니다",  //
                            content = @Content),
                    @ApiResponse(
                            responseCode = "405",
                            description = "잘못된 HTTP method 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류입니다.",
                            content = @Content)})
    ResponseEntity<BaseResponse<?>> stopTimerAndFetchAccumulatedTime(@PathVariable Long taskId, @RequestBody StopTimerRequest stopTimerRequest);

    @Operation(
            summary = "할일 추가 후 타이머 시작 API",
            description = "타이머로 진입할 때, 오늘 할일을 추가해주는 API입니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "요청이 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰의 값이 올바르지 않습니다.",
                            content = @Content),

                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류입니다.")})
    ResponseEntity<BaseResponse<?>> startTimer(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate,
                                                      @RequestBody StartTimerRequest startTimerRequest);

    @Operation(
            summary = "타이머 내 할 일 카드 리스트 조회 API",
            description = "타이머 내에서 할일 카드 정보를 가져오는 API입니다.",

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "요청이 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = TodoCardResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "401",
                            description = "액세스 토큰의 값이 올바르지 않습니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "대상을 찾을 수 없습니다",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "405",
                            description = "잘못된 HTTP method 요청입니다.",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류입니다.")})
    ResponseEntity<BaseResponse<?>> getTodoCards(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate);
}
