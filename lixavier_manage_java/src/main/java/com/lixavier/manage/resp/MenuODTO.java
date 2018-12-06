package com.lixavier.manage.resp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("菜单")
public class MenuODTO implements Serializable {

    /**   
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;


    /**
     * 
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
    @ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单URL
     */
    @ApiModelProperty("菜单URL")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @ApiModelProperty("类型   0：目录   1：菜单   2：按钮")
    private Byte type;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 是否删除 0.否 1.是
     */
    @ApiModelProperty("是否删除 0.否 1.是")
    private Boolean isDeleted;


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

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }

    public String getPerms(){
        return perms;
    }
    public void setPerms(String perms){
        this.perms = perms;
    }

    public Byte getType(){
        return type;
    }
    public void setType(Byte type){
        this.type = type;
    }

    public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }

    public Integer getOrderNum(){
        return orderNum;
    }
    public void setOrderNum(Integer orderNum){
        this.orderNum = orderNum;
    }

    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Boolean getIsDeleted(){
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }

    /**
    * The following custom property definition
    */


    private List<MenuODTO> children;

    public List<MenuODTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuODTO> children) {
        this.children = children;
    }
}