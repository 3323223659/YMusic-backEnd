package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.dto.PlaylistDTO;
import com.music.dto.PlaylistPageQueryDTO;
import com.music.entity.MusicPlaylist;
import com.music.entity.Playlist;
import com.music.mapper.MusicPlaylistMapper;
import com.music.mapper.PlaylistMapper;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.service.PlaylistService;
import com.music.utils.AliOssUtil;
import com.music.vo.PlaylistVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    PlaylistMapper playlistMapper;
    @Autowired
    MusicPlaylistMapper musicPlaylistMapper;
    @Autowired
    private AliOssUtil aliOssUtil;

    //新增歌单
    @Override
    public void insert(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        BeanUtils.copyProperties(playlistDTO,playlist);
//        playlist.setCreateTime(LocalDateTime.now());
//        playlist.setUpdateTime(LocalDateTime.now());

        playlistMapper.insert(playlist);
    }

    //修改歌单信息
    @Override
    public void update(PlaylistDTO playlistDTO) {
        Playlist pre = playlistMapper.selectById(playlistDTO.getId());
        Playlist playlist = new Playlist();
        BeanUtils.copyProperties(playlistDTO,playlist);
        playlistMapper.updateById(playlist);
        if (!pre.getImage().equals(playlistDTO.getImage())){
            aliOssUtil.delete(pre.getImage(),"image");
        }
    }

    //删除歌单
    @Override
    @Transactional
    public void delete(Long id) {
        Playlist playlist = playlistMapper.selectById(id);
        //删除歌单信息
        playlistMapper.deleteById(id);
        //关联表中删除歌单与音乐关联信息
        LambdaQueryWrapper<MusicPlaylist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MusicPlaylist::getPlaylistId,id);
        musicPlaylistMapper.delete(queryWrapper);
        aliOssUtil.delete(playlist.getImage(),"image");
    }

    //歌单信息分页查询
    @Override
    public PageResult<Playlist> page(PageParams pageParams, PlaylistPageQueryDTO playlistPageQueryDTO) {
        LambdaQueryWrapper<Playlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(playlistPageQueryDTO.getName()),Playlist::getName, playlistPageQueryDTO.getName());
        Page<Playlist> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<Playlist> playlistPage = playlistMapper.selectPage(page, queryWrapper);
        List<Playlist> records = playlistPage.getRecords();
        long total = playlistPage.getTotal();
        PageResult<Playlist> pageResult = new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());

        return pageResult;
    }

    //歌单数量查询
    @Override
    public Integer count() {
        LambdaQueryWrapper<Playlist> queryWrapper = new LambdaQueryWrapper<>();
        Integer playlistCount = playlistMapper.selectCount(queryWrapper);
        return playlistCount;
    }

    //查询所有歌单
    @Override
    public List<PlaylistVO> list() {
        LambdaQueryWrapper<Playlist> queryWrapper = new LambdaQueryWrapper<>();
        List<PlaylistVO> playlists = new ArrayList<>();
        for (Playlist playlist : playlistMapper.selectList(queryWrapper)) {
            PlaylistVO playlistVO = new PlaylistVO(playlist.getId(), playlist.getName());
            playlists.add(playlistVO);
        }

        return playlists;
    }


}
