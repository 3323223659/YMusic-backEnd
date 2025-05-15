package com.music.controller.client;

import com.music.context.BaseContext;
import com.music.dto.MusicQueryDTO;
import com.music.entity.Music;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("clientMusicController")
@RequestMapping("/music/client/music")
@Api(tags = "歌曲相关接口")
public class MusicController {
    @Autowired
    MusicService musicService;

    @GetMapping("/page")
    @ApiOperation("歌曲分页查询")
    public Result<PageResult<Music>> pageQuery(PageParams pageParams, MusicQueryDTO musicQueryDTO){
        log.info("歌曲分页查询：{},{}",pageParams,musicQueryDTO);
        PageResult<Music> pageResult = musicService.page(pageParams,musicQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/collection")
    @ApiOperation("查询收藏歌曲")
    public Result<List<Music>> collection(){
        Long userId = BaseContext.getCurrentId();
        log.info("查询收藏歌曲：{}",userId);
        List<Music> musics = musicService.collection(userId);
        return Result.success(musics);
    }

    @PostMapping("/{id}")
    @ApiOperation("收藏歌曲")
    public Result<String> collect(@PathVariable Long id){
        Long userId = BaseContext.getCurrentId();
        log.info("收藏歌曲：{},{}",id,userId);
        musicService.collect(id,userId);
        return Result.success("收藏成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("取消收藏")
    public Result<String> deleteCollect(@PathVariable Long id){
        Long userId = BaseContext.getCurrentId();
        log.info("取消收藏歌曲：{},{}",id,userId);
        musicService.deleteCollect(id,userId);
        return Result.success("取消收藏成功");
    }

    @GetMapping("/list")
    @ApiOperation("根据条件查询歌曲")
    public Result<List<Music>> list(MusicQueryDTO musicQueryDTO) {
        log.info("根据条件查询歌曲：{}",musicQueryDTO);
        List<Music> list = musicService.list(musicQueryDTO);
        return Result.success(list);
    }
}
