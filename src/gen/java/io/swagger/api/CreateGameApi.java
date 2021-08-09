package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CreateGameApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.model.Game;

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
@Path("/createGame")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen", date = "2021-07-10T11:23:36.936Z[GMT]")public class CreateGameApi  {

    @Inject CreateGameApiService service;

    @GET
    
    
    @Produces({ "application/json" })
    @Operation(summary = "", description = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "New Created Game Object", content = @Content(schema = @Schema(implementation = Game.class))) })
    public Response createGameGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.createGameGet(securityContext);
    }
}
