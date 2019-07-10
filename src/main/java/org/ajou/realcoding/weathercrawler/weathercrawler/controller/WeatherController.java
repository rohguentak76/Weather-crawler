package org.ajou.realcoding.weathercrawler.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.weathercrawler.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
//앱과 통신
@RestController
public class WeatherController {
    @Autowired // spring에서 자동으로 객체화해주는 것 WeatherService 자동 객체화
    WeatherService weatherService;
    @GetMapping("/weather-crawler/available-cities")
    public List<String> getAvailableCities() throws IOException {
        return weatherService.loadAvailablecity();
    }
    @GetMapping("/weather-crawler/current-weathers/by-city-name/{cityname}")
    public CurrentWeather getCurrentWeather(@PathVariable String cityname){
        return weatherService.getCurrentWeatherbyCitName(cityname);
    }
}
