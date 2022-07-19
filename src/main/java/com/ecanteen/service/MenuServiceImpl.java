package com.ecanteen.service;

import com.ecanteen.domain.Menu;
import com.ecanteen.repository.MenuRepository;

import com.ecanteen.service.dto.MenuDTO;

import com.ecanteen.service.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Interface for managing {@link com.ecanteen.domain.Menu}.
 */


@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);


    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;



    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository,MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public MenuDTO save(MenuDTO menuDTO) {
        log.debug("Request to save Menus : {}", menuMapper);
        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    @Override
    public MenuDTO update(MenuDTO menuDTO) {
        log.debug("Request to save menus : {}", menuDTO);
        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    @Override
    public Optional<MenuDTO> partialUpdate(MenuDTO menuDTO) {
        log.debug("Request to partially update menus : {}", menuDTO);

        return menuRepository
            .findById(menuDTO.getId())
            .map(existingMenus -> {
                menuMapper.partialUpdate(existingMenus, menuDTO);

                return existingMenus;
            })
            .map(menuRepository::save)
            .map(menuMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all menus");
        return menuRepository.findAll(pageable).map(menuMapper::toDto);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<MenuDTO> findOne(Long id) {
        log.debug("Request to get menus : {}", id);
        return menuRepository.findById(id).map(menuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete menus : {}", id);
        menuRepository.deleteById(id);
    }
}
