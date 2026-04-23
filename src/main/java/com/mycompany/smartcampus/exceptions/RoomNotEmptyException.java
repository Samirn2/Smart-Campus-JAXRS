/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampus.exceptions;

/**
 *
 * @author samirnuur
 */

// A custom exception for rooms with sensors that cannot be deleted
public class RoomNotEmptyException extends RuntimeException {
    public RoomNotEmptyException(String message){
        super(message);
    }
}
