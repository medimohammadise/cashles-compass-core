package com.ecanteen.repository;

import com.ecanteen.domain.ActivationCode;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long>, JpaSpecificationExecutor<ActivationCode> {
}
