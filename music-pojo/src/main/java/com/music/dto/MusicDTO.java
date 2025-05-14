package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "对歌曲传的数据模型")
public class MusicDTO {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("歌曲名称")
    private String name;

    @ApiModelProperty("歌手id")
    private Long singerId;

    @ApiModelProperty("歌曲分类id")
    private Long categoryId;

    @ApiModelProperty("存储路径")
    private String path;

}
