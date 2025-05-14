package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "歌单信息分页查询数据模型")
public class PlaylistPageQueryDTO {

    @ApiModelProperty("歌单名称")
    private String name;

}
