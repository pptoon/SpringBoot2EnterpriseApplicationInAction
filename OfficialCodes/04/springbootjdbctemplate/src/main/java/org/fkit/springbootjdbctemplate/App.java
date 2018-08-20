package org.fkit.springbootjdbctemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author dlei(徐磊)
 * @Email dlei0009@163.com
 * @QQ 251425887
 * @SpringBootApplication指定这是一个 spring boot的应用程序.
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	// SpringApplication 用于从main方法启动Spring应用的类。
        SpringApplication.run(App.class, args);
    }
}
