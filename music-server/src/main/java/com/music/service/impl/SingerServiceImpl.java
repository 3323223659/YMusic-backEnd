package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.dto.SingerDTO;
import com.music.dto.SingerPageQuery;
import com.music.entity.Category;
import com.music.entity.Music;
import com.music.entity.Playlist;
import com.music.entity.Singer;
import com.music.mapper.MusicMapper;
import com.music.mapper.SingerMapper;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.service.SingerService;
import com.music.utils.AliOssUtil;
import com.music.vo.SingerVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerMapper singerMapper;
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    private AliOssUtil aliOssUtil;

    //新增歌手
    @Override
    public void insert(SingerDTO singerDTO) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(singerDTO,singer);
        singerMapper.insert(singer);
    }

    //修改歌手信息
    @Override
    public void update(SingerDTO singerDTO) {
        Singer pre = singerMapper.selectById(singerDTO.getId());
        Singer singer = new Singer();
        BeanUtils.copyProperties(singerDTO,singer);
        singerMapper.updateById(singer);
        if (!pre.getImage().equals(singerDTO.getImage())){
            aliOssUtil.delete(pre.getImage(),"image");
        }
    }

    //删除歌手
    @Override
    public void delete(Long id) {
        Singer singer = singerMapper.selectById(id);
        singerMapper.deleteById(id);
        Music music = new Music();
        Long defaultSingerId = 0L;
        music.setSingerId(defaultSingerId);
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Music::getSingerId,id);
        musicMapper.update(music,queryWrapper);
        aliOssUtil.delete(singer.getImage(),"image");
    }

    //歌手信息分页查询
    @Override
    public PageResult<Singer> page(PageParams pageParams, SingerPageQuery singerPageQuery) {
        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(singerPageQuery.getName()),Singer::getName,singerPageQuery.getName())
                .eq(StringUtils.isNotEmpty(singerPageQuery.getSex()),Singer::getSex,singerPageQuery.getSex());
        Page<Singer> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<Singer> singerPage = singerMapper.selectPage(page, queryWrapper);
        List<Singer> records = singerPage.getRecords();
        long total = singerPage.getTotal();
        PageResult<Singer> pageResult = new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());

        return pageResult;
    }

    //歌手数量查询
    @Override
    public Integer count() {
        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        Integer singerCount = singerMapper.selectCount(queryWrapper);
        return singerCount;
    }

    //查询所有歌手
    @Override
    public List<SingerVO> list() {
        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<>();
        List<SingerVO> singers = new ArrayList<>();
        for (Singer singer : singerMapper.selectList(queryWrapper)) {
            SingerVO singerVO = new SingerVO(singer.getId(),singer.getName());
            singers.add(singerVO);
        }
        return singers;
    }
}
