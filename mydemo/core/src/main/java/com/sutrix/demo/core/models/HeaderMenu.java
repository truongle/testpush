package com.sutrix.demo.core.models;

import com.sutrix.demo.core.entities.MenuHeader1st;
import com.sutrix.demo.core.entities.MenuHeader2nd;
import com.sutrix.demo.core.entities.MenuHeader3rd;

import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;

import com.adobe.cq.wcm.launches.utils.LaunchUtils;
import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.wcm.api.Page;

import com.day.cq.wcm.api.designer.Style;
import com.day.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUsePojo;

public class HeaderMenu extends WCMUsePojo{
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	private List<MenuHeader1st> listMenuHeader1st = null;
	@Override 
	public void activate() throws Exception{
		 log.info("##### INVOKED ACTIVATE");
         listMenuHeader1st = new ArrayList<MenuHeader1st>();
         setItems();
	}
public void setItems() {
    	
    	Style currentStyle = getCurrentStyle();
    	Page currentPage = getCurrentPage();
    	
    	
    	int absParent = currentStyle.get("absParent", 1);
    	
    	
    	Page homePage = currentPage.getAbsoluteParent(absParent);
    	
    	
    	
    	String home = homePage != null ? homePage.getPath() : Text
				.getAbsoluteParent(currentPage.getPath(), 2);
    	
    	log.info("Bash launch: " + LaunchUtils.getLaunchPath(currentStyle.getPath()));
    	log.info("home:   " + home);
    	log.info("get path: " + homePage.getPath());
    	log.info("text: " + Text
				.getAbsoluteParent(currentPage.getPath(), 2));
    	log.info("Bash launch: " + LaunchUtils.getLaunchPath(home));
    	if (LaunchUtils.isLaunchBasedPath("/content")) {
			return;
		}
    	
    	
    	// Main menu
    	Iterator<Page> MenuHeader1stPageIterator = homePage.listChildren();
    	
    	while(MenuHeader1stPageIterator.hasNext()) {
    		
    		Page MenuHeader1stPage = MenuHeader1stPageIterator.next();
    		
    		MenuHeader1st MenuHeader1st = new MenuHeader1st();
    		
    		MenuHeader1st.setTitle(MenuHeader1stPage.getTitle());
    		MenuHeader1st.setPath(MenuHeader1stPage.getPath());
    		
    		// set ArrayList MenuHeader2nd
    		List<MenuHeader2nd> listMenuHeader2nd = new ArrayList<MenuHeader2nd>();
    		Iterator<Page> MenuHeader2ndPageIterator = MenuHeader1stPage.listChildren();
    		while(MenuHeader2ndPageIterator.hasNext()) {
    			
    			Page MenuHeader2ndPage = MenuHeader2ndPageIterator.next();
        		HierarchyNodeInheritanceValueMap pagePropertiesMenuHeader2nd = new HierarchyNodeInheritanceValueMap(MenuHeader2ndPage.getContentResource());
        		
        		
    			MenuHeader2nd MenuHeader2nd = new MenuHeader2nd();
    			
    			MenuHeader2nd.setTitle(MenuHeader2ndPage.getTitle());
    			MenuHeader2nd.setPath(MenuHeader2ndPage.getPath());
    		
					
    			
    			// set ArrayList MenuHeader3rd
    			List<MenuHeader3rd> listMenuHeader3rd = new ArrayList<MenuHeader3rd>();
    			Iterator<Page> MenuHeader3rdPageIterator = MenuHeader2ndPage.listChildren();
    			while(MenuHeader3rdPageIterator.hasNext()) {
    				
    				Page MenuHeader3rdPage = MenuHeader3rdPageIterator.next();
    				
    				MenuHeader3rd MenuHeader3rd = new MenuHeader3rd();
    				
    				MenuHeader3rd.setTitle(MenuHeader3rdPage.getTitle());
    				MenuHeader3rd.setPath(MenuHeader3rdPage.getPath());
    				
    				listMenuHeader3rd.add(MenuHeader3rd);
    			}
    			
    			MenuHeader2nd.setLstMenu3rd(listMenuHeader3rd);
    			
    			listMenuHeader2nd.add(MenuHeader2nd);
    		}
    		
    		MenuHeader1st.setLstMenu2nd(listMenuHeader2nd);
    		
    		listMenuHeader1st.add(MenuHeader1st);
    	}
    }
    public List<MenuHeader1st> getListMenuHeader1st(){
   
        return this.listMenuHeader1st;
   
    }
    
}
