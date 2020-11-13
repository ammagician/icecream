package com.apang.icecream.systemmanager.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.core.domain.bo.Resource;
import com.apang.icecream.core.services.IResourceService;
import com.apang.icecream.core.services.ITenantService;
import com.apang.icecream.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.apang.icecream.core.base.HttpResult;
import com.apang.icecream.core.base.LoggerManage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/api/sysmgr")
public class ResourceController {
	@Autowired
	private IResourceService resourcesService;

	@Autowired
	private ITenantService tenantService;

	@ApiOperation(value = "根据ID查询资源", notes = "根据ID查询资源")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getById(@RequestParam("id") String id) {
		Resource resources = resourcesService.getById(id);
		HttpResult result = HttpResult.ok();
		result.setData(resources);
		return result;
	}

	private QueryWrapper<Resource> getBaseQueryWrapper(Resource res) {
		QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>();
		if (!StringUtils.isEmpty(res.getName())) {
			wrapper.like("name", res.getName());
		}

		if (!StringUtils.isEmpty(res.getCode())) {
			wrapper.like("code", res.getCode());
		}

		if (!StringUtils.isEmpty(res.getPortalId())) {
			wrapper.eq("portalId", res.getPortalId());
		}

		if (!StringUtils.isEmpty(res.getParentId())) {
			wrapper.eq("parentId", res.getParentId());
		}

		if (res.getType() != null) {
			wrapper.eq("type", res.getType());
		}

		return wrapper;
	}

	private HttpResult getList(QueryWrapper<Resource> wrapper) {
		List<Resource> resourcess = resourcesService.getList(wrapper);

		HttpResult result = HttpResult.ok();
		result.setData(resourcess);
		return result;
	}

	private HttpResult getPage(int current, int size, QueryWrapper<Resource> wrapper) {
		Page<Resource> page = new Page<Resource>(current, size);
		IPage<Resource> resourcess = resourcesService.getPage(page, wrapper);

		HttpResult result = HttpResult.ok();
		result.setData(resourcess);
		return result;
	}

	private HttpResult save(Resource resources) {
		//resources.setId(UUID.randomUUID().toString());
		boolean success = resourcesService.save(resources);

		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	private HttpResult update(Resource resources) {
		boolean success = resourcesService.updateById(resources);
		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	private HttpResult del(String ids) {
		List<String> list = ListUtil.splitToList(ids, ",");
		boolean success = resourcesService.removeByIds(list);
		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	private HttpResult updateOrder(List<String> ids) {
		boolean success = resourcesService.updateOrder(ids);
		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	private List<Resource> groupByPortalId(List<Resource> list) {
		Map<String, Resource> hash = new HashMap<String, Resource>();
		for (Resource r : list) {
			if (r.getType() == 1) {
				Resource p = new Resource();
				p.setId(r.getId());
				p.setName(r.getName());
				p.setChildren(new ArrayList<Resource>());
				hash.put(r.getId().toString(), p);
				continue;
			}

			Resource p = hash.get(r.getPortalId());
			if (p != null) {
				p.getChildren().add(r);
			}
		}
		return new ArrayList<Resource>(hash.values());
	}

	@ApiOperation(value = "查询门户菜单", notes = "门户菜单")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/menu/tree", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getMenuTree(String portalId) {
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
		if (!StringUtils.isEmpty(portalId)) {
			queryWrapper.like("PortalId", portalId);
		}
		queryWrapper.in("TYPE", 3, 4);

		List<Resource> list = resourcesService.list(queryWrapper);
		List<Resource> data = ListUtil.listToTree(list);
		HttpResult result = HttpResult.ok();
		result.setData(data);
		return result;
	}

	@ApiOperation(value = "查询门户菜单", notes = "门户菜单")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/menu/permissionTree", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getMenuPermissionTree(String portalId) {
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
		if (!StringUtils.isEmpty(portalId)) {
			queryWrapper.like("PortalId", portalId);
		}
		queryWrapper.in("TYPE", 3, 4);
		List<Resource> list = resourcesService.list(queryWrapper);
		List<Resource> data = ListUtil.listToTree(list);

		List<String> ids = new ArrayList<String>();
		Map<String, List<Resource>> hash = new HashMap<String, List<Resource>>();
		// 根据relationId找到页面
		for (Resource r : list) {
			String rId = String.valueOf(r.getRelationId());
			if (r.getType() == 4 && !StringUtils.isEmpty(rId)) {
				ids.add(rId);
				List<Resource> group = hash.get(rId);
				if (group == null) {
					group = new ArrayList<Resource>();
					hash.put(rId, group);
				}
				group.add(r);
			}
		}

		if (ids.size() > 0) {
			Resource r = new Resource();
			r.setType(6);
			queryWrapper = new QueryWrapper<Resource>(r);
			queryWrapper.in("PARENTID", ids);
			List<Resource> btns = resourcesService.list(queryWrapper);
			for (Resource btn : btns) {
				String pId = String.valueOf(btn.getParentId());
				List<Resource> ps = hash.get(pId);
				for (Resource p : ps) {
					List<Resource> children = p.getChildren();
					if (children == null) {
						children = new ArrayList<Resource>();
						p.setChildren(children);
					}
					children.add(btn);
				}
			}
		}

		HttpResult result = HttpResult.ok();
		result.setData(data);
		return result;
	}

	@ApiOperation(value = "新增菜单", notes = "新增菜单")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/menu/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "菜单管理", description = "", operate = "新增菜单")
	public HttpResult saveMenu(@RequestBody Resource resources) {
		List<Integer> types = new ArrayList<Integer>();
		types.add(3);
		types.add(4);
		int size = resourcesService.getMaxOrder(resources.getParentId(), types);
		resources.setOrderNo(size + 1);
		return save(resources);
	}

	@ApiOperation(value = "更新菜单", notes = "更新菜单")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/menu/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "菜单管理", description = "", operate = "更新菜单")
	public HttpResult updateMenu(@RequestBody Resource resources) {
		return update(resources);
	}

