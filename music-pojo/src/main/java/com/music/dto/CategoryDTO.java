package com.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "对分类传的数据模型")
public class CategoryDTO  implements Serializable {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("分类名称")
    private String name;

}
