package com.mysystems.irrigationsystem.facade;


import com.mysystems.irrigationsystem.model.LandPlot;

import java.net.URISyntaxException;

public interface Sensor {


    void updateSlot(LandPlot l, int duration) throws URISyntaxException;

    void retry();

    void alert();
}
