
package com.sutrix.demo.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.commons.jcr.JcrConstants;

//khong chay duoc component ?!?
/*@SlingServlet(
  resourceTypes = "cq/gui/components/authoring/dialog",
		  selectors = "data", 
		  extensions = "html",
		  methods = "GET", 
		  metatype =true	  

)*/


//chay duoc component
@SlingServlet(  resourceTypes = "/bin/foo/path12",  methods = "GET")
public class DemoConfigurationServlet extends SlingSafeMethodsServlet {
  /**
  * 
  */
  private static final long serialVersionUID = -1764652920091154941L;
	
  @Reference IDemoConfiguration confSer;
	
  @Override
  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws ServletException, IOException {
    
    //slit string to array
    String countries = confSer.getCountry();
	ArrayList<String> listCountries = new ArrayList<String>();
	
	for (String country:countries.split(", ")){
			listCountries.add(country);
	}
	
	//get list resource country
	List<Resource> listCountriesRes = new ArrayList<Resource>();
    for(String country : listCountries) {
      ValueMap vm = new ValueMapDecorator(new HashMap<String, Object>());
      vm.put("value", country);
      vm.put("text", country);
      listCountriesRes.add(new ValueMapResource(request.getResourceResolver(), new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, vm));
    }
    
    DataSource dataSource = new SimpleDataSource(listCountriesRes.iterator());
    request.setAttribute(DataSource.class.getName(), dataSource);
    //response.getWriter().println("aaaaaa");
  }
}





