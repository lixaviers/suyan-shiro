package com.lixavier.manage.model;

import java.util.Date;

public class UserAddress {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String CONSIGNEE = "姓名";
    public static final String MOBILE = "手机号";
    public static final String PROVINCE_ID = "省份id";
    public static final String PROVINCE_NAME = "省份名称";
    public static final String CITY_ID = "城市id";
    public static final String CITY_NAME = "城市名称";
    public static final String AREA_ID = "区id";
    public static final String AREA_NAME = "区名称";
    public static final String ADDRESS = "详细地址";
    public static final String IS_DEFAULT = "是否默认";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除：0.未删除，1.已删除";


    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String consignee;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 省份id
     */
    private Integer provinceId;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 区id
     */
    private Integer areaId;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 是否默认
     */
    private Boolean isDefault;

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

    public Long getUserId(){
        return userId;
    }
    
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getConsignee(){
        return consignee;
    }
    
    public void setConsignee(String consignee){
        this.consignee = consignee;
    }

    public String getMobile(){
        return mobile;
    }
    
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public Integer getProvinceId(){
        return provinceId;
    }
    
    public void setProvinceId(Integer provinceId){
        this.provinceId = provinceId;
    }

    public String getProvinceName(){
        return provinceName;
    }
    
    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }

    public Integer getCityId(){
        return cityId;
    }
    
    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }

    public String getCityName(){
        return cityName;
    }
    
    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public Integer getAreaId(){
        return areaId;
    }
    
    public void setAreaId(Integer areaId){
        this.areaId = areaId;
    }

    public String getAreaName(){
        return areaName;
    }
    
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }

    public Boolean getIsDefault(){
        return isDefault;
    }
    
    public void setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
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

}