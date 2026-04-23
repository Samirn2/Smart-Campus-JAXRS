/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;
import com.smartcampus.models.Room;
import com.smartcampus.models.Sensor;
import com.smartcampus.service.RoomService;
import com.smartcampus.service.SensorService;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/sensors")
public class SensorResource {
    
    private final SensorService sensorService = SensorService.getInstance();
    private final RoomService roomService = RoomService.getInstance();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sensor> getAllSensors(){
        return sensorService.getAllSensors();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerSensor(Sensor newSensor){
        
        //Checking if room exists
        Room targetRoom = roomService.getRoomById(newSensor.getRoomId());
        
        //If the room doesn't exist, request will be rejected
        if(targetRoom == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Validation Has Failed\", \"reason\": \"ROOM: " + 
                            newSensor.getRoomId()+ " | Does not exist in system.\"}")
                    .build();
        }
        
        sensorService.addSensor(newSensor);
        targetRoom.getSensorIds().add(newSensor.getId());
        
        return Response.status(Response.Status.CREATED).entity(newSensor).build();
    }
    
    
}
