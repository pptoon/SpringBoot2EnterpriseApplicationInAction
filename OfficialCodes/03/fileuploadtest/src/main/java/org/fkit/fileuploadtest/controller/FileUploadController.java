package org.fkit.fileuploadtest.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.fkit.fileuploadtest.domain.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	// 映射"/"请求
	@RequestMapping("/")
	public String index(){
		// 根据Thymeleaf默认模板，将返回resources/templates/index.html
		return "index";
	}

	
	@RequestMapping("/registerForm")
	public String registerForm(){
		return "registerForm";
	}
	
	// 上传文件会自动绑定到MultipartFile中
	 @PostMapping(value="/upload")
	 public String upload(HttpServletRequest request,
			@RequestParam("description") String description,
			@RequestParam("file") MultipartFile file) throws Exception{
		// 接收参数description
	    System.out.println("description = " + description);
	    // 如果文件不为空，写入上传路径
		if(!file.isEmpty()){
			// 上传文件路径
			String path = request.getServletContext().getRealPath(
	                "/upload/");
			System.out.println("path = " + path);
			// 上传文件名
			String filename = file.getOriginalFilename();
		    File filepath = new File(path,filename);
			// 判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
	        // 将上传文件保存到一个目标文件当中
			file.transferTo(new File(path+File.separator+ filename));
			return "success";
		}else{
			return "error";
		}
		 
	 }
	 
	 @RequestMapping(value="/register")
	 public String register(HttpServletRequest request,
			 @ModelAttribute User user,
			 Model model)throws Exception{
		// 接收参数username
		System.out.println("username = " +user.getUsername());
		// 如果文件不为空，写入上传路径
		if(!user.getHeadPortrait().isEmpty()){
			// 上传文件路径
			String path = request.getServletContext().getRealPath(
	                "/upload/");
			System.out.println("path = " + path);
			// 上传文件名
			String filename = user.getHeadPortrait().getOriginalFilename();
		    File filepath = new File(path,filename);
			// 判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
	        // 将上传文件保存到一个目标文件当中
	        user.getHeadPortrait().transferTo(new File(path+File.separator+ filename));
	        // 将用户添加到model
	        model.addAttribute("user", user);
	        return "userInfo";
		}else{
			return "error";
		}
	}
	 
	 @RequestMapping(value="/download")
	 public ResponseEntity<byte[]> download(HttpServletRequest request,
			 @RequestParam("filename") String filename,
			 @RequestHeader("User-Agent") String userAgent,
			 Model model)throws Exception{
		// 下载文件路径
		String path = request.getServletContext().getRealPath(
                "/upload/");
		// 构建File
		File file = new File(path+File.separator+ filename);
		// ok表示Http协议中的状态 200
        BodyBuilder builder = ResponseEntity.ok();
        // 内容长度
        builder.contentLength(file.length());
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用URLDecoder.decode对文件名进行解码
        filename = URLEncoder.encode(filename, "UTF-8");
        // 设置实际的响应文件名，告诉浏览器文件要用于【下载】、【保存】attachment 以附件形式
        // 不同的浏览器，处理方式不同，要根据浏览器版本进行区别判断
        if (userAgent.indexOf("MSIE") > 0) {
                // 如果是IE，只需要用UTF-8字符集进行URL编码即可
                builder.header("Content-Disposition", "attachment; filename=" + filename);
        } else {
                // 而FireFox、Chrome等浏览器，则需要说明编码的字符集
                // 注意filename后面有个*号，在UTF-8后面有两个单引号！
                builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + filename);
        }
        return builder.body(FileUtils.readFileToByteArray(file));
	 }
	
}
