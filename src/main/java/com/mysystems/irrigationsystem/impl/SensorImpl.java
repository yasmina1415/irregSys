package com.mysystems.irrigationsystem.impl;


import com.mysystems.irrigationsystem.controller.Controller;
import com.mysystems.irrigationsystem.facade.Sensor;
import com.mysystems.irrigationsystem.model.LandPlot;
import com.mysystems.irrigationsystem.model.TimeSlot;
import com.mysystems.irrigationsystem.repository.LandplotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Optional;



@Service
public class SensorImpl implements Sensor {

    @Autowired
    LandplotRepository landplotRepository;
    @Autowired
    Controller controller;

    public void water(Long id){
        //case1: sensor of landplot #2 needs irrigation
        Optional<LandPlot> l = landplotRepository.findById(id);
        int time = l.get().getTimeSlot();
        System.out.println("ID of plot needs irrigate:" + (l.get().getId()));
        TimeSlot task1 = new TimeSlot(time);
        //then update time slot
        l.get().setTimeSlot(10);
        controller.configureLandPlot(l.get());
    }

    @Override
    public void updateSlot(LandPlot l , int duration) throws URISyntaxException {
        String url = "http://localhost:4003/irrigationsystem/public/test";
        RestTemplate restTemplate = new RestTemplate();
        l.setTimeSlot(duration);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(l, headers);
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(url,String.class);
        System.out.println(responseEntity.getStatusCode());
    }

    @Override
    public void retry() {


        System.out.println("retried");
        alert();
    }

    @Override
    public void alert() {
        System.out.println("alerted");
    }

//    public ResponseEntity<String> test3(){
//     //   String url = "http://localhost:4003/irrigationsystem/public/test";
//      //  RestTemplate restTemplate = new RestTemplate();
////            l.setTimeSlot(duration);
////            HttpHeaders headers = new HttpHeaders();
////            headers.setContentType(MediaType.APPLICATION_JSON);
////            HttpEntity<?> requestEntity = new HttpEntity<>(l, headers);
////        ResponseEntity<String> s = restTemplate.getForEntity(url,String.class);
//        //ResponseEntity<String> s = test2;
//
//        System.out.println(s);
//        return s;
//    }

}
