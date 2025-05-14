package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "歌曲信息分页查询数据模型")
public class MusicQueryDTO {

    @ApiModelProperty("歌曲名称")
    private String name;

    @ApiModelProperty("歌手id")
    private Long singerId;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("歌单id")
    private Long playlistId;
}
