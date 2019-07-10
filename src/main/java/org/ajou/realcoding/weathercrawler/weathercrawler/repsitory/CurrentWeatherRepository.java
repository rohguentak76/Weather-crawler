package org.ajou.realcoding.weathercrawler.weathercrawler.repsitory;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CurrentWeatherRepository {
    @Autowired

    private MongoTemplate mongoTemplate;

    public CurrentWeather insertCurrentWeather(CurrentWeather currentWeather){
        return mongoTemplate.save(currentWeather);
    }

    public CurrentWeather findCurrentWeather(String cityname) {
        Query query = Query.query(Criteria.where("name").is(cityname));
        return mongoTemplate.findOne(query, CurrentWeather.class);
    }
}
