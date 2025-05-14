package com.music.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询歌单列表返回的数据格式")
public class PlaylistVO implements Serializable {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

}
