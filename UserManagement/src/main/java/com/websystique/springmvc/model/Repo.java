package com.websystique.springmvc.model;

public class Repo {

	private String repo;
	
	private String folder;
	
	private String index;

	
	private String schema;
	
	
	public Repo(){
		repo="";
	}
	
	public Repo(String repo, String folder, String index, String schema){
		this.repo = repo;
		this.folder = folder;
		this.index = index;
		this.schema = schema;
	}

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	

	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", username=" + username + ", role=" + role
//				+ ", email=" + email + "]";
//	}
	
}
