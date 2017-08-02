package com.sutrix.demo.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sutrix.demo.core.servlets.ReadCSV;

@SlingServlet(paths = "/bin/path123", methods = "GET")
public class ServletCSV extends SlingAllMethodsServlet {

	/** The logger */
	private static final Logger logger = LoggerFactory
			.getLogger(ServletCSV.class);

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		final PrintWriter out = response.getWriter();
		final ResourceResolver resolver = request.getResourceResolver();
		
		ReadCSV readCSV = new ReadCSV();
		readCSV.read(resolver);
		
		response.getWriter().println("success");

	}
}
	