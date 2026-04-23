/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.mappers;
import com.mycompany.smartcampus.exceptions.RoomNotEmptyException;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException>{
    
    @Override
    public Response toResponse(RoomNotEmptyException exception){
        return Response.status(Response.Status.CONFLICT)
                .type(MediaType.APPLICATION_JSON)
                .entity("{" + "\"error\": \"Conflict\"," + "\"status\": 409," +
                        "\"message\": \"Room is currently occupied by active hardware.\"" + "}")
                .build();
    }
}