	@ApiOperation(value = "根据ID删除菜单", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/menu/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "菜单管理", description = "", operate = "删除菜单")
	public HttpResult delMenu(@RequestParam("ids") String ids) {
		return del(ids);
	}

	@ApiOperation(value = "更新菜单顺序", notes = "更新菜单顺序")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/menu/order", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "菜单管理", description = "", operate = "更新菜单顺序")
	public HttpResult orderMenu(@RequestBody List<String> ids) {
		return updateOrder(ids);
	}

	@ApiOperation(value = "根据条件查询Portal", notes = "根据条件查询Portal")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/portal/list", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult getPortalList(@RequestBody Resource res) {
		res.setType(1);
		QueryWrapper<Resource> queryWrapper = getBaseQueryWrapper(res);
		return getList(queryWrapper);
	}

	@ApiOperation(value = "根据条件查询Portal", notes = "根据条件查询Portal")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/portal/listByTenant", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult getPortalListByTenant(@RequestBody Resource res) {
		res.setType(1);
		QueryWrapper<Resource> queryWrapper = getBaseQueryWrapper(res);
		int tenantId = AuthenticationUtil.getTenantId();
		List<String> portals = tenantService.getDetailById(tenantId).getPortals();
		queryWrapper.in("id", portals);
		return getList(queryWrapper);
	}

	@ApiOperation(value = "根据条件分页查询Portal", notes = "根据条件分页查询Portal")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/portal/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPortalPage(int current, int size, Resource res) {
		res.setType(1);
		QueryWrapper<Resource> wrapper = getBaseQueryWrapper(res);
		return getPage(current, size, wrapper);
	}

	@ApiOperation(value = "新增Portal", notes = "新增Portal")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/portal/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "Portal管理", description = "", operate = "新增Portal")
	public HttpResult savePortal(@RequestBody Resource resources) {
		resources.setType(1);
		return save(resources);
	}

	@ApiOperation(value = "更新Portal", notes = "更新Portal")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/portal/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "Portal管理", description = "", operate = "更新Portal")
	public HttpResult updatePortal(@RequestBody Resource resources) {
		resources.setType(1);
		return update(resources);
	}

	@ApiOperation(value = "根据ID删除Portal", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/portal/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "Portal管理", description = "", operate = "删除Portal")
	public HttpResult delPortal(@RequestParam("ids") String ids) {
		return del(ids);
	}

	@ApiOperation(value = "查询分组Portlet, 按照portal分组", notes = "查询分组Portlet")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/portlet/group", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPortletGroup() {
		QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>();
		wrapper.in("TYPE", 1, 2);
		List<Resource> list = resourcesService.list(wrapper);
		List<Resource> data = groupByPortalId(list);
		return HttpResult.ok(data);
	}

	@ApiOperation(value = "根据条件查询Portlet", notes = "根据条件查询Portlet")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/portlet/list", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult getPortletList(@RequestBody Resource res) {
		res.setType(2);
		QueryWrapper<Resource> queryWrapper = getBaseQueryWrapper(res);
		return getList(queryWrapper);
	}

	@ApiOperation(value = "根据条件分页查询Portlet", notes = "根据条件分页查询Portlet")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/portlet/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPortletPage(int current, int size, Resource res) {
		res.setType(2);
		QueryWrapper<Resource> wrapper = getBaseQueryWrapper(res);

		return getPage(current, size, wrapper);
	}

	@ApiOperation(value = "新增Portlet", notes = "新增Portlet")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/portlet/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "Portlet管理", description = "", operate = "新增Portlet")
	public HttpResult savePortlet(@RequestBody Resource resources) {
		resources.setType(2);
		return save(resources);
	}

	@ApiOperation(value = "更新Portlet", notes = "更新Portlet")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/portlet/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "Portlet管理", description = "", operate = "更新Portlet")
	public HttpResult updatePortlet(@RequestBody Resource resources) {
		resources.setType(2);
		return update(resources);
	}

	@ApiOperation(value = "根据ID删除Portlet", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/portlet/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "Portlet管理", description = "", operate = "删除Portlet")
	public HttpResult delPortlet(@RequestParam("ids") String ids) {
		return del(ids);
	}


	@ApiOperation(value = "查询分组Page,按照portal分组", notes = "查询分组Page")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/page/group", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPageGroup() {
		QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>();
		wrapper.in("TYPE", 1, 5);
		wrapper.orderByAsc("TYPE");
		List<Resource> list = resourcesService.list(wrapper);
		
		List<Resource> data = groupByPortalId(list);
		return HttpResult.ok(data);
	}

	@ApiOperation(value = "根据条件查询Page", notes = "根据条件查询Page")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/page/list", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult getPageList(@RequestBody Resource res) {
		res.setType(5);
		QueryWrapper<Resource> queryWrapper = getBaseQueryWrapper(res);
		return getList(queryWrapper);
	}

	@ApiOperation(value = "根据条件分页查询Page", notes = "根据条件分页查询Page")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/page/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPagePage(int current, int size, Resource res) {
		res.setType(5);
		QueryWrapper<Resource> wrapper = getBaseQueryWrapper(res);
		return getPage(current, size, wrapper);
	}

	@ApiOperation(value = "新增Page", notes = "新增Page")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/page/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "Page管理", description = "", operate = "新增Page")
	public HttpResult savePage(@RequestBody Resource resources) {
		resources.setType(5);
		return save(resources);
	}

	@ApiOperation(value = "更新Page", notes = "更新Page")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/page/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "Page管理", description = "", operate = "更新Page")
	public HttpResult updatePage(@RequestBody Resource resources) {
		resources.setType(5);
		return update(resources);
	}

	@ApiOperation(value = "根据ID删除Page", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/page/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "Page管理", description = "", operate = "删除Page")
	public HttpResult delPage(@RequestParam("ids") String ids) {
		List<String> list = ListUtil.splitToList(ids, ",");
		QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>();
		wrapper.in("PARENTID", list);
		//先删除按钮
		if (resourcesService.remove(wrapper)) {
			return del(ids);
		}
		return HttpResult.error("删除失败");
	}

	@ApiOperation(value = "根据条件查询按钮资源", notes = "根据条件查询按钮资源")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/btn/list", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getBtnList(@RequestParam(value = "page", required = false) int page) {
		Resource res = new Resource();
		res.setParentId(page);
		res.setType(6);
		List<Resource> buttons = resourcesService.getList(getBaseQueryWrapper(res));
		HttpResult result = HttpResult.ok();
		result.setData(buttons);
		return result;
	}

	@ApiOperation(value = "新增按钮资源", notes = "新增按钮资源")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/btn/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "按钮资源管理", description = "", operate = "新增按钮")
	public HttpResult saveBtn(@RequestBody Resource resources) {
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
		queryWrapper.eq("code", resources.getCode());
		queryWrapper.eq("type", 6);
		if (resourcesService.getOne(queryWrapper) != null) {
			return HttpResult.error("该授权编码已经存在");
		}

		resources.setType(6);
		return save(resources);
	}

	@ApiOperation(value = "更新按钮资源", notes = "更新按钮资源")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/btn/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "按钮资源管理", description = "", operate = "更新按钮")
	public HttpResult updateBtn(@RequestBody Resource resources) {
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
		queryWrapper.eq("code", resources.getCode());
		queryWrapper.eq("type", 6);
		if (resourcesService.getOne(queryWrapper) != null) {
			return HttpResult.error("该授权编码已经存在");
		}

		resources.setType(6);
		return update(resources);
	}

	@ApiOperation(value = "根据ID删除按钮资源", notes = "逗号按钮资源")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/btn/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "按钮资源管理", description = "", operate = "删除按钮")
	public HttpResult delBtn(@RequestParam("ids") String ids) {
		return del(ids);
	}
}
