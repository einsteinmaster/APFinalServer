package io.swagger.api;

import de.intecelektro.ApiController;
import io.swagger.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-12T15:26:34.001Z[GMT]")
@RestController
public class IsAtCapturePointApiController implements IsAtCapturePointApi {

    private static final Logger log = LoggerFactory.getLogger(IsAtCapturePointApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public IsAtCapturePointApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> isAtCapturePointGameIdPut(@DecimalMin("1")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("gameId") String gameId,@Parameter(in = ParameterIn.DEFAULT, description = "asdf", required=true, schema=@Schema()) @Valid @RequestBody Player body) {
        return ApiController.getController().handlerIsAtCapturepoint(gameId,body);
    }

}
