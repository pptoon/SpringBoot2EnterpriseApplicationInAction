package org.fkit.oa.identity.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.identity.domain.Dept;
import org.fkit.oa.identity.domain.Module;
import org.fkit.oa.identity.domain.Role;
import org.fkit.oa.identity.domain.User;
import org.fkit.oa.identity.dto.UserModule;
import org.fkit.oa.identity.vo.TreeData;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月14日上午10:03:07
 * @From http://www.fkjava.org 
 *
 */
public interface IdentityService {

	/**
	 * @return 查询所有的部门
	 */
	List<Dept> getAllDepts() ;

	/**
	 * 异步登录的业务层接口方法
	 * @param params
	 * @return
	 */
	Map<String, Object> login(Map<String, Object> params);
	
	/**
	 * 根据用户的主键查询用户信息,包含了延迟加载的部门和职位信息
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);

	/**
	 * 修改自己
	 * @param user
	 */
	void updateSelf(User user,HttpSession session);

	/**
	 * @return 异步加载部门与职位的json字符串信息写回到页面
	 */
	Map<String, Object> getAllDeptsAndJobsAjax();

	/**
	 * 分页查询用户信息 
	 * 
	 * @param user
	 * @param pageModel
	 * @return
	 */
	List<User> getUsersByPager(User user, PageModel pageModel);

	/**
	 * 批量删除用户
	 * @param ids
	 */
	void deleteUserByUserIds(String ids);

	/**
	 * 校验用户是否已经被注册 
	 * @param userId
	 * @return
	 */
	String isUserValidAjax(String userId);

	/**
	 * 
	 * @param user 
	 */
	void addUser(User user);

	/**
	 * 根据userId修改用户信息
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 激活用户
	 * @param user
	 */
	void activeUser(User user);

	/**
	 * 加载所有的模块树
	 * @return
	 */
	List<TreeData> loadAllModuleTrees();

	/**
	 * 根据父节点查询所有的子模块
	 * @param parentCode
	 * @return
	 */
	List<Module> getModulesByParent(String parentCode,PageModel pageModel);
	/**
	 * 根据父节点查询所有的子模块 
	 * 不分页
	 * @param parentCode
	 * @return
	 */
	List<Module> getModulesByParent(String parentCode);

	/**
	 * 批量删除菜单
	 * @param ids
	 */
	void deleteModules(String ids);

	/**
	 * 为当前父节点菜单添加子节点模块
	 * @param parentCode
	 * @param module
	 */
	void addModule(String parentCode, Module module);

	/**
	 * 根据编号查询模块信息
	 * @param code
	 * @return
	 */
	Module getModuleByCode(String code);

	/**
	 * 修改模块
	 * @param module
	 */
	void updateModule(Module module);

	/**
	 * 分页查询角色信息
	 * @param pageModel
	 * @return
	 */
	List<Role> getRoleByPager(PageModel pageModel);

	/**
	 * 添加角色
	 * @param role
	 */
	void addRole(Role role);

	/**
	 * 批量删除角色
	 * @param ids
	 */
	void deleteRole(String ids);

	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	Role getRoleById(Long id);

	/**
	 * 修改角色
	 * @param role
	 */
	void updateRole(Role role);

	/**
	 * 分页查询属于这个角色下的用户信息 
	 * @param role
	 * @param pageModel
	 * @return
	 */
	List<User> selectRoleUser(Role role, PageModel pageModel);

	/**
	 * 查询不属于某个角色下的用户
	 * @param role
	 * @param pageModel
	 * @return
	 */
	List<User> selectNotRoleUser(Role role, PageModel pageModel);

	/**
	 * 给用户绑定角色
	 * @param role
	 * @param ids
	 */
	void bindUser(Role role, String ids);

	/**
	 * 给用户解绑角色
	 * @param role
	 * @param ids
	 */
	void unBindUser(Role role, String ids);

	/**
	 * 查询当前角色在当前模块下拥有的操作权限编号。
	 * @param role
	 * @param parentCode
	 * @return
	 */
	List<String> getRoleModuleOperasCodes(Role role, String parentCode);

	/**
	 * 给角色绑定某个模块下的操作权限 
	 * @param codes
	 * @param role
	 * @param parentCode
	 */
	void bindPopedom(String codes, Role role, String parentCode);

	/**
	 * 查询当前用户的权限模块 
	 * @return
	 */
	List<UserModule> getUserPopedomModules();


}
