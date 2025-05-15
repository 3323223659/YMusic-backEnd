package com.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.music.constant.MessageConstant;
import com.music.dto.LoginDTO;
import com.music.entity.Admin;
import com.music.exception.PasswordErrorException;
import com.music.mapper.AdminMapper;
import com.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //管理员登录
    @Override
    public Admin login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        Admin admin = adminMapper.selectOne(queryWrapper);
        //根据管理员用户名查询不到，用户名不存在
        if (admin == null){
            throw new PasswordErrorException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码错误
        if (!password.equals(admin.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return admin;
    }

}
