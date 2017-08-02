package com.sutrix.demo.core.entities;

import java.util.ArrayList;
import java.util.List;

public class MenuHeader2nd{
	private String title;
	private String path;
	private List<MenuHeader3rd> lstMenu3rd;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<MenuHeader3rd> getLstMenu3rd() {
		return lstMenu3rd;
	}
	public void setLstMenu3rd(List<MenuHeader3rd> lstMenu3rd) {
		this.lstMenu3rd = lstMenu3rd;
	}
	
	public MenuHeader2nd(){
		lstMenu3rd = new ArrayList<MenuHeader3rd>();
	}
	
}
