package com.ems.api;

import com.ems.Utils.FileUtils;
import com.ems.model.Patient;
import com.ems.service.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rohitkumar on 03/02/18.
 */
@RunWith(SpringRunner.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    private MockMvc mockMvc;
    private PatientService patientService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        patientService = Mockito.mock(PatientService.class);

        Field field = ReflectionUtils.findField(EntityController.class, "entityService");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, patientController, patientService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();

    }

    @Test
    public void testCreateEntity() throws Exception {

        String createPatientJson = FileUtils.readFileIntoJson(Patient.class, "create_patient.json");
        Patient patient = FileUtils.readObjectFromJsonFile(Patient.class, "create_patient.json");

        String message = "Entity created successfully.";
        APIResponse<Patient>  apiResponse = new APIResponse(message,  HttpStatus.CREATED.value(), patient);        ResponseEntity expectedResponse = new ResponseEntity<APIResponse>(apiResponse, HttpStatus.CREATED);
        Mockito.when(patientService.createEntity(any())).thenReturn(patient);

        this.mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(createPatientJson))
                .andExpect(status().is(201))
                .equals(expectedResponse);
    }
}
