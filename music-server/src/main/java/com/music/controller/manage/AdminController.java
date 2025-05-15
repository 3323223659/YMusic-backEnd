package com.music.controller.manage;

import com.music.constant.JwtClaimsConstant;
import com.music.dto.LoginDTO;
import com.music.entity.Admin;
import com.music.properties.JwtProperties;
import com.music.result.Result;
import com.music.service.AdminService;
import com.music.utils.JwtUtil;
import com.music.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/music/manage/admin")
@Api(tags = "管理员相关接口")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<LoginVo> login(@RequestBody LoginDTO loginDTO){
        log.info("管理员登录：{}",loginDTO);
        Admin admin = adminService.login(loginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        LoginVo loginVo = LoginVo.builder()
                .id(admin.getId())
                .userName(admin.getUsername())
                .name(admin.getName())
                .token(token)
                .build();
        return Result.success(loginVo);
    }

}
