package com.music.service;

import com.music.dto.MusicDTO;
import com.music.dto.MusicQueryDTO;
import com.music.entity.Music;
import com.music.model.PageParams;
import com.music.model.PageResult;

import java.util.List;

public interface MusicService {

    //新增歌曲
    void insert(MusicDTO musicDTO);

    //修改歌曲
    void update(MusicDTO musicDTO);

    //删除歌曲
    void delete(Long id);

    //歌曲分页查询
    PageResult<Music> page(PageParams pageParams, MusicQueryDTO musicQueryDTO);

    //添加歌曲到歌单
    void musicToPlaylist(Long musicId, Long playlistId);

    //收藏歌曲
    void collect(Long musicId, Long userId);

    //从歌单删除歌曲
    void deleteMusicByPlaylist(Long musicId, Long playlistId);

    //查询收藏歌曲
    List<Music> collection(Long userId);

    //取消收藏
    void deleteCollect(Long musicId, Long userId);

    //歌曲数量查询
    Integer count();

    //根据条件查询歌曲
    List<Music> list(MusicQueryDTO musicQueryDTO);
}
