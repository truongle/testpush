package com.sutrix.demo.core.servlets;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sutrix.demo.core.bean.Properties;
/**
 * Sample servlet which easily converts a Node as JSON to the PrintWriter.
 */
@SlingServlet(paths={"/bin/foo"})
public class ResourceToJSONServlet extends SlingAllMethodsServlet {

    /** The logger */
    private static final Logger logger = LoggerFactory.getLogger(ResourceToJSONServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        
        final Resource resource = request.getResourceResolver().getResource("/content/abc123");
        final Node node = resource.adaptTo(Node.class);
        //final ValueMap valueMap = resource.adaptTo(ValueMap.class);

        try {
			PropertyIterator propertyIterator = node.getProperties();
			List<Properties> listJson = new ArrayList<Properties>();
			while (propertyIterator.hasNext()){
				Property pro = propertyIterator.nextProperty();
				
				Properties p = new Properties();
				p.setNameProperty(pro.getName());
				p.setValueProperty(pro.getString());
		
				//JSONObject json = new JSONObject(p);
				//listJson.add(json);
				//final ValueMap valueMap = resource.adaptTo(ValueMap.class);
				
				listJson.add(p);
				
			}
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(listJson);
			
			response.getWriter().println(str);
			
			
		} catch (RepositoryException e) {

			e.printStackTrace();
		}
    }    
  
    
}
