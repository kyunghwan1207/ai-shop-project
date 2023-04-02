package com.shop.service;

import com.shop.dto.CustDTO;
import com.shop.frame.MyService;
import com.shop.mapper.CustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustService implements MyService<String, CustDTO> {
    @Autowired
    CustMapper custMapper;

    @Override
    public void register(CustDTO custDTO) throws Exception {
        custMapper.insert(custDTO);
    }

    @Override
    public void modify(CustDTO custDTO) throws Exception {
        custMapper.update(custDTO);
    }

    @Override
    public void remove(String key) throws Exception {
        custMapper.delete(key);
    }

    @Override
    public CustDTO get(String key) throws Exception {
        return custMapper.select(key);
    }

    @Override
    public List<CustDTO> get() throws Exception {
        return custMapper.selectall();
    }
    public List<CustDTO> findByName(String searchName) throws Exception {
        return custMapper.findByName(searchName);
    }
}
