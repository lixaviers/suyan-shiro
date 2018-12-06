package com.lixavier.manage.req;

import com.lixavier.manage.resp.QueryResultODTO;

import java.util.Date;
import java.util.List;

public class RoleQueryDTO extends QueryDTO {
    
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
    private String roleName;
            
    public String getRoleName(){
        return roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    private List<String> roleNameList;

    public List<String> getRoleNameList(){
        return roleNameList;
    }
    public void setRoleNameList(List<String> roleNameList){
        this.roleNameList = roleNameList;
    }
    private String roleNameLike;

    public String getRoleNameLike(){
        return roleNameLike;
    }
    public void setRoleNameLike(String roleNameLike){
        this.roleNameLike = roleNameLike;
    }
    private String remark;
            
    public String getRemark(){
        return remark;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    private List<String> remarkList;

    public List<String> getRemarkList(){
        return remarkList;
    }
    public void setRemarkList(List<String> remarkList){
        this.remarkList = remarkList;
    }
    private String remarkLike;

    public String getRemarkLike(){
        return remarkLike;
    }
    public void setRemarkLike(String remarkLike){
        this.remarkLike = remarkLike;
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