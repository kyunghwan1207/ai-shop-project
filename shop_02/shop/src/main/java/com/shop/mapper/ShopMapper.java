package com.shop.mapper;

import com.shop.dto.ShopDTO;
import com.shop.frame.Team3Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopMapper extends Team3Mapper<String, ShopDTO> {
    List<ShopDTO> selectWithMenu(String shop_id) throws Exception;
}
