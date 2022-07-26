package com.ecanteen.service.mapper;

 import com.ecanteen.domain.ActivationCode;
 import com.ecanteen.domain.Menu;
 import com.ecanteen.domain.NotificationHistory;
 import com.ecanteen.service.dto.ActivationCodeDTO;
 import com.ecanteen.service.dto.MenuDTO;
 import com.ecanteen.service.dto.NotificationHistoryDTO;
import org.mapstruct.Mapper;

 import java.util.List;


@Mapper(componentModel = "spring"  ,imports = { NotificationHistory.class, NotificationHistoryDTO.class })

public interface NotificationHistoryMapper  extends EntityMapper<NotificationHistoryDTO, NotificationHistory>{


}
