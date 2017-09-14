package cn.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.pojo.Page;
import cn.pojo.User;
import cn.service.UserService;

@RequestMapping("/user")
@Controller // 告诉我们是servlet
public class UserCotroller {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private User user;
	@Resource
	private Page<User> page;

	// 登录验证
	@RequestMapping(value = "/login") // url 映射
	public String login(@RequestParam(value = "username") String name, @RequestParam(value = "password") String pass,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		user.setUserName(name);
		user.setUserPassword(pass);
		User u = userService.login(user);
		model.addAttribute("LOGIN_USER", u);
		session.setAttribute("User_login", u);
		if (u == null) {
			return "redirect:/login.jsp";// 重定向
		} else {
			return "welcome"; // WEB-INF/jsp/weldome.jsp 逻辑视图名映射
		}
	}

	// 注销
	@RequestMapping(value = "/loginout")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();// session失效
		return "redirect:/login.jsp";// 重定向
	}

	// 查看所有用户列表
	@RequestMapping(value = "/userList")
	public String userList(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<User> list = userService.findAllUser();
		model.addAttribute("USER_LIST", list);
		return "userList";
	}

	// 根据用户名和页码进行模糊分页查询
	@RequestMapping(value = "/userListLike")
	public String userList(//
			@RequestParam(value = "userName", required = false) String userName, //
			@RequestParam(value = "pageNum", required = false) Integer pageNum, //
			Model model) {
		log.info("userName>>>>>>>>>>>>>>>>>>" + userName);
		int count = userService.count(userName);// 获得以userName为条件的模糊查询总记录数
		page.setTotalCount(count);// 对page设置总条数
		page.setTotalPage();// 对page设置总页数
		if (pageNum == null) {// 首次进入页面的时候为空，进行赋值
			pageNum = 1;
		}
		page.setPage(pageNum);// 设置当前页数
		page.setRowNum();// 设置当前行数
		List<User> list = userService.findAllUsersByName(userName, page.getRowNum(), page.getPageCount());
		model.addAttribute("USER_LIST_LIKE", list);
		model.addAttribute("USER_NAME", userName);
		model.addAttribute("PAGE_NUM", page.getPage());
		model.addAttribute("TOTAL_PAGE", page.getTotalPage());
		// 当前的页码
		return "userList_like";
	}

	@RequestMapping(value = "/getForm")
	public String userList(String userName, Model model) {
		log.info("getForm>>>>>>>>>>>>>>>>>>>>>" + userName);
		return null;
	}

	// 根据id删除用户
	@RequestMapping(value = "/deleteById")
	public String deleteUserById(@RequestParam(value = "id") Integer id, Model model) {
		User u = new User();
		u.setId(id);
		userService.deleteUserById(u);
		return "redirect:/user/userListLike";// user/userListLike
	}

	// 跳转到用户增加页面
	@RequestMapping(value = "/toUserAdd")
	public String goAddUser(@ModelAttribute("user") User user) {
		return "userAdd";
	}

	// 执行用户添加
	@RequestMapping(value = "/doUserAdd")
	public String doUserAdd(User user, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "a_idPicPath", required = false) MultipartFile mfile) {
		String idPicPath = "";
		// 1、判断上传的文件是否是空
		if (!mfile.isEmpty()) {
			// 获得文件的上传路径
			String path = session.getServletContext().getRealPath("upload");
			log.debug("上传到服务器文件夹的路径是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>=" + path);
			// 获得源文件名称
			String oldName = mfile.getOriginalFilename();
			log.debug("上传的原始文件名称是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>=" + oldName);
			// 获得源文件的后缀
			String suffix = FilenameUtils.getExtension(oldName);
			log.debug("上传的原始文件的后缀名称是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>=" + suffix);
			// 判断上传文件的大小
			log.debug("上传的原始文件的大小是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>=" + mfile.getSize() / 1000 + "KB");
			if (mfile.getSize() > 500000) {// 500k
				request.setAttribute("uploadFileError", "* 上传大小不能超过500k");
				return "userAdd";
			} else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")
					|| suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("pneg")) {// 判断后缀是否是图片
				// 修改上传的文件名称
				String uploadFile = System.currentTimeMillis() + RandomUtils.nextInt(100000) + "upLoad.jpg";
				log.debug("转化后的文件的名称是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + uploadFile);
				File file = new File(path, uploadFile);
				if (!file.exists()) {
					// 创建组合后的文件
					file.mkdirs();
					log.debug("准备上传后的文件的完整路径是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + file);
				}
				try {
					// 保存文件
					mfile.transferTo(file);
				} catch (Exception e) {
					request.setAttribute("uploadFileError", " * 上传失败！");
					return "useradd";
				}
				idPicPath = path + File.separator + uploadFile;
				log.debug("准备上传后的文件的完整路径是>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + idPicPath);
			} else {
				request.setAttribute("uploadFileError", " * 上传图片格式不正确");
				return "useradd";
			}
		}
		// user:对象会自动绑定form提交的name属性值给user对象进行赋值
		user.setCreatedBy(((User) session.getAttribute("User_login")).getId());
		user.setCreationDate(new Date());
		user.setIdPicPath(idPicPath);
		int i = userService.addNewUser(user);
		if (i > 0) {// 添加成功
			return "redirect:/user/userListLike";
		}
		return "useradd";// 添加失败
	}

	// 验证用户的存在与否 ok
	@RequestMapping(value = "/userExits")
	@ResponseBody // 返回json格式数据
	public Object checkUserCode(@RequestParam(value = "userCode") String userCode) {
		User user = userService.getUserByUserCode(userCode);
		HashMap<String, String> map = new HashMap<String, String>();
		if (user != null) {// 已经存在了
			map.put("userCode", "exits");
		} else {
			map.put("userCode", "noexits");
		}
		return JSONArray.toJSONString(map);// [{"userCode": "noexits"}]
	}

	// 获得用户的性别
	@RequestMapping(value = "/userSex", produces = "text/html;charset=UTF-8")
	@ResponseBody // 返回json格式数据
	public Object getUserSex() {
		List<Integer> list = userService.getUserSex();
		return JSONArray.toJSONString(list);
	}

	@RequestMapping(value = "/toUserView")
	public String toUserView(@RequestParam Integer id, Model model) {
		model.addAttribute("ID", id);
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return "userView";
	}

	@RequestMapping(value = "/userView", produces = "text/html;charset=UTF-8")
	@ResponseBody // 返回json格式数据
	public Object getUserById(@RequestParam Integer id) {
		User user = userService.getById(id);
		log.debug("User>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user);
		return JSONArray.toJSONString(user);
	}

	// 修改跳转
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(@RequestParam("id") Integer id, Model model) {
		User user = userService.getById(id);
		model.addAttribute("USER_UPDATE", user);
		return "userUpdate";
	}

	// 执行修改 返回页面
	@RequestMapping("/doUpdate")
	public String doUpdate(User user,
			// @RequestParam(value = "_birthday", required = false) String
			// _birthday,
			Model model, HttpSession session) {
		// Date date = null;
		// try {
		// // Wed Jun 15 00:00:00 CST 1983
		// date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
		// Locale.US).parse(_birthday);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// user.setBirthday(date);
		int i = userService.updateUser(user);
		if (i > 0) {
			return "redirect:/user/userListLike";
		} else {
			return "userUpdate";
		}
	}

	// 调转到密码修改页面
	@RequestMapping("/toPassword.html")
	public String toPassUpdate() {
		return "passWord";
	}

	// 修改密码
	@RequestMapping("/doPassWord")
	public String doUpdatePass(User user) {
		int i = userService.doUpdatePass(user);
		if (i > 0) {
			return "redirect:/login.jsp";
		} else {
			return "redirect:user/doPassWord";
		}
	}
}
