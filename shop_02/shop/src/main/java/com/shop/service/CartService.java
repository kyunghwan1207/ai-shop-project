package com.shop.service;

import com.shop.dto.CartDTO;
import com.shop.frame.MyService;
import com.shop.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements MyService<Integer, CartDTO> {
    @Autowired
    CartMapper cartMapper;

    @Override
    public void register(CartDTO cartDTO) throws Exception {
        cartMapper.insert(cartDTO);
    }

    @Override
    public void modify(CartDTO cartDTO) throws Exception {
        cartMapper.update(cartDTO);
    }

    @Override
    public void remove(Integer id) throws Exception {
        cartMapper.delete(id);
    }

    @Override
    public CartDTO get(Integer id) throws Exception {
        return cartMapper.select(id);
    }

    @Override
    public List<CartDTO> get() throws Exception {
        return cartMapper.selectall();
    }

    public List<CartDTO> findAllWithCustId(String cust_id) throws Exception {
        return cartMapper.findAllWithCustId(cust_id);
    }
}
