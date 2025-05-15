package com.music.controller.manage;

import com.music.dto.MusicDTO;
import com.music.dto.MusicQueryDTO;
import com.music.entity.Music;
import com.music.entity.Playlist;
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
@RestController("manageMusicController")
@RequestMapping("/music/manage/music")
@Api(tags = "歌曲相关接口")
public class MusicController {
    @Autowired
    MusicService musicService;

    @PostMapping
    @ApiOperation("新增歌曲")
    public Result<String> insert(@RequestBody MusicDTO musicDTO){
        log.info("新增歌曲：{}",musicDTO);
        musicService.insert(musicDTO);
        return Result.success("新增成功");
    }

    @PutMapping
    @ApiOperation("修改歌曲")
    public Result<String> update(@RequestBody MusicDTO musicDTO){
        log.info("修改歌曲：{}",musicDTO);
        musicService.update(musicDTO);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除歌曲")
    public Result<String> delete(@PathVariable Long id){
        log.info("删除歌曲：{}",id);
        musicService.delete(id);
        return Result.success("删除成功");
    }

    @GetMapping("/page")
    @ApiOperation("歌曲分页查询")
    public Result<PageResult<Music>> pageQuery(PageParams pageParams, MusicQueryDTO musicQueryDTO){
        log.info("歌曲分页查询：{},{}",pageParams,musicQueryDTO);
        PageResult<Music> pageResult = musicService.page(pageParams,musicQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/toPlaylist")
    @ApiOperation("添加歌曲到歌单")
    public Result<String> musicToPlaylist(Long musicId,Long playlistId){
        log.info("添加歌曲到歌单：{},{}",musicId,playlistId);
        musicService.musicToPlaylist(musicId,playlistId);
        return Result.success("添加成功");
    }

    @DeleteMapping("/deleteMusicByPlaylist")
    @ApiOperation("从歌单删除歌曲")
    public Result<String> deleteMusicByPlaylist(Long musicId,Long playlistId){
        log.info("从歌单删除歌曲：{},{}",musicId,playlistId);
        musicService.deleteMusicByPlaylist(musicId,playlistId);
        return Result.success("删除成功");
    }

    @GetMapping("/list")
    @ApiOperation("根据条件查询歌曲")
    public Result<List<Music>> list(MusicQueryDTO musicQueryDTO) {
        log.info("根据条件查询歌曲：{}",musicQueryDTO);
        List<Music> list = musicService.list(musicQueryDTO);
        return Result.success(list);
    }

    @GetMapping("/count")
    @ApiOperation("统计歌曲数量")
    public Result<Integer> count(){
        log.info("歌曲数量查询");
        Integer musicCount = musicService.count();
        return Result.success(musicCount);
    }

}
