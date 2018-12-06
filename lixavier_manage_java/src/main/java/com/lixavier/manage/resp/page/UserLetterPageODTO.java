package com.lixavier.manage.resp.page;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("站内信")
public class UserLetterPageODTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Byte type;

    private String typeDesc;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Byte commonStatus;

    /**
     * 是否已读
     */
    @ApiModelProperty("是否已读")
    private Boolean isRead;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(Byte commonStatus) {
        this.commonStatus = commonStatus;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}