package com.music.controller.manage;

import com.music.dto.CategoryDTO;
import com.music.dto.CategoryPageQueryDTO;
import com.music.entity.Category;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.CategoryService;
import com.music.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("manageCategoryController")
@RequestMapping("/music/manage/category")
@Api(tags = "歌曲分类相关接口")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    @ApiOperation("新增分类")
    //todo:redis
    @CacheEvict(cacheNames = "categoryCache",key = "'categoryList'")
    public Result<String> insert(@RequestBody CategoryDTO categoryDTO){
        log.info("添加歌曲分类：{}",categoryDTO);
        categoryService.insert(categoryDTO);
        return Result.success("添加成功");
    }

    @PutMapping
    @ApiOperation("修改分类")
    //todo:redis
    @CacheEvict(cacheNames = "categoryCache",key = "'categoryList'")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改歌曲分类：{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除分类")
    //todo:redis
    @CacheEvict(cacheNames = "categoryCache",key = "'categoryList'")
    public Result<String> deleteById(@PathVariable Long id){
        log.info("删除分类：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分类信息分页查询")
    public Result<PageResult<Category>> pageQuery(PageParams pageParams, CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类信息分页查询：{},{}",pageParams, categoryPageQueryDTO);
        PageResult<Category> pageResult = categoryService.page(pageParams, categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    @ApiOperation("查询所有分类")
    //todo:redis
    @Cacheable(cacheNames = "categoryCache",key = "'categoryList'")
    public Result<List<CategoryVO>> list() {
        List<CategoryVO> list = categoryService.list();
        return Result.success(list);
    }

}
