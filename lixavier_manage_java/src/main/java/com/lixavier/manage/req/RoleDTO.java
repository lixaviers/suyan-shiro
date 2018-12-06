package com.lixavier.manage.req;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * 
     */
    private Long id;
            
    /**
     * 角色名称
     */
    private String roleName;
            
    /**
     * 备注
     */
    private String remark;
            
    /**
     * 创建时间
     */
    private Date createTime;
            
    /**
     * 更新时间
     */
    private Date updateTime;
            
    /**
     * 是否删除 0.否 1.是
     */
    private Boolean isDeleted;
        
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getRoleName(){
        return roleName;
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    
    public String getRemark(){
        return remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
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


    private List<Long> menuIdList;

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}