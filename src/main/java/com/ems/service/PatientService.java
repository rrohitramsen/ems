package com.ems.service;

import com.ems.model.Patient;
import com.ems.repository.PatientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Service
@Resource(name="patientService")
public class PatientService implements EntityService<Patient> {

    private static final Logger LOGGER = Logger.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Patient createEntity(Patient entity) {
        return null;
    }

    @Override
    public Patient updateEntity(Long id, Patient entity) {
        return null;
    }

    @Override
    public Patient partiallyUpdateEntity(Long id, Map<String, Object> updates) {
        return null;
    }

    @Override
    public void deleteEntity(Long id) {

    }

    @Override
    public Patient getEntity(Long id) {
        return null;
    }

    @Override
    public List<Patient> getAllEntities() {
        return null;
    }
}
