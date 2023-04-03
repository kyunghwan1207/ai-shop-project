package com.shop.mapper;

import com.github.pagehelper.Page;
import com.shop.dto.ItemDTO;
import com.shop.frame.Team3Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemMapper extends Team3Mapper<Integer, ItemDTO> {
    //insert
    //update
    //delete
    //select
    //selectall
    List<ItemDTO> search(String txt) throws Exception;
    Page<ItemDTO> getPage() throws Exception;

    List<Integer> getIdList(); // item select-box 구현에서 사용
}
