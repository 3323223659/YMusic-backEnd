package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.constant.MessageConstant;
import com.music.dto.MusicDTO;
import com.music.dto.MusicQueryDTO;
import com.music.entity.Category;
import com.music.entity.Music;
import com.music.entity.MusicPlaylist;
import com.music.entity.UserMusic;
import com.music.exception.MusicPlaylistErrorException;
import com.music.mapper.MusicMapper;
import com.music.mapper.MusicPlaylistMapper;
import com.music.mapper.UserMusicMapper;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.MusicService;
import com.music.utils.AliOssUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    MusicPlaylistMapper musicPlaylistMapper;
    @Autowired
    UserMusicMapper userMusicMapper;
    @Autowired
    AliOssUtil aliOssUtil;

    //新增歌曲
    @Override
    public void insert(MusicDTO musicDTO) {
        Music music = new Music();
        BeanUtils.copyProperties(musicDTO,music);

        musicMapper.insert(music);
    }

    //修改歌曲
    @Override
    public void update(MusicDTO musicDTO) {
        Music pre = musicMapper.selectById(musicDTO.getId());
        Music music = new Music();
        BeanUtils.copyProperties(musicDTO,music);
        musicMapper.updateById(music);
        if (!pre.getPath().equals(musicDTO.getPath())){
            aliOssUtil.delete(pre.getPath(),"audio");
        }
    }

    //删除歌曲
    @Override
    @Transactional
    public void delete(Long id) {
        Music music = musicMapper.selectById(id);
        musicMapper.deleteById(id);
        //删除歌单表中的信息
        LambdaQueryWrapper<MusicPlaylist> queryWrapper1 = new LambdaQueryWrapper();
        queryWrapper1.eq(MusicPlaylist::getMusicId,id);
        musicPlaylistMapper.delete(queryWrapper1);

        //删除用户收藏表信息
        LambdaQueryWrapper<UserMusic> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(UserMusic::getMusicId,id);
        userMusicMapper.delete(queryWrapper2);

        aliOssUtil.delete(music.getPath(),"audio");
    }

    //歌曲分页信息查询
    @Override
    @Transactional
    public PageResult<Music> page(PageParams pageParams, MusicQueryDTO musicQueryDTO) {
        if (Objects.isNull(musicQueryDTO.getPlaylistId())) {
            LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(StringUtils.isNotEmpty(musicQueryDTO.getName()),Music::getName, musicQueryDTO.getName())
                    .eq(!Objects.isNull(musicQueryDTO.getSingerId()),Music::getSingerId,musicQueryDTO.getSingerId())
                    .eq(!Objects.isNull(musicQueryDTO.getCategoryId()),Music::getCategoryId,musicQueryDTO.getCategoryId());

            Page<Music> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
            Page<Music> musicPage = musicMapper.selectPage(page, queryWrapper);
            List<Music> records = musicPage.getRecords();
            long total = musicPage.getTotal();
            PageResult<Music> pageResult = new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
            return pageResult;
        } else {
            LambdaQueryWrapper<MusicPlaylist> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MusicPlaylist::getPlaylistId,musicQueryDTO.getPlaylistId());
            List<MusicPlaylist> musicPlaylists = musicPlaylistMapper.selectList(queryWrapper);

            List<Long> ids = new ArrayList<>();
            for (MusicPlaylist musicPlaylist : musicPlaylists) {
                ids.add(musicPlaylist.getMusicId());
            }
            if (ids.isEmpty()){
                List<Music> records = new ArrayList<>();
                PageResult<Music> pageResult = new PageResult<>(records, records.size(),0,0);
                return pageResult;
            }

            List<Music> records = musicMapper.selectBatchIds(ids);
            PageResult<Music> pageResult = new PageResult<>(records, records.size(),0,0);
            return pageResult;
        }

    }

    //添加歌曲到歌单
    @Override
    public void musicToPlaylist(Long musicId, Long playlistId) {
        LambdaQueryWrapper<MusicPlaylist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MusicPlaylist::getPlaylistId,playlistId)
                        .eq(MusicPlaylist::getMusicId,musicId);
        MusicPlaylist musicPlaylist = musicPlaylistMapper.selectOne(queryWrapper);
        if (musicPlaylist != null){
            throw new MusicPlaylistErrorException(MessageConstant.MUSIC_PLAYLIST_EXISTS);
        }
        musicPlaylist = new MusicPlaylist();
        musicPlaylist.setMusicId(musicId);
        musicPlaylist.setPlaylistId(playlistId);

        musicPlaylistMapper.insert(musicPlaylist);
    }

    //收藏歌曲
    @Override
    public void collect(Long musicId, Long userId) {
        LambdaQueryWrapper<UserMusic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserMusic::getUserId,userId)
                .eq(UserMusic::getMusicId,musicId);
        UserMusic userMusic = userMusicMapper.selectOne(queryWrapper);
        if (userMusic != null){
            throw new MusicPlaylistErrorException(MessageConstant.USER_MUSIC_EXISTS);
        }
        userMusic = new UserMusic();
        userMusic.setMusicId(musicId);
        userMusic.setUserId(userId);

        userMusicMapper.insert(userMusic);
    }

    //从歌单删除歌曲
    @Override
    public void deleteMusicByPlaylist(Long musicId, Long playlistId) {
        LambdaQueryWrapper<MusicPlaylist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MusicPlaylist::getMusicId,musicId)
                .eq(MusicPlaylist::getPlaylistId,playlistId);

        musicPlaylistMapper.delete(queryWrapper);
    }

    //查询收藏歌曲
    @Override
    public List<Music> collection(Long userId) {
        LambdaQueryWrapper<UserMusic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserMusic::getUserId,userId);
        List<UserMusic> userMusics = userMusicMapper.selectList(queryWrapper);
        List<Long> ids = new ArrayList<>();
        List<Music> records = new ArrayList<>();
        if (!userMusics.isEmpty()) {
            for (UserMusic userMusic : userMusics) {
                ids.add(userMusic.getMusicId());
            }
            records = musicMapper.selectBatchIds(ids);
            return records;
        }
        return records;
    }

    //取消收藏
    @Override
    public void deleteCollect(Long musicId, Long userId) {
        LambdaQueryWrapper<UserMusic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserMusic::getMusicId,musicId)
                .eq(UserMusic::getUserId,userId);

        userMusicMapper.delete(queryWrapper);
    }

    //歌曲数量查询
    @Override
    public Integer count() {
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        Integer musicCount = musicMapper.selectCount(queryWrapper);
        return musicCount;
    }

    //根据条件查询歌曲
    @Override
    public List<Music> list(MusicQueryDTO musicQueryDTO) {
        if (Objects.isNull(musicQueryDTO.getPlaylistId())) {
            LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(StringUtils.isNotEmpty(musicQueryDTO.getName()),Music::getName, musicQueryDTO.getName())
                    .eq(!Objects.isNull(musicQueryDTO.getSingerId()),Music::getSingerId,musicQueryDTO.getSingerId())
                    .eq(!Objects.isNull(musicQueryDTO.getCategoryId()),Music::getCategoryId,musicQueryDTO.getCategoryId());
            List<Music> music = musicMapper.selectList(queryWrapper);

            return music;
        } else {
            LambdaQueryWrapper<MusicPlaylist> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MusicPlaylist::getPlaylistId,musicQueryDTO.getPlaylistId());
            List<MusicPlaylist> musicPlaylists = musicPlaylistMapper.selectList(queryWrapper);

            List<Long> ids = new ArrayList<>();
            for (MusicPlaylist musicPlaylist : musicPlaylists) {
                ids.add(musicPlaylist.getMusicId());
            }
            if (ids.isEmpty()){
                List<Music> records = new ArrayList<>();
                return records;
            }
            List<Music> records = musicMapper.selectBatchIds(ids);
            return records;
        }
    }


}
