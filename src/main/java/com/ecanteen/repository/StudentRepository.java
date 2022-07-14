package com.ecanteen.repository;

import com.ecanteen.domain.School;
import com.ecanteen.domain.Student;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the School entity.
 */
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {}
