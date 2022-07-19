package com.ecanteen.repository;

 import com.ecanteen.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkerRepository extends JpaRepository<Worker, Long> , JpaSpecificationExecutor<Worker> {
}
