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

import java.util.List;
import java.util.Map;
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
        T responseEntity = entityService.createEntity(entity);
        String message = "Entity created successfully.";
        APIResponse<T>  apiResponse = new APIResponse(message,  HttpStatus.CREATED.value(), responseEntity);
        LOGGER.debug(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
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
        T responseEntity = entityService.updateEntity(id, entity);
        String message;
        if (Objects.isNull(responseEntity)) {
            message = "Entity with id "+id+" not found in the database.";
            LOGGER.debug(message);
            APIResponse response = new APIResponse<>(message, HttpStatus.BAD_REQUEST.value(), message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        message = "Entity Updated successfully.";
        APIResponse response = new APIResponse(message, HttpStatus.OK.value(), responseEntity);
        LOGGER.debug(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Partial update entity with the given id and the updates.", response = APIResponse.class)
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = APIResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<APIResponse> updatePartialEntity(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> updates) {

        LOGGER.debug("Inside updatePartialEntity with id "+id+" and updates "+updates);
        T responseEntity = entityService.partiallyUpdateEntity(id, updates);
        if (Objects.isNull(responseEntity)) {
            String message = "Entity with id "+id+" not found in the database.";
            LOGGER.debug(message);
            APIResponse response = new APIResponse<>(message, HttpStatus.BAD_REQUEST.value(), message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        APIResponse response = new APIResponse("Entity partially updated", HttpStatus.OK.value(), responseEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        entityService.deleteEntity(id);
        String message = "Entity deleted successfully";
        APIResponse response = new APIResponse(message, HttpStatus.OK.value(), message);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        T responseEntity = entityService.getEntity(id);

        if (Objects.isNull(responseEntity)) {
            String message = "Entity with id "+id+" not found in the database.";
            LOGGER.debug(message);
            APIResponse response = new APIResponse<>(message, HttpStatus.BAD_REQUEST.value(), message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        String message = "Entity "+responseEntity +" retrieved successfully.";
        LOGGER.debug(message);
        APIResponse response = new APIResponse<>(message, HttpStatus.OK.value(), responseEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
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

        LOGGER.debug("Inside displayEntity all entity");
        List<T> responseEntity = entityService.getAllEntities();

        if (Objects.isNull(responseEntity) || responseEntity.isEmpty()) {
            String message = "Entity not found in the database.";
            LOGGER.debug(message);
            APIResponse response = new APIResponse<>(message, HttpStatus.BAD_REQUEST.value(), message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        String message = "Entity "+responseEntity +" retrieved successfully.";
        LOGGER.debug(message);
        APIResponse response = new APIResponse<>(message, HttpStatus.OK.value(), responseEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
