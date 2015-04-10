package cn.edu.seu.institute.entity;

import java.util.Date;

public class Application {
	private int id;
	private String name;
	private String phone;
	private String mail;
	private String filename;
	private String directory;
	private Date applyTime;
	private boolean dealed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public boolean isDealed() {
		return dealed;
	}

	public void setDealed(boolean dealed) {
		this.dealed = dealed;
	}

	public String getUrl() {
		return "static/applyForms/" + directory + "/" + filename;
	}
}
