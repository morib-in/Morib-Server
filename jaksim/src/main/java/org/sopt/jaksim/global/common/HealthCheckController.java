package org.sopt.jaksim.global.common;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    private final Environment env;
    private static final String NULL = "";

    @GetMapping("/profile")
    @Operation(summary = "active profile 확인 API", description = "active profile을 확인하는 일종의 Health Check API입니다.")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse(NULL);
    }

}
