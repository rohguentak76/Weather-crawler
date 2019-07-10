package org.ajou.realcoding.weathercrawler.weathercrawler.api;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//외부 api와 통신
@Service
public class OpenWeatherMapApiClient {
    private final String appid = "75bfb6faf4389eaa1ccac05133c9a8f4";
    private final String openweatherURL = "https://api.openweathermap.org/data/2.5/weather?q={cityName}&appid={appid}";
    @Autowired
    private RestTemplate restTemplate;

    public CurrentWeather requestCurrentWeather(String cityname){
        return restTemplate.exchange(openweatherURL, HttpMethod.GET, null, CurrentWeather.class,cityname, appid).getBody();
    }
}
