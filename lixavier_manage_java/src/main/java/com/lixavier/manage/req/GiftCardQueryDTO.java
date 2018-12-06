package com.lixavier.manage.req;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GiftCardQueryDTO extends QueryDTO {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String id;
            
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    private List<String> idList;

    public List<String> getIdList(){
        return idList;
    }
    public void setIdList(List<String> idList){
        this.idList = idList;
    }
    private String idLike;

    public String getIdLike(){
        return idLike;
    }
    public void setIdLike(String idLike){
        this.idLike = idLike;
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
    private BigDecimal priceFrom;

    public BigDecimal getPriceFrom(){
        return priceFrom;
    }
    public void setPriceFrom(BigDecimal priceFrom){
        this.priceFrom = priceFrom;
    }
            
    private BigDecimal priceTo;

    public BigDecimal getPriceTo(){
        return priceTo;
    }
    public void setPriceTo(BigDecimal priceTo){
        this.priceTo = priceTo;
    }
    private String useOrderNo;
            
    public String getUseOrderNo(){
        return useOrderNo;
    }
    public void setUseOrderNo(String useOrderNo){
        this.useOrderNo = useOrderNo;
    }
    private List<String> useOrderNoList;

    public List<String> getUseOrderNoList(){
        return useOrderNoList;
    }
    public void setUseOrderNoList(List<String> useOrderNoList){
        this.useOrderNoList = useOrderNoList;
    }
    private String useOrderNoLike;

    public String getUseOrderNoLike(){
        return useOrderNoLike;
    }
    public void setUseOrderNoLike(String useOrderNoLike){
        this.useOrderNoLike = useOrderNoLike;
    }
    private Date endTimeFrom;

    public Date getEndTimeFrom(){
        return endTimeFrom;
    }
    public void setEndTimeFrom(Date endTimeFrom){
        this.endTimeFrom = endTimeFrom;
    }
            
    private Date endTimeTo;

    public Date getEndTimeTo(){
        return endTimeTo;
    }
    public void setEndTimeTo(Date endTimeTo){
        this.endTimeTo = endTimeTo;
    }
    private Byte commonStatus;
            
    public Byte getCommonStatus(){
        return commonStatus;
    }
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
    }
    private List<Byte> commonStatusList;

    public List<Byte> getCommonStatusList(){
        return commonStatusList;
    }
    public void setCommonStatusList(List<Byte> commonStatusList){
        this.commonStatusList = commonStatusList;
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