package com.ecanteen.repository;

 import com.ecanteen.domain.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> , JpaSpecificationExecutor<NotificationHistory> {
}
