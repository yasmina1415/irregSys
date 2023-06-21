package com.mysystems.irrigationsystem.controller;


import com.mysystems.irrigationsystem.impl.SensorImpl;
import com.mysystems.irrigationsystem.model.LandPlot;
import com.mysystems.irrigationsystem.repository.LandplotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("irrigationsystem/public")
public class Controller {

    @Autowired
    LandplotRepository landplotRepository;
    @Autowired
    SensorImpl sensor;


    @PostMapping("/add")
    public ResponseEntity<?> addLandPlot(@RequestBody LandPlot landPlot){
         try {
             landplotRepository.save(landPlot);
             return  ResponseEntity.status(HttpStatus.OK).build();
         }catch(Exception e){
             e.printStackTrace();
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
    @PostMapping("/configure")
    public ResponseEntity<?> configureLandPlot(@RequestBody LandPlot landPlot){
        try {
            landplotRepository.configureTime(landPlot.getTimeSlot(), landPlot.getId());
            return  ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
    @PostMapping("/edit")
    public ResponseEntity<?> editLandPlot(@RequestBody LandPlot landPlot){
        try {

            landplotRepository.editLandPlot(landPlot.getWaterAmount(), landPlot.getTimeSlot(), landPlot.getId());
            return  ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
    @PostMapping("/list")
    public ResponseEntity<?> listAllLandPlot(){
        try {

            List<LandPlot> landPlots =  landplotRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(landPlots);
        }catch(Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
    @PostConstruct
    @GetMapping("/init")
    public void init() throws URISyntaxException {
        //case1 sensor discover land #2 needs irrigation
        sensor.water(2L);
        //add new landPlot
        LandPlot lnew = new LandPlot();
        lnew.setTimeSlot(6);
        lnew.setWaterAmount(4.5F);
        addLandPlot(lnew);
        //retry
        sensor.retry();
    }




}
