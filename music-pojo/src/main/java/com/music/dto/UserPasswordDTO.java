package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "修改用户密码所传的数据模型")
public class UserPasswordDTO {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("原密码")
    private String password;

    @ApiModelProperty("新密码")
    private String newPassword;

    @ApiModelProperty("确认新密码")
    private String conformPassword;
}
