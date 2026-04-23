/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;
import com.smartcampus.models.SensorReading;
import com.smartcampus.service.SensorService;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

public class SensorReadingResource {
    
    private String sensorId;
    private final SensorService sensorService = SensorService.getInstance();
    
    public SensorReadingResource(String sensorId){
        this.sensorId = sensorId;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getHistory(){
        //Has been changed to now fetch saved data in the service for sensor    
        return sensorService.getReadingForSensor(sensorId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading newReading){
        
        //Saving the reading into the history
        sensorService.addReading(sensorId, newReading);
        
        return Response.status(Response.Status.CREATED)
                .entity(newReading)
                .build();
    }
}
