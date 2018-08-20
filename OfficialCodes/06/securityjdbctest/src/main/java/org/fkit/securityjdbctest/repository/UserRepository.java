package org.fkit.securityjdbctest.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fkit.securityjdbctest.pojo.FKRole;
import org.fkit.securityjdbctest.pojo.FKUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {
	
	// 注入JdbcTemplate
	@Autowired
    private JdbcTemplate jdbcTemplate;

	// 根据登录名查询出用户的方法即可，不需要通过用户名和密码去查询。
	@Transactional(readOnly = true)
	public FKUser findByLoginName(String loginName){
		String sql = "select * from tb_user where login_name = ?";
		System.out.println(sql);
		// 根据loginName查询用户
		FKUser fkUser = jdbcTemplate.queryForObject(sql, 
				new Object[]{loginName}, new RowMapper<FKUser>(){

					@Override
					public FKUser mapRow(ResultSet rs, int rowNum) throws SQLException {
						FKUser fkUser = new FKUser();
						fkUser.setId(rs.getLong("id"));
						fkUser.setLoginName(rs.getString("login_name"));
						fkUser.setPassword(rs.getString("password"));
						fkUser.setUsername(rs.getString("username"));
						return fkUser;
					}
					
				});
		List<FKRole> roles = new ArrayList<>();
		// 根据用户id查询用户权限
		List<Map<String,Object>> result =jdbcTemplate
		.queryForList("SELECT id,authority FROM tb_role r,tb_user_role ur "
				+ "WHERE r.id = ur.role_id AND user_id = ?",new Object[]{fkUser.getId()});
		for(Map<String,Object> map : result){
			FKRole fkRole = new FKRole();
			fkRole.setId((Long)map.get("id"));
			fkRole.setAuthority((String)map.get("authority"));
			roles.add(fkRole);
		}
		// 添加用户权限
		fkUser.setRoles(roles);
		// 返回用户
		return fkUser;
	}

}
