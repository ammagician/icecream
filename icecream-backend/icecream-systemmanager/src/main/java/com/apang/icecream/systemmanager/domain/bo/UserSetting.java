package com.apang.icecream.systemmanager.domain.bo;

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
 * @since 2019-09-16
 */
@TableName("s_user_setting")
public class UserSetting extends BaseDto {

    private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId("Id")
	private String Id;

    /**
	 * 主题
	 */
    private String theme;


    /**
	 * 当前门户ID
	 */
	@TableField("currentPortal")
	private String currentPortal;


	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCurrentPortal() {
		return currentPortal;
	}

	public void setCurrentPortal(String currentPortal) {
		this.currentPortal = currentPortal;
	}
}
