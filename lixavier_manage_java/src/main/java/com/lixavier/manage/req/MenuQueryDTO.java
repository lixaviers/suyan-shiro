package com.lixavier.manage.req;

import com.lixavier.manage.resp.QueryResultODTO;

import java.util.Date;
import java.util.List;

public class MenuQueryDTO extends QueryDTO {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Long id;
            
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    private List<Long> idList;

    public List<Long> getIdList(){
        return idList;
    }
    public void setIdList(List<Long> idList){
        this.idList = idList;
    }
    private Long parentId;
            
    public Long getParentId(){
        return parentId;
    }
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    private List<Long> parentIdList;

    public List<Long> getParentIdList(){
        return parentIdList;
    }
    public void setParentIdList(List<Long> parentIdList){
        this.parentIdList = parentIdList;
    }
    private String name;
            
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    private List<String> nameList;

    public List<String> getNameList(){
        return nameList;
    }
    public void setNameList(List<String> nameList){
        this.nameList = nameList;
    }
    private String nameLike;

    public String getNameLike(){
        return nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }
    private String url;
            
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    private List<String> urlList;

    public List<String> getUrlList(){
        return urlList;
    }
    public void setUrlList(List<String> urlList){
        this.urlList = urlList;
    }
    private String urlLike;

    public String getUrlLike(){
        return urlLike;
    }
    public void setUrlLike(String urlLike){
        this.urlLike = urlLike;
    }
    private Byte type;
            
    public Byte getType(){
        return type;
    }
    public void setType(Byte type){
        this.type = type;
    }
    private List<Byte> typeList;

    public List<Byte> getTypeList(){
        return typeList;
    }
    public void setTypeList(List<Byte> typeList){
        this.typeList = typeList;
    }
    private String icon;
            
    public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    private List<String> iconList;

    public List<String> getIconList(){
        return iconList;
    }
    public void setIconList(List<String> iconList){
        this.iconList = iconList;
    }
    private String iconLike;

    public String getIconLike(){
        return iconLike;
    }
    public void setIconLike(String iconLike){
        this.iconLike = iconLike;
    }
    private Integer orderNum;
            
    public Integer getOrderNum(){
        return orderNum;
    }
    public void setOrderNum(Integer orderNum){
        this.orderNum = orderNum;
    }
    private List<Integer> orderNumList;

    public List<Integer> getOrderNumList(){
        return orderNumList;
    }
    public void setOrderNumList(List<Integer> orderNumList){
        this.orderNumList = orderNumList;
    }
    private Date createTimeFrom;

    public Date getCreateTimeFrom(){
        return createTimeFrom;
    }
    public void setCreateTimeFrom(Date createTimeFrom){
        this.createTimeFrom = createTimeFrom;
    }
            
    private Date createTimeTo;

    public Date getCreateTimeTo(){
        return createTimeTo;
    }
    public void setCreateTimeTo(Date createTimeTo){
        this.createTimeTo = createTimeTo;
    }
    private Date updateTimeFrom;

    public Date getUpdateTimeFrom(){
        return updateTimeFrom;
    }
    public void setUpdateTimeFrom(Date updateTimeFrom){
        this.updateTimeFrom = updateTimeFrom;
    }
            
    private Date updateTimeTo;

    public Date getUpdateTimeTo(){
        return updateTimeTo;
    }
    public void setUpdateTimeTo(Date updateTimeTo){
        this.updateTimeTo = updateTimeTo;
    }
    private Boolean isDeleted;
            
    public Boolean getIsDeleted(){
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    private List<Boolean> isDeletedList;

    public List<Boolean> getIsDeletedList(){
        return isDeletedList;
    }
    public void setIsDeletedList(List<Boolean> isDeletedList){
        this.isDeletedList = isDeletedList;
    }

    /**
    * The following custom property definition
    */
}