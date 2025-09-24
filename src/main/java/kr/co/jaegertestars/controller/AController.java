package kr.co.jaegertestars.controller;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class AController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Tracer tracer = GlobalOpenTelemetry.getTracer("kr.co.jaeger-test");
    // B 서비스의 특정 엔드포인트를 호출하는 메서드
    // 예: /b-service-endpoint
    // 실제 엔드포인트 URL로 변경 필요
    // 예시로 GET 요청을 보내는 메서드 작성
    // 필요에 따라 POST, PUT 등 다른 HTTP 메서드로 변경 가능
    // 또한, 요청에 필요한 헤더나 바디가 있다면 추가로 설정해야 함
    // 예시에서는 단순히 GET 요청을 보내고 응답을 문자열로 받음
    // 실제 사용 시에는 응답 타입에 맞게 변경 필요
    @GetMapping("/call-b-service")
    public String callBService() {
        Span span = tracer.spanBuilder("hello-handler").startSpan();


        try (var scope = span.makeCurrent()) {
            String bServiceUrl = "http://localhost:8081/world"; // B 서비스의 실제 URL로 변경
            log.info("Calling B service at URL: {}", bServiceUrl);
            return restTemplate.getForObject(bServiceUrl, String.class);
//            // 실제 로직
//            log.info("B service /world endpoint called");
//            return "Hello World";
        } finally {
            span.end();
        }
    }

}
