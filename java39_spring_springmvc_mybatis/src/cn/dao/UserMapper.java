package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.User;

public interface UserMapper {
	// 1、按照用户名和用户角色进行查找用户
	List<User> getUsers(User user);

	// 2、添加用户
	int addUser(User user);

	// 3、登录验证，根据用户名和密码进行匹配
	User login(User user);

	// 4、获得所有用户
	List<User> getAllUsers();

	// 5、根据姓名，行号，页码进行模糊分页查询
	List<User> getAllUsersByName(//
			@Param("userName") String userName, // 姓名
			@Param("row") Integer row, // 行号
			@Param("pageCount") Integer pageCount);// 一页几条

	// 6、按姓名进行模糊查询获得总数
	int count(@Param("userName") String userName);

	// 7、根据id进行删除
	int deleteUserById(User u);

	// 8、根据用户userCode查找用户
	User getUserByUserCode(@Param("userCode") String userCode);

	// 9、获得用户的性别
	List<Integer> getUserSex();

	// 10、根据 id获得用户
	User getById(Integer id);

	// 11、根据id修改用户
	int updateUser(User user);

	int doUpdatePass(User user);

}
