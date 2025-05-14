package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "歌曲分类信息分页查询数据模型")
public class CategoryPageQueryDTO {

    @ApiModelProperty("分类名称")
    private String name;

}
