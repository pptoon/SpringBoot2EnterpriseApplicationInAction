package org.fkit.springbootjdbctemplate.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.Resource;
import org.fkit.springbootjdbctemplate.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
/**
 * @Repository注解：标注这是一个持久化操作对象.
 */
@Repository
public class UserRepository {
	
	// 注入JdbcTemplate模板对象
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/***
	 *  插入数据
	 * @return 插入影响的行数
	 */
	public int insertUser(){
		String sql = "insert into tb_user(login_name ,username ,passWord) "
				+ "values (?,?,?),(?,?,?),(?,?,?)";
		Object[] args = new Object[]{"swk","孙悟空","123456","zbj","猪八戒","123456"
				,"ts","唐僧","123456"};
		// 参数一：插入数据的sql语句 参数二: 对应sql语句中占位符?的参数
		return jdbcTemplate.update(sql, args);
	}
	
	/***
	 *  根据userName查询数据
	 * @param userName
	 * @return User对象
	 */
	public User selectByUsername(String username) {
		// 定义SQL语句
		String sql = "select * from tb_user where username = ?";
		// 定义一个RowMapper
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		// 执行查询方法
		User user = jdbcTemplate.queryForObject(sql, new Object[] { username }, rowMapper);
		return user;
	}
	
	/***
	 * 根据id查询数据
	 * @return User对象
	 */
	public User findUserById(int id) {
		// 定义SQL语句
		String sql = "select * from tb_user where id=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		// 执行查询方法
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
	}
	
	/***
	 * 查询所有数据
	 * @return 包含User对象的List集合
	 */
	public List<User> findAll() {
		// 定义SQL语句
		String sql = "select * from tb_user";
		// 申明结果集的映射rowMapper，将结果集的数据映射成User对象数据
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	/***
	 * 根据id删除数据
	 */
	public void delete(final Integer id) {
		// 定义SQL语句
		String sql = "delete from tb_user where id=?";
		// 执行
		jdbcTemplate.update(sql, new Object[] { id });
	}
	
	/***
	 * 修改数据
	 */
	public void update(final User user) {
		// 定义SQL语句
		String sql = "update tb_user set username=?, login_name=? where id=?";
		// 执行
		jdbcTemplate.update(sql,
			new Object[] { user.getUsername(), user.getLoginName(), user.getId()});
	}
	/**
	 * 插入数据获取被插入数据的主键
	 * */
	public User insertGetKey(User user) {
		// 1.申明插入的sql语句
		String sql = "insert into tb_user(username,login_name,password) values(?,?,?)";
		// 2.定义插入数据后获取主键的对象
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// 3.插入数据后，将被插入数据的主键返回回来
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getLoginName());
				ps.setString(3, user.getPassword());
				return ps;
			}
		}, holder);
		// 4.获取被插入数据库的主键 然后注入到user对象中去
		int newUserId = holder.getKey().intValue();
		user.setId(newUserId);
		return user;
	}
}
