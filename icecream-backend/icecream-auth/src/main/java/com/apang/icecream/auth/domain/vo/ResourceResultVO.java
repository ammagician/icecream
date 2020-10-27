
package com.apang.icecream.auth.domain.vo;

import com.apang.icecream.core.domain.bo.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * 资源结果组合模型.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月12日<br>
 * @since JDK 1.8.0
 */
public class ResourceResultVO implements Serializable {
    // 菜单模型.
    private List<Resource> menuList;
    // 功能模型.
    private List<Resource> funcList;

    public List<Resource> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Resource> menuList) {
        this.menuList = menuList;
    }

    public List<Resource> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<Resource> funcList) {
        this.funcList = funcList;
    }
}
