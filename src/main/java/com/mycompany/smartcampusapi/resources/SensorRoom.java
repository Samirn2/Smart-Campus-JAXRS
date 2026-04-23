/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.resources;

import com.smartcampus.models.Room;
import com.smartcampus.service.RoomService;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/rooms")
public class SensorRoom {
    
    private final RoomService roomService = RoomService.getInstance();
    
    // Returning all rooms as JSON array
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
    
    //Adds a new room
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room newRoom) {
        if(newRoom == null || newRoom.getId() == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Room data is incomplete\"}")
                    .build();
        }
    
    roomService.addRoom(newRoom);
    
    return Response.status(Response.Status.CREATED)
            .entity(newRoom)
            .build();
    }

    //Fetching rooms specifically based on roomId
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoomId(@PathParam("roomId") String roomId){
        Room room = roomService.getRoomById(roomId);
        
        if(room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Room not found\"}")
                    .build();
        }
        
        return Response.ok(room).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        String result = roomService.deleteRoom(roomId);
        
        switch(result){
            case "SUCCESSFUL":
                return Response.ok("{\"message\": \"Room successfully deleted\"}").build();
            case "HAS_SENSORS":
                //Specifc saftey logic for 2.2
                return Response.status(Response.Status.CONFLICT)
                        .entity("\"error\": \"Deletion blocked\", \"reason\": \"Room contains sensors.\"}")
                        .build();
            case "ROOM_NOT_FOUND":
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Room not found\"}")
                        .build();
            default:
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
       


            
}   

    



