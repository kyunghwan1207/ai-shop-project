package com.shop.mapper;

import com.shop.dto.CustDTO;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustMapper extends MyMapper<String, CustDTO> {

    List<CustDTO> findByName(String searchName);

}
