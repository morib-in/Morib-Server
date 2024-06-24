package org.sopt.jaksim.global.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Component
@Order(1)
public class LoggingFilter extends AbstractRequestLoggingFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // 로깅 제외 목록 생성
        Set<String> excludedUserAgents = new HashSet<>();
        excludedUserAgents.add("ELB-HealthChecker/2.0");

        try {
            super.doFilterInternal(requestWrapper, responseWrapper, filterChain);
        } finally {
            String userAgent = request.getHeader("User-Agent");
            // 로깅 제외 목록을 제외한 나머지 정보 로깅
            if(!excludedUserAgents.contains(userAgent)){
                String method = requestWrapper.getMethod();
                String uri = requestWrapper.getRequestURI();

                // Log the method and URI
                logger.info("Request: Method: {}, URI: {}", method, uri);

                // Log all headers
                Enumeration<String> headerNames = requestWrapper.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    String headerValue = requestWrapper.getHeader(headerName);
                    logger.info("Request: Header: {} = {}", headerName, headerValue);
                }

                logger.info("Request IP : {}", request.getRemoteAddr());
                String requestBody = new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
                logger.info("Request Body: {}", requestBody);

                byte[] responseBytes = responseWrapper.getContentAsByteArray();
                if (responseBytes.length > 0) {
                    String responseBody = new String(responseBytes, StandardCharsets.UTF_8);
                    String contentType = responseWrapper.getContentType(); // contentType을 가져옵니다.

                    try {
                        if (contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                            // JSON 형식 처리
                            Object json = mapper.readValue(responseBody, Object.class);
                            String prettyResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                            logger.info("Response Body (JSON): {}", prettyResponse);
                        } else if (contentType != null && contentType.contains("text/calendar")) {
                            // text/calendar 형식 처리
                            logger.info("Response Body (Calendar): {}", responseBody);
                        } else {
                            // 그 외 형식 처리
                            logger.info("Response Body (Other): {}", responseBody);
                        }
                    } catch (Exception e) {
                        logger.error("Error occurred while trying to process the response: ", e);
                        logger.info("Response Body (unformatted): {}", responseBody);
                    }
                }

                int responseStatus = responseWrapper.getStatus();
                logger.info("Response Status: {}", responseStatus);
                responseWrapper.copyBodyToResponse();
            }
            else {
                logger.info("{} Log info is excluded",userAgent);
            }

        }
    }
}
