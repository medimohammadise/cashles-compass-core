package com.ecanteen.service.mapper;

 import com.ecanteen.domain.NotificationHistory;
 import com.ecanteen.service.dto.NotificationHistoryDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface NotificationHistoryMapper  extends EntityMapper<NotificationHistoryDTO, NotificationHistory>{}
