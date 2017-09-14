package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dao.RoleMapper;
import cn.pojo.Role;

@Service

@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImp implements RoleService {
	@Resource
	private RoleMapper roleMapper;

	@Override
	public List<Role> getAllRole() {
		return roleMapper.getAllRole();
	}

}
