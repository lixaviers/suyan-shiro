package com.lixavier.manage.req;

import com.lixavier.manage.resp.QueryResultODTO;

import java.util.Date;
import java.util.List;

public class UserRoleQueryDTO extends QueryDTO {
    
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
    private Long userId;
            
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    private List<Long> userIdList;

    public List<Long> getUserIdList(){
        return userIdList;
    }
    public void setUserIdList(List<Long> userIdList){
        this.userIdList = userIdList;
    }
    private Long roleId;
            
    public Long getRoleId(){
        return roleId;
    }
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    private List<Long> roleIdList;

    public List<Long> getRoleIdList(){
        return roleIdList;
    }
    public void setRoleIdList(List<Long> roleIdList){
        this.roleIdList = roleIdList;
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

    /**
    * The following custom property definition
    */
}