package com.post.db.own.service;

import com.post.db.entity.Menu;
import com.post.db.own.dao.MenuDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuList() {
        return menuDao.getMenuList();
    }
}
