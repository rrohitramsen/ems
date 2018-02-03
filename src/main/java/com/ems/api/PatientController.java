package com.ems.api;

import com.ems.model.Patient;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rohitkumar on 03/02/18.
 */
@RestController
@RequestMapping("/patients")
@Api(value="Patient Management System API", description="CRUD operations on Patient.")
public class PatientController extends EntityController<Patient> {
}
