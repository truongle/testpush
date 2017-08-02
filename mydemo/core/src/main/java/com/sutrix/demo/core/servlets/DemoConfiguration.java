
package com.sutrix.demo.core.servlets;
 
import java.util.Dictionary;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.ComponentContext;


 
@Service
@Component(name=ConstantsHelper.SERVICE_CONFIGURATION_NAME, label = "IVC - Funeral Planner - Login Configuration", immediate = true, enabled = true, description = "This authentication is only used to call servlet via HttpClient in the author mode")
@Properties({
    @Property(name = DemoConfiguration.AEM_COUNTRY, label = "Country", value = DemoConfiguration.DEFAULT_VALUE),
    //@Property(name = ConfigurationServiceImpl.AEM_PASSWORD, label = "Password", value = ConfigurationServiceImpl.DEFAULT_VALUE),
     
   // @Property(name = ConfigurationServiceImpl.FUNERAL_HOME_URL, label = "Funeral Home Url", value = ConstantsHelper.FUNERAL_HOME_URL)
    }
)
public class DemoConfiguration implements IDemoConfiguration	
{
    static final String DEFAULT_VALUE = "admin";
    
    static final String AEM_COUNTRY = "country";
    //static final String AEM_PASSWORD = "funeral.aem.user.password";
     
    //static final String FUNERAL_HOME_URL = "funeral.home.url";
     
    private String country;
   
  private String getValue(final Dictionary<?, ?> config, String key, String defaultValue) {
      String sRet = defaultValue;
      try {
          sRet = (String) config.get(key);
      } catch (Exception ex) {
          sRet = defaultValue;
      }
      if (StringUtils.isBlank(sRet)) {
          sRet = defaultValue;
      }
      return sRet;
  }
  
  @Activate
  public void activate(ComponentContext context) {
       
      final Dictionary<?, ?> config = context.getProperties();
       
      this.country = getValue(config, AEM_COUNTRY, DEFAULT_VALUE);
      
       
      //this.funeralHomeUrl = getValue(config, FUNERAL_HOME_URL, ConstantsHelper.FUNERAL_HOME_URL);
       
  }
  
  @Modified
  public void modified(ComponentContext context) {
      activate(context);
  }
  public String getCountry() {
      return country;
  }
}