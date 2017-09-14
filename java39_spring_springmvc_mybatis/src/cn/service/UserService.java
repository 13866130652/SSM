package cn.service;

import java.util.List;

import cn.pojo.User;

public interface UserService {
	List<User> findUsers(User user);

	// 添加一条数据
	int addNewUser(User user);

	// 添加多个数据
	int[] addNewUsers(List<User> list);

	// 验证登录
	User login(User user);

	// 获得所有用户
	List<User> findAllUser();

	// 5、根据姓名，行号，页码进行模糊分页查询
	List<User> findAllUsersByName(//
			String name, // 姓名
			Integer row, // 行号
			Integer pageCount);// 一页几条

	// 6、按姓名进行模糊查询获得总数
	int count(String userName);

	// 7、根据id进行删除
	int deleteUserById(User u);

	// 8、通过userCode查找用户
	User getUserByUserCode(String userCode);

	List<Integer> getUserSex();

	User getById(Integer id);

	int updateUser(User user);

	int doUpdatePass(User user);
}
