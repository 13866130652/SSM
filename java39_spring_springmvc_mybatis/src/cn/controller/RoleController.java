package cn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.pojo.Role;
import cn.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private RoleService roleService;

	@RequestMapping(value = "/getRoles", produces = "text/html;charset=UTF-8")
	// produces = "text/html;charset=UTF-8",解决返回json乱码问题
	@ResponseBody
	public Object getRoles() {
		List<Role> list = roleService.getAllRole();
		System.out.println("list=>>>>>>>>>>>>>>" + list);
		return JSONArray.toJSONString(list);
	}
}
