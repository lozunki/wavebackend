package org.lozunki.wavebackend.product.entity.DTO;


import lombok.Data;

import java.util.List;

@Data
public class ParentProductNameDTO {

    private String parentName; // 父项目名称
    private List<String> childNames; // 子项目名称列表

}
