package com.music.controller.client;

import com.music.dto.PlaylistPageQueryDTO;
import com.music.entity.Playlist;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.PlaylistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("clientPlaylistController")
@RequestMapping("/music/client/playlist")
@Api(tags = "歌单相关接口")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    @GetMapping("/page")
    @ApiOperation("歌单信息分页查询")
    public Result<PageResult<Playlist>> pageQuery(PageParams pageParams, PlaylistPageQueryDTO playlistPageQueryDTO){
        log.info("歌单信息分页查询：{},{}",pageParams, playlistPageQueryDTO);
        PageResult<Playlist> pageResult = playlistService.page(pageParams, playlistPageQueryDTO);
        return Result.success(pageResult);
    }
}
