package com.music.controller.client;

import com.music.constant.JwtClaimsConstant;
import com.music.context.BaseContext;
import com.music.dto.LoginDTO;
import com.music.dto.UserDTO;
import com.music.dto.UserEnrollDTO;
import com.music.dto.UserPasswordDTO;
import com.music.entity.User;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.properties.JwtProperties;
import com.music.result.Result;
import com.music.service.UserService;
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
@RestController("clientUserController")
@RequestMapping("/music/client/user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<LoginVo> login(@RequestBody LoginDTO loginDTO){
        log.info("用户登录：{}",loginDTO);
        User user = userService.login(loginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        LoginVo loginVo = LoginVo.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .name(user.getName())
                .token(token)
                .build();
        return Result.success(loginVo);
    }

    @PutMapping("/login")
    @ApiOperation("用户注册")
    public Result<String> register(@RequestBody UserEnrollDTO userEnrollDTO){
        log.info("注册用户：{}",userEnrollDTO);
        userService.insert(userEnrollDTO);
        return Result.success("注册成功");
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户信息")
    public Result<User> getById(@PathVariable("id") Long id){
        log.info("根据id查询用户信息：{}",id);
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping
    @ApiOperation("修改用户信息")
    public Result<String> update(@RequestBody UserDTO userDTO){
        log.info("修改用户信息：{}",userDTO);
        userService.update(userDTO);
        return Result.success("修改成功");
    }

    @PutMapping("/password")
    @ApiOperation("修改用户密码")
    public Result<String> password(@RequestBody UserPasswordDTO userPasswordDTO){
        log.info("修改用户密码：{}",userPasswordDTO);
        userService.updatePassword(userPasswordDTO);
        return Result.success("修改密码成功");
    }

    @DeleteMapping
    @ApiOperation("注销用户信息")
    public Result<String> deleteById(String password){
        Long userId = BaseContext.getCurrentId();
        log.info("注销用户信息：{}",userId);
        userService.deleteById(userId,password);
        return Result.success("注销成功");
    }

}
