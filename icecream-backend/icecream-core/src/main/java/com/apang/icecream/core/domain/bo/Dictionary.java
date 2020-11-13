package com.apang.icecream.core.domain.bo;

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
@TableName("s_dictionary")
public class Dictionary extends BaseDto {

    private static final long serialVersionUID = 1L;

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    @TableField("typeName")
    private String typeName;

    @TableField("typeCode")
    private String typeCode;

    @TableField("dicName")
    private String dicName;

    @TableField("dicValue")
    private String dicValue;

    private String remarks;

    @TableField("logicId")
    private String logicId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getLogicId() {
        return logicId;
    }

    public void setLogicId(String logicId) {
        this.logicId = logicId;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
            "Id=" + id +
            ", typeName=" + typeName +
            ", typeCode=" + typeCode +
            ", dicName=" + dicName +
            ", dicValue=" + dicValue +
            ", remarks=" + remarks +
            ", logicId=" + logicId +
        "}";
    }
}
