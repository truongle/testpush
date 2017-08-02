package com.sutrix.demo.core.bean;

public class ElementMapping {
    
    private String pagePath;

    /**
     * @return the pagePath
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * @param pagePath the pagePath to set
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
    
    @Override
    public String toString() {
        return "{pagePath:" + pagePath + "}";
    }

}
