package com.shop.mapper;

import com.github.pagehelper.Page;
import com.shop.dto.CartDTO;
import com.shop.frame.Team3Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper extends Team3Mapper<Integer, CartDTO> {
    List<CartDTO> findAllWithCustId(String cust_id) throws Exception;
    Page<CartDTO> getPageWithCustId(String cust_id) throws Exception;
}
