package com.music.service;

import com.music.dto.PlaylistDTO;
import com.music.dto.PlaylistPageQueryDTO;
import com.music.entity.Playlist;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.vo.PlaylistVO;

import java.util.List;

public interface PlaylistService {

    //新增歌单
    void insert(PlaylistDTO playlistDTO);

    //修改歌单信息
    void update(PlaylistDTO playlistDTO);

    //删除歌单
    void delete(Long id);

    //歌单信息分页查询
    PageResult<Playlist> page(PageParams pageParams, PlaylistPageQueryDTO playlistPageQueryDTO);

    //歌单数量查询
    Integer count();

    //查询所有歌单
    List<PlaylistVO> list();
}
