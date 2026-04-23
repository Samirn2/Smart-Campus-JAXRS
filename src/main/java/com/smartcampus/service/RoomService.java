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
        rooms.add(new Room("R102","Cyber Security", 25));
        
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
}
