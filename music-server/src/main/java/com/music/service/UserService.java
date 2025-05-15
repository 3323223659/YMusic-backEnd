package com.music.service;

import com.music.dto.*;
import com.music.entity.User;
import com.music.model.PageParams;
import com.music.model.PageResult;

public interface UserService {

    //用户登录
    User login(LoginDTO userLoginDTO);

    //根据id查询用户信息
    User getById(Long id);

    //用户注册
    void insert(UserEnrollDTO userEnrollDTO);

    //修改用户信息
    void update(UserDTO userDTO);

    //用户注销
    void deleteById(Long musicId,String password);

    //修改用户密码
    void updatePassword(UserPasswordDTO userPasswordDTO);

    //用户分页查询
    PageResult<User> pageQuery(PageParams pageParams, UserPageQueryDTO userPageQueryDTO);

    //用户数量统计
    Integer count();
}
