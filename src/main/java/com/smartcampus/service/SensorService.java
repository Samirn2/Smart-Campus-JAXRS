/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.service;
import com.smartcampus.models.Sensor;
import com.smartcampus.models.SensorReading;

/**
 *
 * @author samirnuur
 */

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class SensorService {
    
    private static SensorService instance;
    private final List<Sensor> sensors = new ArrayList<>();
    
    private final Map<String, List<SensorReading>> readingHistory = new HashMap<>();
    
    private SensorService(){
        
    }
    
    public static synchronized SensorService getInstance() {
        if(instance == null){
            instance = new SensorService();
        }
        return instance;
    }
    
    public List<Sensor> getAllSensors(){
        return sensors;
    }
    
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }
    
    public List<SensorReading> getReadingForSensor(String sensorId){
        return readingHistory.getOrDefault(sensorId, new ArrayList<>());
    }
    
    //Finds a specifc sensor or returns null if not present
    public Sensor getSensorById(String id) {
        return sensors.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    // Saves reading and updates the parent sensors value 
    public void addReading(String sensorId, SensorReading reading){
        
        List <SensorReading> readings = readingHistory.computeIfAbsent(sensorId, s -> new ArrayList<>());
        readings.add(reading);
        
        Sensor sensor = getSensorById(sensorId);
        if(sensor != null){
            sensor.setCurrentValue(reading.getValue());
        }
    }
    
    
}
