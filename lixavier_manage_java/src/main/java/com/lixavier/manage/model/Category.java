package com.lixavier.manage.model;

import java.util.Date;
import java.util.List;

public class Category {

    public static final String ID = "ID";
    public static final String PARENT_ID = "父类id";
    public static final String NAME = "名称";
    public static final String ORDER_NUM = "排序";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除：0.未删除，1.已删除";


    /**
     * ID
     */
    private Long id;

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除：0.未删除，1.已删除
     */
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
    * ----------------------------------------------------------------------------------------
    * ----------------------------------------------------------------------------------------
    * ----------------------------------------------------------------------------------------
    */


    private List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}