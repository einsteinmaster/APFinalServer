package io.swagger.api;

import de.intecelektro.ApiController;
import io.swagger.model.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-12T15:26:34.001Z[GMT]")
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

    public ResponseEntity<Game> getGameGameIdGet(@DecimalMin("1") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("gameId") String gameId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ApiController.getController().handlerGetGame(gameId);
        }

        return new ResponseEntity<Game>(HttpStatus.NOT_IMPLEMENTED);
    }

}
