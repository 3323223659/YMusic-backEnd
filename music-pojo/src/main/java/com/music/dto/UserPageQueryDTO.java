package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户信息分页查询数据模型")
public class UserPageQueryDTO {

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户性别")
    private String sex;
}
