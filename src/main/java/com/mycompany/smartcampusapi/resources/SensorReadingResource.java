/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;
import com.smartcampus.models.SensorReading;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class SensorReadingResource {
    
    private String sensorId;
    
    public SensorReadingResource(String sensorId){
        this.sensorId = sensorId;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getHistory(){
        
        List<SensorReading> history = new ArrayList<>();
        
        history.add(new SensorReading("SR-01", System.currentTimeMillis(), 22.5));
        history.add(new SensorReading("SR-02", System.currentTimeMillis() - 60000, 21.8));
        
        return history;

    }
}
