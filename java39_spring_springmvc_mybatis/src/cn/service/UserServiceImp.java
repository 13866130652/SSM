package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.UserMapper;
import cn.pojo.User;

@Transactional(propagation = Propagation.REQUIRED, timeout = -1)
@Service
public class UserServiceImp implements UserService {
	@Resource
	private UserMapper userMapper1;

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> findUsers(User user) {
		return userMapper1.getUsers(user);
	}

	public int addNewUser(User user) {
		return userMapper1.addUser(user);
	}

	public int[] addNewUsers(List<User> list) {
		if (list != null) {
			int[] num = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				num[i] = addNewUser(list.get(i));
				throw new RuntimeException("这是测试异常，不是程序自己发生的异常！");
			}
			return num;
		}
		return null;
	}

	// 登录验证
	@Transactional(propagation = Propagation.SUPPORTS)
	public User login(User user) {
		return userMapper1.login(user);
	}

	// 查询所有信息
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> findAllUser() {
		return userMapper1.getAllUsers();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> findAllUsersByName(String name, Integer row, Integer pageCount) {
		return userMapper1.getAllUsersByName(name, row, pageCount);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public int count(String userName) {
		return userMapper1.count(userName);
	}

	@Override
	public int deleteUserById(User u) {
		return userMapper1.deleteUserById(u);
	}

	@Override
	public User getUserByUserCode(String userCode) {
		return userMapper1.getUserByUserCode(userCode);
	}

	@Override
	public List<Integer> getUserSex() {
		// TODO Auto-generated method stub
		return userMapper1.getUserSex();
	}

	@Override
	public User getById(Integer id) {
		return userMapper1.getById(id);
	}

	@Override
	public int updateUser(User user) {
		return userMapper1.updateUser(user);
	}

	@Override
	public int doUpdatePass(User user) {
		return userMapper1.doUpdatePass(user);
	}

}
