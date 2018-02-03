package com.ems.repository;

import com.ems.model.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rohitkumar on 28/01/18.
 */
@Repository
public interface EntityRepository<T extends BasicEntity> extends JpaRepository<T, Long> {

}
