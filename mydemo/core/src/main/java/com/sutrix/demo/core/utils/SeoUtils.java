package com.sutrix.demo.core.utils;

import com.day.cq.wcm.api.Page;

public class SeoUtils {

    /**
     * Gets title of a specific {@link Page}
     * 
     * @param page a {@link Page}
     * @return title of page
     */
    public static String getTitle(Page page) {
        String title = "";
        if (page == null) {
            return title;
        }

        title = page.getTitle();
        if ("".equals(title)) {
            title = page.getPageTitle();
            if ("".equals(title)) {
                title = page.getNavigationTitle();
                if ("".equals(title)) {
                    title = page.getName();
                }
            }
        }

        return title;
    }
}
