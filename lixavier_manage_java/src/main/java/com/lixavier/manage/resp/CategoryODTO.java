package com.lixavier.manage.resp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("类目")
public class CategoryODTO implements Serializable {

    /**   
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 父类id
     */
    @ApiModelProperty("父类id")
    private Long parentId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getParentId(){
        return parentId;
    }
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }



    /**
     * ----------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------
     * ----------------------------------------------------------------------------------------
     */



    private List<CategoryODTO> children;

    public List<CategoryODTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryODTO> children) {
        this.children = children;
    }
}