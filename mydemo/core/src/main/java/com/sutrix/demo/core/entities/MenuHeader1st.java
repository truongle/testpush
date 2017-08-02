package com.sutrix.demo.core.entities;

import java.util.ArrayList;
import java.util.List;

public class MenuHeader1st{
	private String title;
	private String path;
	private List<MenuHeader2nd> lstMenu2nd;
	
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
	public List<MenuHeader2nd> getLstMenu2nd() {
		return lstMenu2nd;
	}
	public void setLstMenu2nd(List<MenuHeader2nd> lstMenu2nd) {
		this.lstMenu2nd = lstMenu2nd;
	}
	
	public MenuHeader1st()
	{
		this.lstMenu2nd = new ArrayList<MenuHeader2nd>();
	}
}