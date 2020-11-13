package com.apang.icecream.core.domain.bo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.apang.icecream.core.base.BaseDto;

/**
 * <p>
 * 
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-09
 */
@TableName("s_document")
public class Document extends BaseDto {

    private static final long serialVersionUID = 1L;

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    @TableField("uuidFileName")
    private String uuidFileName;

    @TableField("relativeDir")
    private String relativeDir;

    @TableField("oFileName")
    private String oFileName;

    @TableField("uploadTime")
    private LocalDateTime uploadTime;

    @TableField("bizCode")
    private String bizCode;

    private String suffix;

    @TableField("fileSize")
    private Long fileSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }
    public String getUuidFileName() {
        return uuidFileName;
    }

    public void setUuidFileName(String uuidFileName) {
        this.uuidFileName = uuidFileName;
    }
    public String getRelativeDir() {
        return relativeDir;
    }

    public void setRelativeDir(String relativeDir) {
        this.relativeDir = relativeDir;
    }
    public String getoFileName() {
        return oFileName;
    }

    public void setoFileName(String oFileName) {
        this.oFileName = oFileName;
    }
    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Document{" +
            "Id=" + id +
            ", uuidFileName=" + uuidFileName +
            ", relativeDir=" + relativeDir +
            ", oFileName=" + oFileName +
            ", uploadTime=" + uploadTime +
            ", bizCode=" + bizCode +
            ", suffix=" + suffix +
            ", fileSize=" + fileSize +
        "}";
    }
}
