package com.lixavier.manage.model;

import java.math.BigDecimal;
import java.util.Date;

public class GiftCard {

    public static final String ID = "ID";
    public static final String USER_ID = "用户iid";
    public static final String PRICE = "价格";
    public static final String USE_ORDER_NO = "使用订单号";
    public static final String END_TIME = "失效时间";
    public static final String COMMON_STATUS = "状态";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除：0.未删除，1.已删除";


    /**
     * ID
     */
    private String id;

    /**
     * 用户iid
     */
    private Long userId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 使用订单号
     */
    private String useOrderNo;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private Byte commonStatus;

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
    

    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public Long getUserId(){
        return userId;
    }
    
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public BigDecimal getPrice(){
        return price;
    }
    
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public String getUseOrderNo(){
        return useOrderNo;
    }
    
    public void setUseOrderNo(String useOrderNo){
        this.useOrderNo = useOrderNo;
    }

    public Date getEndTime(){
        return endTime;
    }
    
    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }

    public Byte getCommonStatus(){
        return commonStatus;
    }
    
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
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


	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}