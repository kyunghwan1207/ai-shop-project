package com.shop.mapper;

import com.shop.dto.MallDTO;
import com.shop.frame.Team3Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MallMapper extends Team3Mapper<Integer, MallDTO> {

}
