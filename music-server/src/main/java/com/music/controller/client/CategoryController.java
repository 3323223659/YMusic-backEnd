package com.music.controller.client;

import com.music.entity.Category;
import com.music.result.Result;
import com.music.service.CategoryService;
import com.music.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("clientCategoryController")
@RequestMapping("/music/client/category")
@Api(tags = "歌曲分类相关接口")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation("查询所有分类")
    //todo:redis
    @Cacheable(cacheNames = "categoryCache",key = "'categoryList'")
    public Result<List<CategoryVO>> list() {
        List<CategoryVO> list = categoryService.list();
        return Result.success(list);
    }

}
