package com.github.seyatel3.FoodApp.command.service;

import com.github.seyatel3.FoodApp.repository.MenuRepository;
import com.github.seyatel3.FoodApp.repository.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public List<Menu> retrieveAll() {
        return menuRepository.findAll();
    }

    @Override
    public Optional<Menu> findByName(String name) {
        return menuRepository.findByName(name);
    }

    @Override
    public Optional<Menu> findById(Integer id_menu) {
        return menuRepository.findById(id_menu);
    }

    @Override
    public void menuUpdate(Integer id_menu, String name, BigDecimal price) {menuRepository.menuUpdate(id_menu, name, price); }

    @Override
    public BigDecimal getPrice(Integer id_menu) {return menuRepository.getPrice(id_menu);}
    @Override
    public String getName(Integer idMenu) {return menuRepository.getName(idMenu);}
}
