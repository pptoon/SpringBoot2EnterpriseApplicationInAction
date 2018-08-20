package org.fkit.fileuploadtest.domain;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	// 对应上传的headPortrait，类型为MultipartFile，上传文件会自动绑定到image属性当中
	private MultipartFile headPortrait;
	
	public User() {
		super();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MultipartFile getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(MultipartFile headPortrait) {
		this.headPortrait = headPortrait;
	}
	
	
}
