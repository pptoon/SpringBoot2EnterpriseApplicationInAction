package org.fkit.hellotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 名称：<br/>
 * 说明：<br/>SpringBootApplication指定这是一个 spring boot的应用程序.
 * @author Finersoft
 * @date 2018年8月22日
 */
@SpringBootApplication 
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	//SpringApplication 用于从main方法启动Spring应用的类。
        SpringApplication.run(App.class, args);
    }
}
