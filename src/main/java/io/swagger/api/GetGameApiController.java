package io.swagger.api;

import io.swagger.model.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-09T09:41:57.095Z[GMT]")
@RestController
public class GetGameApiController implements GetGameApi {

    private static final Logger log = LoggerFactory.getLogger(GetGameApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetGameApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Game> getGameGameIdGet(@DecimalMin("1")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("gameId") String gameId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Game>(objectMapper.readValue("{\n  \"score\" : 6,\n  \"id\" : \"id\",\n  \"capturePoints\" : [ {\n    \"altitude\" : 5.637376656633329,\n    \"latitude\" : 1.4658129805029452,\n    \"team\" : 2.3021358869347655,\n    \"longitude\" : 5.962133916683182\n  }, {\n    \"altitude\" : 5.637376656633329,\n    \"latitude\" : 1.4658129805029452,\n    \"team\" : 2.3021358869347655,\n    \"longitude\" : 5.962133916683182\n  } ],\n  \"timeLeft\" : 0\n}", Game.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Game>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Game>(HttpStatus.NOT_IMPLEMENTED);
    }

}
