package com.music.controller.manage;

import com.music.dto.PlaylistDTO;
import com.music.dto.PlaylistPageQueryDTO;
import com.music.entity.Category;
import com.music.entity.Playlist;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.PlaylistService;
import com.music.vo.PlaylistVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("managePlaylistController")
@RequestMapping("/music/manage/playlist")
@Api(tags = "歌单相关接口")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    @PostMapping
    @ApiOperation("新增歌单")
    //todo:redis
    @CacheEvict(cacheNames = "playlistCache",key = "'playlistCache'")
    public Result<String> insert(@RequestBody PlaylistDTO playlistDTO){
        log.info("新增歌单：{}",playlistDTO);
        playlistService.insert(playlistDTO);
        return Result.success("新增成功");
    }

    @PutMapping
    @ApiOperation("修改歌单信息")
    //todo:redis
    @CacheEvict(cacheNames = "playlistCache",key = "'playlistCache'")
    public Result<String> update(@RequestBody PlaylistDTO playlistDTO){
        log.info("修改歌单信息：{}",playlistDTO);
        playlistService.update(playlistDTO);
        return Result.success("修改信息成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除歌单")
    //todo:redis
    @CacheEvict(cacheNames = "playlistCache",key = "'playlistCache'")
    public Result<String> delete(@PathVariable Long id){
        log.info("删除歌单：{}",id);
        playlistService.delete(id);
        return Result.success("删除歌单成功");
    }

    @GetMapping("/page")
    @ApiOperation("歌单信息分页查询")
    public Result<PageResult<Playlist>> pageQuery(PageParams pageParams, PlaylistPageQueryDTO playlistPageQueryDTO){
        log.info("歌单信息分页查询：{},{}",pageParams, playlistPageQueryDTO);
        PageResult<Playlist> pageResult = playlistService.page(pageParams, playlistPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/count")
    @ApiOperation("统计歌单数量")
    public Result<Integer> count(){
        log.info("歌单数量查询");
        Integer playlistCount = playlistService.count();
        return Result.success(playlistCount);
    }

    @GetMapping("/list")
    @ApiOperation("查询所有歌单")
    //todo:redis
    @Cacheable(cacheNames = "playlistCache",key = "'playlistCache'")
    public Result<List<PlaylistVO>> list() {
        List<PlaylistVO> list = playlistService.list();
        return Result.success(list);
    }
}
