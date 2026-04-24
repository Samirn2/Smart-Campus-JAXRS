/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;
import com.mycompany.smartcampus.models.SensorReading;
import com.mycompany.smartcampus.service.SensorService;
import com.mycompany.smartcampus.exceptions.SensorUnavailableException;
import com.mycompany.smartcampus.models.Sensor;

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
        
        Sensor sensor = sensorService.getSensorById(sensorId);
        
        if(sensor != null && "MAINTENANCE".equalsIgnoreCase(sensor.getStatus())){
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"error\":\"Forbidden: Sensor [" + sensorId + "] is in MAINTENANCE.\"}")
                    .build();
        }
        //Saving the reading into the history
        sensorService.addReading(sensorId, newReading);
        
        return Response.status(Response.Status.CREATED)
                .entity(newReading)
                .build();
    }
}
