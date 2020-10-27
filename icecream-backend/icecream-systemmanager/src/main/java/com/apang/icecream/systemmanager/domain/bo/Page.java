package com.apang.icecream.systemmanager.domain.bo;

import java.io.Serializable;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author andy pang
 * @since 2020-09-24
 */
@TableName("s_page")
public class Page  extends BaseDto {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String code;

    private String url;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
