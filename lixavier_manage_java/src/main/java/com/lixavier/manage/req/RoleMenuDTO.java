package com.lixavier.manage.req;

import java.io.Serializable;
import java.util.Date;

public class RoleMenuDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * 
     */
    private Long id;
            
    /**
     * 角色ID
     */
    private Long roleId;
            
    /**
     * 菜单ID
     */
    private Long menuId;
            
    /**
     * 创建时间
     */
    private Date createTime;
            
    /**
     * 更新时间
     */
    private Date updateTime;
        
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getRoleId(){
        return roleId;
    }
    
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    
    public Long getMenuId(){
        return menuId;
    }
    
    public void setMenuId(Long menuId){
        this.menuId = menuId;
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
    
    /**
    * The following custom property definition
    */
}