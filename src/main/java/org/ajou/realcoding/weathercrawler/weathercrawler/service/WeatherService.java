package org.ajou.realcoding.weathercrawler.weathercrawler.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.weathercrawler.weathercrawler.api.OpenWeatherMapApiClient;
import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.weathercrawler.repsitory.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
//be에서 제공하는 메소드들
@Service
@Slf4j
public class WeatherService {
    //List<String> cities = null;
    @Autowired //objectmapper 자동으로 객채화
    ObjectMapper objectMapper;
    @Autowired
    OpenWeatherMapApiClient openWeatherMapApiClient;
    @Autowired
    private CurrentWeatherRepository currentWeatherRepository;
    //@PostConstruct
    public List<String> loadAvailablecity() throws IOException {
        File file = new File("availableCityNames");
        return objectMapper.readValue(file,new TypeReference<List<String>>(){});
    }
    /*public List<String> availablecities() throws IOException {
        File file = new File("availableCityNames");
        cities = objectMapper.readValue(file,new TypeReference<List<String>>(){});

    }*/
    public CurrentWeather getCurrentWeatherbyCitName(String cityname){

        return currentWeatherRepository.findCurrentWeather(cityname);
        //return openWeatherMapApiClient.requestCurrentWeather(cityname);
    }

    LinkedList<String> citiesqueue = new LinkedList<>();
    @Scheduled(fixedDelay = 2000L)
    public void getCurrentWeatherPeriodically() throws IOException {
        if(citiesqueue.isEmpty()){
            citiesqueue.addAll(loadAvailablecity());
        }
        String targetcity = citiesqueue.pop();
        citiesqueue.addLast(targetcity);

        CurrentWeather currentWeather = openWeatherMapApiClient.requestCurrentWeather(targetcity);
        currentWeatherRepository.insertCurrentWeather(currentWeather);
        log.info("current weather has inserted succesfully:"+ currentWeather);
    }
}
