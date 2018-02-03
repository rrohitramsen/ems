package com.ems.api;

import com.ems.model.BasicEntity;
import com.ems.service.EntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by rohitkumar on 28/01/18.
 */
@RestController
@RequestMapping("/entities")
@Api(value="Entity Management System API", description="CRUD operations on Entity.")
public class EntityController<T extends BasicEntity> {

    private static final Logger LOGGER = Logger.getLogger(EntityController.class);

    @Autowired
    private EntityService<T> entityService;

    @ApiOperation(value = "Create entity.", response = APIResponse.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> createEntity(@RequestBody T entity) {

        LOGGER.debug("Inside createEntity with param "+entity);

        APIResponse<T> apiResponse;
        ResponseEntity<APIResponse> response;

        if (Objects.isNull(entity)) {
            String message = "Entity is null, Please provide valid value";
            LOGGER.debug(message);
            apiResponse = new APIResponse(message, entity);
            response = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            return response;
        }

        BasicEntity responseEntity = entityService.createEntity(entity);
        String message = "Entity"+responseEntity+" created successfully.";
        LOGGER.debug(message);
        apiResponse = new APIResponse(message, responseEntity);
        response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        return response;
    }

    @ApiOperation(value = "Update entity with the given id.", response = APIResponse.class)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> updateEntity(@PathVariable(value = "id") long id, @RequestBody T entity) {

        LOGGER.debug("Inside updateEntity with param "+entity);
        T result = entityService.updateEntity(id, entity);
        APIResponse response = new APIResponse("Entity Updated",result);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete entity with the given id.", response = APIResponse.class)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> deleteEntity(@PathVariable(value = "id") long id) {

        LOGGER.debug("Inside deleteEntity with param "+id);
        return null;
    }

    @ApiOperation(value = "Display entity with the given id.", response = APIResponse.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> displayEntity(@PathVariable(value = "id") long id) {

        LOGGER.debug("Inside displayEntity with param "+id);
        return null;
    }

    @ApiOperation(value = "List all entities.", response = APIResponse.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> displayAllEntity() {

        LOGGER.debug("Inside displayAllEntity with param ");
        return null;
    }

}
