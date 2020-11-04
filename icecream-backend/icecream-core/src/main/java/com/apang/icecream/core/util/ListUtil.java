package com.apang.icecream.core.util;

import com.apang.icecream.core.domain.bo.Menu;

import java.lang.reflect.Method;
import java.util.*;

public class ListUtil {

	public static <T> List<T> listToTree(List<T> list) {
		List<T> result = new ArrayList<T>();
		try {
			Map<String, T> hash = new HashMap<String, T>();
			for (T r : list) {
				Method getId = r.getClass().getDeclaredMethod("getId");
				String id = (String) getId.invoke(r);
				hash.put(id, r);
			}

			for (T r : list) {
				Method getParentId = r.getClass().getDeclaredMethod("getParentId");
				String parentId = (String) getParentId.invoke(r);
				T p = hash.get(parentId);
				if (p == null) {
					result.add(r);
				} else {
					Method getChildren = p.getClass().getDeclaredMethod("getChildren");
					List<T> children = (List<T>) getChildren.invoke(p);
					if (children == null) {
						children = new ArrayList<T>();
						Method setChildren = p.getClass().getDeclaredMethod("setChildren");
						setChildren.invoke(p, children);
					}
					children.add(r);
				}
			}
		} catch (Exception e) {
			System.out.println("转换异常");
		}

		return result;
	}

	public static List<Menu> buildMenuTree(List<Menu> menuList) {
		Map<String, Menu> treeMap = toMenuMap(menuList);
		return buildTree(treeMap);
	}



	private static List<Menu> buildTree(Map<String, Menu> treeMap) {
		List<Menu> menuTreeList = new ArrayList<Menu>();
		for (Map.Entry<String, Menu> en : treeMap.entrySet()) {
			Menu m = en.getValue();
			Menu parent = treeMap.get(m.getParentId());
			if(null != parent) {
				List<Menu> children = parent.getChildren();
				if(null == children) {
					children = new ArrayList<Menu>();
					parent.setChildren(children);
				}
				children.add(m);
			}else {
				menuTreeList.add(m);
			}
		}
		return menuTreeList;
	}

	private static Map<String, Menu> toMenuMap(final List<Menu> menuList) {
		final Map<String, Menu> map= new HashMap<String, Menu>();
		if(null != menuList  && menuList.size() > 0) {
			for (Menu m : menuList) {
				map.put(m.getId(), m);
			}
		}
		return map;
	}

	public static List<String> splitToList(String str, String sp){
		String[] arr = str.split(sp);
		return Arrays.asList(arr);
	}
}
