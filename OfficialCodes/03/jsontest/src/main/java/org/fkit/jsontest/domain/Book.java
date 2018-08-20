package org.fkit.jsontest.domain;

import java.io.Serializable;


public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String author;
	private String image;
	private Double price;
	private String remark;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer id, String name, String author, String image, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.image = image;
		this.price = price;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", image=" + image + ", price=" + price
				+ ", remark=" + remark + "]";
	}

	

}
