# Generic entity management system rest API end points supporting POST, GET, PUT, PATCH, DELETE.

## Requirement
Create a simple entity management system that provides a REST endpoint to manage any entity (e.g. a product in a catalog, patient information in healthcare etc.).

The system should allow for the following capabilities:

1.	It should be able to adapt to any kind of entity with minimal code changes
2.	Adding, removing, modifying the attributes of an entity should be simple

## Benifits
1. Reduce developement effort by 60% becuase the developer only need to give service implmenetation. Rest of controller, log, swagger docs, workflow will be take care by the framework.
2. Enforces rest best practices.




## Basic Entity

```java
@Entity
public abstract class BasicEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    @ApiModelProperty(notes = "The database generated ID.")
    private Long id;

    @Column(name = "created_time", insertable=true, updatable=false)
    @ApiModelProperty(notes = "The database generated created time.")
    private Date createdTime;

    @Column(name = "updated_time", insertable=false, updatable=true)
    @ApiModelProperty(notes = "The database generated updated time.")
    private Date updatedTime;
}

```

## Generic Entity Controller

```java
@RestController
@RequestMapping("/entities")
@Api(value="Entity Management System API", description="CRUD operations on Entity.")
public class EntityController<T extends BasicEntity>

```

### Create Entity using Http Post

```java
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

```
![create_entity](/src/main/resources/images/create_entity.png "create_entity")

### Update Entity using Http Put

```java
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
```
![update_entity](/src/main/resources/images/update_entity.png "update_entity")


### Partial Update Entity using Http Patch

```java
@@ApiOperation(value = "Partial update entity with the given id and the updates.", response = APIResponse.class)
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
```
![partial_update_entity](/src/main/resources/images/partial_update_entity.png "partial_update_entity")


### Delete Entity using Http Delete

```java
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
```
![delete_entity](/src/main/resources/images/delete_entity.png "delete_entity")


### Get Entity using Http Get

```java
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

```
![get_entity](/src/main/resources/images/get_entity.png "get_entity")


### Get All Entity using Http Get

```java
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
```
![get_all_entity](/src/main/resources/images/get_all_entities.png "get_all_entity")

### How to create new entities

1. Create the Entity Class, Example Patient

```java
@Entity
public class Patient extends BasicEntity{


    @Column(name = "name", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Name of the patient")
    private String name;

    @Column(name = "address", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Address of the patient")
    private String address;

    @Column(name = "mobile", insertable=true, updatable=false)
    @ApiModelProperty(notes = "Mobile number of the patient")
    private String mobile;
}
```

2. Create the Repository, Example PatientRepository

```java
@Repository
public interface PatientRepository extends EntityRepository<Patient> {
}
```

3. Create the Service, Example PatientService and implement the method, That's the only implementation required.

```java
@Service
@Resource(name="patientService")
public class PatientService implements EntityService<Patient>
```

4. Create the Rest Controller, Example PatientController

```java
@RestController
@RequestMapping("/patients")
@Api(value="Patient Management System API", description="CRUD operations on Patient.")
public class PatientController extends EntityController<Patient> {
}
```

## Now complete, Your Patient Rest API with all the rest api end points are ready.
![entity_swagger](/src/main/resources/images/entity_swagger.png "entity_swagger")
![patient_swagger](/src/main/resources/images/patient_swagger.png "patient_swagger")
![product_swagger](/src/main/resources/images/product_swagger.png "product_swagger")
![model_swagger](/src/main/resources/images/model_swagger.png "model_swagger")



## How to start ?

```
$ mvn spring-boot:run
```

## Swagger-UI
* After starting the application Click on [Swagger-home](http://localhost:8080/api/swagger-ui.html)


