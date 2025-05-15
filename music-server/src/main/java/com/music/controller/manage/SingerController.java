package com.music.controller.manage;

import com.music.dto.SingerDTO;
import com.music.dto.SingerPageQuery;
import com.music.entity.Category;
import com.music.entity.Singer;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.SingerService;
import com.music.vo.SingerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("manageSingerController")
@RequestMapping("/music/manage/singer")
@Api(tags = "歌手相关接口")
public class SingerController {
    @Autowired
    SingerService singerService;

    @PostMapping
    @ApiOperation("新增歌手")
    //todo:redis
    @CacheEvict(cacheNames = "singerCache",key = "'singerList'")
    public Result<String> insert(@RequestBody SingerDTO singerDTO){
        log.info("新增歌手：{}",singerDTO);
        singerService.insert(singerDTO);
        return Result.success("新增成功");
    }

    @PutMapping
    @ApiOperation("修改歌手信息")
    //todo:redis
    @CacheEvict(cacheNames = "singerCache",key = "'singerList'")
    public Result<String> update(@RequestBody SingerDTO singerDTO){
        log.info("修改歌手信息：{}",singerDTO);
        singerService.update(singerDTO);
        return Result.success("修改信息成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除歌手")
    //todo:redis
    @CacheEvict(cacheNames = "singerCache",key = "'singerList'")
    public Result<String> delete(@PathVariable Long id){
        log.info("删除歌手：{}",id);
        singerService.delete(id);
        return Result.success("删除歌手成功");
    }

    @GetMapping("/page")
    @ApiOperation("歌手信息分页查询")
    public Result<PageResult<Singer>> pageQuery(PageParams pageParams, SingerPageQuery singerPageQuery){
        log.info("歌手信息分页查询：{},{}",pageParams,singerPageQuery);
        PageResult<Singer> pageResult = singerService.page(pageParams,singerPageQuery);
        return Result.success(pageResult);
    }

    @GetMapping("/count")
    @ApiOperation("统计歌手数量")
    public Result<Integer> count(){
        log.info("歌手数量查询");
        Integer singerCount = singerService.count();
        return Result.success(singerCount);
    }

    @GetMapping("/list")
    @ApiOperation("查询所有歌手")
    //todo:redis
    @Cacheable(cacheNames = "singerCache",key = "'singerList'")
    public Result<List<SingerVO>> list() {
        List<SingerVO> list = singerService.list();
        return Result.success(list);
    }
}
