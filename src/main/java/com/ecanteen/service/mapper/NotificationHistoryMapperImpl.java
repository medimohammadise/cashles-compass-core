package com.ecanteen.service.mapper;


  import com.ecanteen.domain.NotificationHistory;
  import com.ecanteen.service.dto.NotificationHistoryDTO;
  import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationHistoryMapperImpl implements NotificationHistoryMapper {


    @Override
    public NotificationHistory toEntity(NotificationHistoryDTO dto) {
        if (dto == null) {
            return null;
        }
        NotificationHistory notificationHistory = new NotificationHistory();

        notificationHistory.setId(dto.getId());
        notificationHistory.setDate(dto.getDate());
        notificationHistory.setMethod(dto.getMethod());
        notificationHistory.setStatus(dto.getStatus());

        return notificationHistory;
    }

    @Override
    public NotificationHistoryDTO toDto(NotificationHistory entity) {
        if (entity == null) {
            return null;
        }

        NotificationHistoryDTO notificationHistoryDTO = new NotificationHistoryDTO();

        notificationHistoryDTO.setId(entity.getId());
        notificationHistoryDTO.setDate(entity.getDate());
        notificationHistoryDTO.setMethod(entity.getMethod());
        notificationHistoryDTO.setStatus(entity.getStatus());

        return notificationHistoryDTO;
    }


    @Override
    public List<NotificationHistory> toEntity(List<NotificationHistoryDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<NotificationHistory> list = new ArrayList<NotificationHistory>(dtoList.size());
        for (NotificationHistoryDTO notificationHistoryDTO : dtoList) {
            list.add(toEntity(notificationHistoryDTO));
        }

        return list;
    }

    @Override
    public List<NotificationHistoryDTO> toDto(List<NotificationHistory> entityList) {
        if (entityList == null) {
            return null;
        }

        List<NotificationHistoryDTO> list = new ArrayList<NotificationHistoryDTO>(entityList.size());
        for (NotificationHistory notificationHistory: entityList) {
            list.add(toDto(notificationHistory));
        }

        return list;
    }

    @Override
    public void partialUpdate(NotificationHistory entity, NotificationHistoryDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getDate() != null) {
            entity.date(dto.getDate());
        }
        if (dto.getMethod() != null) {
            entity.method(dto.getMethod());
        }
        if (dto.getStatus() != null) {
            entity.status(dto.getStatus());
        }


    }
}
