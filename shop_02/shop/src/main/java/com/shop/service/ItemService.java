package com.shop.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.dto.CustDTO;
import com.shop.dto.ItemDTO;
import com.shop.frame.ItemFrameService;
import com.shop.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements ItemFrameService<Integer, ItemDTO> {
    @Autowired
    ItemMapper itemMapper;

    @Override
    public void register(ItemDTO itemDTO) throws Exception {
        itemMapper.insert(itemDTO);
    }

    @Override
    public void modify(ItemDTO itemDTO) throws Exception {
        itemMapper.update(itemDTO);
    }

    @Override
    public void remove(Integer i) throws Exception {
        itemMapper.delete(i);
    }

    @Override
    public ItemDTO get(Integer i) throws Exception {
        return itemMapper.select(i);
    }

    @Override
    public List<ItemDTO> get() throws Exception {
        return itemMapper.selectall();
    }

    public List<ItemDTO> search(String txt) throws Exception {
        return itemMapper.search(txt);
    }
    public Page<ItemDTO> getPage(int pageNo) throws Exception {
        PageHelper.startPage(pageNo, 3);
        return itemMapper.getPage();
    }

    public List<Integer> getIdList() {
        return itemMapper.getIdList();
    }
}
