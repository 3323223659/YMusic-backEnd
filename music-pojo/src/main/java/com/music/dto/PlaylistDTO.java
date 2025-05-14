package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "对歌单传的数据模型")
public class PlaylistDTO {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("歌单名称")
    private String name;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("描述信息")
    private String description;

}
