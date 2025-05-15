package com.music.controller.manage;

import com.music.dto.*;
import com.music.entity.User;
import com.music.model.PageParams;
import com.music.model.PageResult;
import com.music.result.Result;
import com.music.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("manageUserController")
@RequestMapping("/music/manage/user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/page")
    @ApiOperation("用户分页查询")
    public Result<PageResult<User>> page(PageParams pageParams, UserPageQueryDTO userPageQueryDTO){
        log.info("分页查询用户信息：{},{}",pageParams, userPageQueryDTO);
        PageResult<User> pageResult = userService.pageQuery(pageParams, userPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/count")
    @ApiOperation("统计用户数量")
    public Result<Integer> count(){
        log.info("用户数量查询");
        Integer userCount = userService.count();
        return Result.success(userCount);
    }
}
