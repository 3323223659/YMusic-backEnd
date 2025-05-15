package com.music.service;

import com.music.dto.CategoryDTO;
import com.music.dto.CategoryPageQueryDTO;
import com.music.entity.Category;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    //添加歌曲分类
    void insert(CategoryDTO categoryDTO);

    //修改歌曲分类
    void update(CategoryDTO categoryDTO);

    //删除歌曲分类
    void deleteById(Long id);

    //分类信息分页查询
    PageResult<Category> page(PageParams pageParams, CategoryPageQueryDTO categoryPageQueryDTO);

    //查询所有分类信息
    List<CategoryVO> list();
}
