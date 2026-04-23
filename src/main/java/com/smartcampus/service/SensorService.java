/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.service;
import com.smartcampus.models.Sensor;

/**
 *
 * @author samirnuur
 */

import java.util.ArrayList;
import java.util.List;

public class SensorService {
    
    private static SensorService instance;
    private final List<Sensor> sensors = new ArrayList<>();
    
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
    
}
