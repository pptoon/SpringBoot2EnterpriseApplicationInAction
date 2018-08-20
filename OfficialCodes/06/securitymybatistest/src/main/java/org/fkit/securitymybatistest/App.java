package org.fkit.securitymybatistest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication指定这是一个 spring boot的应用程序.
@SpringBootApplication
// 扫描数据访问层接口的包名。
@MapperScan("org.fkit.securitymybatistest.mapper")
public class App 
{
public static void main( String[] args )
{
	// SpringApplication 用于从main方法启动Spring应用的类。
	SpringApplication.run(App.class, args);
}
}
