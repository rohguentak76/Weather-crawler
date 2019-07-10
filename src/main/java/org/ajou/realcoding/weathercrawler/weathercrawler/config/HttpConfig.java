package org.ajou.realcoding.weathercrawler.weathercrawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //spring에 객체화 요청
public class HttpConfig {

    @Bean // new는 한번만 수행하고 Spring container안에 보관||| 어떤 class에서라도 field로선언하고 autowired를통해 호출하겠다.
    public RestTemplate creatRestTemplate(){
        return new RestTemplate();
    }
}
