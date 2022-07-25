package com.localbrand.entities;

public class SystemAccount {
	 private static final long serialVersionUID = 1L;
	 
	 private int id;
	 private String username;
	 private String name;
	 private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
    public String toString() {
        return "SystemAccount[ id=" + id + " ]";
    }
}
