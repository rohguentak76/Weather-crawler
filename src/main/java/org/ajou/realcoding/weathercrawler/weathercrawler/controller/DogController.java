package org.ajou.realcoding.weathercrawler.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.Dog;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //객체화 해서 자동으로 context에 저장
public class DogController {
    List<Dog> dogs = new ArrayList<>();
    @PostMapping("/dogs")
    public Dog creatDog(@RequestBody Dog dog){
        dogs.add(dog);
        return dog;
    }

    /* @GetMapping("/dogs/{name}")
     public Dog finddog(@PathVariable String name){
         for (Dog dog : dogs) {
             if (dog.getName().equals(name)) {
                 return dog;
             }
         }
         return null;
   }*/
     @GetMapping("/dogs")
     public Dog finddog(@RequestParam String name, @RequestParam String kind){ //request parameter
         for (Dog dog : dogs) {
             if (dog.getName().equals(name)) {
                 return dog;
             }
         }
         return null;
     }
    @PutMapping("/dogs/{name}")
    public Dog updatedog(@RequestBody Dog dog){
        for (int i =0; i<dogs.size() ; i++) {
            if (dogs.get(i).getName().equals(dog.getName())) {
                dogs.get(i).setKind(dog.getKind());
                return dog;
            }
            else if(dogs.get(i).getKind().equals(dog.getKind())){
                dogs.get(i).setName(dog.getName());
                return dog;
            }
        }
        return null;
    }
    /*@DeleteMapping("/dogs/{name}")
    public void deletedog(@PathVariable String name){
         for(int i =0;i<dogs.size();i++) {
             if (dogs.get(i).getName().equals(name)) {
                 dogs.remove(i);
             }
         }
    }*/
    @DeleteMapping("/dogs")
    public void deletedog(@RequestParam String name){
        for(int i =0;i<dogs.size();i++) {
            if (dogs.get(i).getName().equals(name)) {
                dogs.remove(i);
            }
        }
    }
}
