package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.IsAtCapturePointApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.model.Player;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.inject.Inject;

import javax.validation.constraints.*;
@Path("/isAtCapturePoint")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen", date = "2021-07-10T11:23:36.936Z[GMT]")public class IsAtCapturePointApi  {

    @Inject IsAtCapturePointApiService service;

    @PUT
    @Path("/{gameId}")
    @Consumes({ "application/json" })
    
    @Operation(summary = "", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Update Ok"),
        
        @ApiResponse(responseCode = "400", description = "Update not Ok") })
    public Response isAtCapturePointGameIdPut(@Parameter(description = "asdf" ,required=true) Player body, @Min(1) @PathParam("gameId") Integer gameId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.isAtCapturePointGameIdPut(body,gameId,securityContext);
    }
}
