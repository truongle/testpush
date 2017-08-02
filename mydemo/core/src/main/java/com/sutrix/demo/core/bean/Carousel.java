package com.sutrix.demo.core.bean;

import java.io.Serializable;
import java.util.List;


public class Carousel implements Serializable {

	private static final long serialVersionUID = 7589326130491497497L;

	private String title;

	private String desktopImgPath;

	private String mobileImgPath;

	private String path;

	private List<String> pagePaths;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the desktopImgPath
	 */
	public String getDesktopImgPath() {
		return desktopImgPath;
	}

	/**
	 * @param desktopImgPath
	 *            the desktopImgPath to set
	 */
	public void setDesktopImgPath(String desktopImgPath) {
		this.desktopImgPath = desktopImgPath;
	}

	/**
	 * @return the mobileImgPath
	 */
	public String getMobileImgPath() {
		return mobileImgPath;
	}

	/**
	 * @param mobileImgPath
	 *            the mobileImgPath to set
	 */
	public void setMobileImgPath(String mobileImgPath) {
		this.mobileImgPath = mobileImgPath;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getPagePaths() {
		return pagePaths;
	}

	public void setPagePaths(List<String> pagePaths) {
		this.pagePaths = pagePaths;
	}

}
