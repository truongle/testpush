
package com.sutrix.demo.core.servlets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

public class ReadCSV {
	private String csvFile = "/home/local/SUTRIXMEDIA1/truong.ln/Videos/product.csv";
	private String line = "";
	private String cvsSplitBy = ",";

	public void read(ResourceResolver resourceResolver) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				Resource resource = resourceResolver
						.getResource("/etc/csv");
				Node node = resource.adaptTo(Node.class);
				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				try {
					Node current = node.addNode(country[0],"sling:OsgiConfig");
					current.setProperty(country[1], country[2]);
					node.getSession().save();
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
