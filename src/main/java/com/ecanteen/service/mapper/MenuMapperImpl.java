package com.ecanteen.service.mapper;

import com.ecanteen.domain.Menu;

import com.ecanteen.service.dto.MenuDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class MenuMapperImpl implements MenuMapper {


    @Override
    public Menu toEntity(MenuDTO dto) {
        if (dto == null) {
            return null;
        }
        Menu menu = new Menu();

        menu.setId(dto.getId());
        menu.setName(dto.getName());
        menu.setCode(dto.getCode());
        menu.setCreatedDate(dto.getCreatedDate());
        menu.setModifiedDate(dto.getModifiedDate());

        return menu;
    }

    @Override
    public MenuDTO toDto(Menu entity) {
        if (entity == null) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId(entity.getId());
        menuDTO.setName(entity.getName());
        menuDTO.setCode(entity.getCode());
        menuDTO.setCreatedDate(entity.getCreatedDate());
        menuDTO.setModifiedDate(entity.getModifiedDate());

        return menuDTO;
    }


    @Override
    public List<Menu> toEntity(List<MenuDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>(dtoList.size());
        for (MenuDTO menuDTO : dtoList) {
            list.add(toEntity(menuDTO));
        }

        return list;
    }

    @Override
    public List<MenuDTO> toDto(List<Menu> entityList) {
        if (entityList == null) {
            return null;
        }

        List<MenuDTO> list = new ArrayList<MenuDTO>(entityList.size());
        for (Menu Menu: entityList) {
            list.add(toDto(Menu));
        }

        return list;
    }

    @Override
    public void partialUpdate(Menu entity, MenuDTO dto) {
        if (dto == null) {
            return;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getName() != null) {
            entity.name(dto.getName());
        }
        if (dto.getCode() != null) {
            entity.code(dto.getCode());
        }
        if (dto.getCreatedDate() != null) {
            entity.createdDate(dto.getCreatedDate());
        }
        if (dto.getModifiedDate() != null) {
            entity.modifiedDate(dto.getModifiedDate());
        }

    }
}

