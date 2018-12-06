package com.lixavier.manage.req;

import java.util.Date;
import java.util.List;

public class UserAddressQueryDTO extends QueryDTO {
    
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
    private String consignee;
            
    public String getConsignee(){
        return consignee;
    }
    public void setConsignee(String consignee){
        this.consignee = consignee;
    }
    private List<String> consigneeList;

    public List<String> getConsigneeList(){
        return consigneeList;
    }
    public void setConsigneeList(List<String> consigneeList){
        this.consigneeList = consigneeList;
    }
    private String consigneeLike;

    public String getConsigneeLike(){
        return consigneeLike;
    }
    public void setConsigneeLike(String consigneeLike){
        this.consigneeLike = consigneeLike;
    }
    private String mobile;
            
    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    private List<String> mobileList;

    public List<String> getMobileList(){
        return mobileList;
    }
    public void setMobileList(List<String> mobileList){
        this.mobileList = mobileList;
    }
    private String mobileLike;

    public String getMobileLike(){
        return mobileLike;
    }
    public void setMobileLike(String mobileLike){
        this.mobileLike = mobileLike;
    }
    private Integer provinceId;
            
    public Integer getProvinceId(){
        return provinceId;
    }
    public void setProvinceId(Integer provinceId){
        this.provinceId = provinceId;
    }
    private List<Integer> provinceIdList;

    public List<Integer> getProvinceIdList(){
        return provinceIdList;
    }
    public void setProvinceIdList(List<Integer> provinceIdList){
        this.provinceIdList = provinceIdList;
    }
    private String provinceName;
            
    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }
    private List<String> provinceNameList;

    public List<String> getProvinceNameList(){
        return provinceNameList;
    }
    public void setProvinceNameList(List<String> provinceNameList){
        this.provinceNameList = provinceNameList;
    }
    private String provinceNameLike;

    public String getProvinceNameLike(){
        return provinceNameLike;
    }
    public void setProvinceNameLike(String provinceNameLike){
        this.provinceNameLike = provinceNameLike;
    }
    private Integer cityId;
            
    public Integer getCityId(){
        return cityId;
    }
    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }
    private List<Integer> cityIdList;

    public List<Integer> getCityIdList(){
        return cityIdList;
    }
    public void setCityIdList(List<Integer> cityIdList){
        this.cityIdList = cityIdList;
    }
    private String cityName;
            
    public String getCityName(){
        return cityName;
    }
    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    private List<String> cityNameList;

    public List<String> getCityNameList(){
        return cityNameList;
    }
    public void setCityNameList(List<String> cityNameList){
        this.cityNameList = cityNameList;
    }
    private String cityNameLike;

    public String getCityNameLike(){
        return cityNameLike;
    }
    public void setCityNameLike(String cityNameLike){
        this.cityNameLike = cityNameLike;
    }
    private Integer areaId;
            
    public Integer getAreaId(){
        return areaId;
    }
    public void setAreaId(Integer areaId){
        this.areaId = areaId;
    }
    private List<Integer> areaIdList;

    public List<Integer> getAreaIdList(){
        return areaIdList;
    }
    public void setAreaIdList(List<Integer> areaIdList){
        this.areaIdList = areaIdList;
    }
    private String areaName;
            
    public String getAreaName(){
        return areaName;
    }
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }
    private List<String> areaNameList;

    public List<String> getAreaNameList(){
        return areaNameList;
    }
    public void setAreaNameList(List<String> areaNameList){
        this.areaNameList = areaNameList;
    }
    private String areaNameLike;

    public String getAreaNameLike(){
        return areaNameLike;
    }
    public void setAreaNameLike(String areaNameLike){
        this.areaNameLike = areaNameLike;
    }
    private String address;
            
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    private List<String> addressList;

    public List<String> getAddressList(){
        return addressList;
    }
    public void setAddressList(List<String> addressList){
        this.addressList = addressList;
    }
    private String addressLike;

    public String getAddressLike(){
        return addressLike;
    }
    public void setAddressLike(String addressLike){
        this.addressLike = addressLike;
    }
    private Boolean isDefault;
            
    public Boolean getIsDefault(){
        return isDefault;
    }
    public void setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
    }
    private List<Boolean> isDefaultList;

    public List<Boolean> getIsDefaultList(){
        return isDefaultList;
    }
    public void setIsDefaultList(List<Boolean> isDefaultList){
        this.isDefaultList = isDefaultList;
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