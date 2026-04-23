/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.mappers;
import com.mycompany.smartcampus.exceptions.SensorUnavailableException;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;

@Provider
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException>{
    
    @Override
    public Response toResponse(SensorUnavailableException exception){
        return Response.status(Response.Status.FORBIDDEN)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"Forbidden\"," +
                        "\"status\": 403," +
                        "\"message\": \"" + exception.getMessage() + "\"" + "}")
                .build();
               
    }
}
