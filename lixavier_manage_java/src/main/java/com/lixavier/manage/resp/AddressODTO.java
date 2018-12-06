package com.lixavier.manage.resp;

import java.io.Serializable;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("地址")
public class AddressODTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;


    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
    * The following custom property definition
    */
    private List<AddressODTO> children;

    public List<AddressODTO> getChildren() {
        return children;
    }

    public void setChildren(List<AddressODTO> children) {
        this.children = children;
    }

}