package com.music.service;

import com.music.dto.LoginDTO;
import com.music.entity.Admin;

public interface AdminService {

    //管理员登录
    Admin login(LoginDTO loginDTO);

}
