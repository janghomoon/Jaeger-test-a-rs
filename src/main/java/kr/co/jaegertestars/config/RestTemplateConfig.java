package kr.co.jaegertestars.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  /**
   * Micrometer Tracing이 RestTemplateBuilder를 통해 생성된 RestTemplate에
   * 자동으로 Trace Context를 주입하는 ClientHttpRequestInterceptor를 적용합니다.
   * 이 인터셉터가 현재 Span의 Trace ID와 Span ID를 HTTP 헤더(예: traceparent)에 추가하여 B 서버로 전송합니다.
   */
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    // RestTemplateBuilder를 사용하지 않고 new RestTemplate()을 사용하면 자동 계측이 적용되지 않습니다.
    return builder.build();
  }
}
