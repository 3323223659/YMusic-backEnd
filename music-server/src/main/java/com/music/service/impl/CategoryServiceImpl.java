package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.dto.CategoryDTO;
import com.music.dto.CategoryPageQueryDTO;
import com.music.entity.Category;
import com.music.entity.Music;
import com.music.mapper.CategoryMapper;
import com.music.mapper.MusicMapper;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.service.CategoryService;
import com.music.vo.CategoryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    MusicMapper musicMapper;

    //添加歌曲分类
    @Override
    public void insert(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.insert(category);
    }

    //修改歌曲分类
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
//        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.updateById(category);
    }

    //删除歌曲分类
    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
        Music music = new Music();
        Long defaultCategoryId = 0L;
        music.setCategoryId(defaultCategoryId);
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Music::getCategoryId,id);
        musicMapper.update(music,queryWrapper);
    }

    //分类信息分页查询
    @Override
    public PageResult<Category> page(PageParams pageParams, CategoryPageQueryDTO categoryPageQueryDTO) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(categoryPageQueryDTO.getName()),Category::getName, categoryPageQueryDTO.getName());
        Page<Category> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<Category> categoryPage = categoryMapper.selectPage(page, queryWrapper);
        List<Category> records = categoryPage.getRecords();
        long total = categoryPage.getTotal();
        PageResult<Category> pageResult = new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());

        return pageResult;
    }

    //查询全部分类信息
    @Override
    public List<CategoryVO> list() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        List<CategoryVO> categories = new ArrayList<>();
        for (Category category : categoryMapper.selectList(queryWrapper)) {
            CategoryVO categoryVO = new CategoryVO(category.getId(), category.getName());
            categories.add(categoryVO);
        }

        return categories;
    }


}
