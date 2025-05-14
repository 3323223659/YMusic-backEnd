package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "歌手信息分页查询数据模型")
public class SingerPageQuery {

    @ApiModelProperty("歌手姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;
}
