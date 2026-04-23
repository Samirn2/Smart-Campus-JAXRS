/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.service;

import com.smartcampus.models.Room;

/**
 *
 * @author samirnuur
 */

import java.util.ArrayList;
import java.util.List;


public class RoomService {
    
    private static RoomService instance;
    private final List<Room> rooms = new ArrayList<>();
    
    private RoomService() {
        rooms.add(new Room("R101","Computing Lab", 40));
        
        Room room2 = new Room("R102","Cyber Security", 25);
        room2.getSensorIds().add("SN-102");
        rooms.add(room2);
        
    }
    
    public static synchronized RoomService getInstance() {
        if(instance == null) {
            instance = new RoomService();
        }
        return instance;
    }
    
    public List<Room> getAllRooms(){
        return rooms;
    }
    
    public void addRoom(Room room){
        rooms.add(room);
    }
    
    public Room getRoomById(String id) {
        return rooms.stream()
                .filter(r -> r.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
    
    public String deleteRoom(String id) {
        Room room = getRoomById(id);
        
        // Checking if the room even  exist
        if(room == null) {
            return "ROOM_NOT_FOUND";
        }
        
        // Check if room has any sensors assigned to it
        if(room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
            return "HAS_SENSORS";
        }
        
        rooms.remove(room);
        return "SUCCESSFUL";
    }
}
