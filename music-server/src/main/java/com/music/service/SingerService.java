package com.music.service;

import com.music.dto.SingerDTO;
import com.music.dto.SingerPageQuery;
import com.music.entity.Category;
import com.music.entity.Singer;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.vo.SingerVO;

import java.util.List;

public interface SingerService {

    //新增歌手
    void insert(SingerDTO singerDTO);

    //修改歌手信息
    void update(SingerDTO singerDTO);

    //删除歌手
    void delete(Long id);

    //歌手信息分页查询
    PageResult<Singer> page(PageParams pageParams, SingerPageQuery singerPageQuery);

    //歌手数量查询
    Integer count();

    //查询所有歌手
    List<SingerVO> list();
}
