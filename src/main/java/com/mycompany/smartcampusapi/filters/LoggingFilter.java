/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartcampusapi.filters;

/**
 *
 * @author samirnuur
 */

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter{
     
    @Override // START of the request
    public void filter(ContainerRequestContext requestContext) throws IOException{
        System.err.println("---------- REQUEST LOG ----------");
        System.err.println("HTTP Method: " + requestContext.getMethod());
        System.err.println("Request Path: " + requestContext.getUriInfo().getPath());
        System.err.println("---------------------------------");
    }
    
    @Override // END of the request
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException{
        System.err.println("---------- RESPONSE LOG ----------");
        System.err.println("Status Code: " + responseContext.getStatus());
        System.err.println("Status Info: " + responseContext.getStatusInfo());
        System.err.println("----------------------------------");   
    }
    
}

