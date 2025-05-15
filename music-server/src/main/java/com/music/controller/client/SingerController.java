package com.music.controller.client;

import com.music.dto.SingerPageQuery;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("clientSingerController")
@RequestMapping("/music/client/singer")
@Api(tags = "歌手相关接口")
public class SingerController {
    @Autowired
    SingerService singerService;

    @GetMapping("/page")
    @ApiOperation("歌手信息分页查询")
    public Result<PageResult<Singer>> pageQuery(PageParams pageParams, SingerPageQuery singerPageQuery){
        log.info("歌手信息分页查询：{},{}",pageParams,singerPageQuery);
        PageResult<Singer> pageResult = singerService.page(pageParams,singerPageQuery);
        return Result.success(pageResult);
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
