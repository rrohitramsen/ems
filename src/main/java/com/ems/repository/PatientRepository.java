package com.ems.repository;

import com.ems.model.Patient;
import org.springframework.stereotype.Repository;

/**
 * Created by rohitkumar on 03/02/18.
 */
@Repository
public interface PatientRepository extends EntityRepository<Patient> {
}
