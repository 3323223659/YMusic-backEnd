package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.constant.MessageConstant;
import com.music.dto.*;
import com.music.entity.User;
import com.music.entity.UserMusic;
import com.music.exception.PasswordErrorException;
import com.music.mapper.UserMapper;
import com.music.mapper.UserMusicMapper;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMusicMapper userMusicMapper;

    //用户登录
    @Override
    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(queryWrapper);
        //根据管理员用户名查询不到，用户名不存在
        if (user == null){
            throw new PasswordErrorException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码错误
        if (!password.equals(user.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        user.setLoginTime(LocalDateTime.now());
        userMapper.updateById(user);
        return user;
    }

    //根据id查询用户信息
    @Override
    public User getById(Long id) {
        User user = userMapper.selectById(id);
        user.setPassword("******");
        return user;
    }

    //用户注册
    @Override
    public void insert(UserEnrollDTO userEnrollDTO) {
       if (!userEnrollDTO.getPassword().equals(userEnrollDTO.getConformPassword())){
           //两次输入的密码不同
           throw new PasswordErrorException(MessageConstant.PASSWORD_DIFFERENT);
       }
        User user = new User();
        BeanUtils.copyProperties(userEnrollDTO,user);
        user.setPassword(DigestUtils.md5DigestAsHex(userEnrollDTO.getPassword().getBytes()));
        user.setName(userEnrollDTO.getUsername());
//        user.setCreateTime(LocalDateTime.now());
        user.setLoginTime(LocalDateTime.now());

        userMapper.insert(user);
    }

    //修改用户信息
    @Override
    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);

        userMapper.updateById(user);
    }

    //用户注销
    @Override
    public void deleteById(Long musicId,String password) {
        User user = userMapper.selectById(musicId);
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!passwordMd5.equals(user.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        userMapper.deleteById(musicId);
        LambdaQueryWrapper<UserMusic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserMusic::getUserId,musicId);
        userMusicMapper.delete(queryWrapper);
    }

    //修改用户密码
    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        User user = userMapper.selectById(userPasswordDTO.getId());
        String prePassword = DigestUtils.md5DigestAsHex(userPasswordDTO.getPassword().getBytes());
        if (!prePassword.equals(user.getPassword())){
            //原密码不正确
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        if (!userPasswordDTO.getNewPassword().equals(userPasswordDTO.getConformPassword())){
            //两次输入的密码不同
            throw new PasswordErrorException(MessageConstant.PASSWORD_DIFFERENT);
        }
        String newPassword = DigestUtils.md5DigestAsHex(userPasswordDTO.getNewPassword().getBytes());
        user.setPassword(newPassword);

        userMapper.updateById(user);
    }

    //用户分页查询
    @Override
    public PageResult<User> pageQuery(PageParams pageParams, UserPageQueryDTO userPageQueryDTO) {
        //查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(userPageQueryDTO.getName()),User::getName, userPageQueryDTO.getName())
                .eq(StringUtils.isNotEmpty(userPageQueryDTO.getSex()),User::getSex, userPageQueryDTO.getSex());
        Page<User> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<User> selectPage = userMapper.selectPage(page, queryWrapper);
        List<User> records = selectPage.getRecords();
        long total = selectPage.getTotal();
        PageResult<User> pageResult = new PageResult<>(records,total, pageParams.getPageNo(), pageParams.getPageSize());

        return pageResult;
    }

    //用户数量统计
    @Override
    public Integer count() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Integer userCount = userMapper.selectCount(queryWrapper);
        return userCount;
    }

}
