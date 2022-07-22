package com.ecanteen.repository;

 import com.ecanteen.domain.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long>, JpaSpecificationExecutor<UserCredential> {
}
