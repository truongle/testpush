package com.sutrix.demo.core.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sutrix.demo.core.bean.Carousel;
import com.sutrix.demo.core.bean.ElementMapping;
import com.sutrix.demo.core.utils.SeoUtils;


public class CarouselComponent extends WCMUsePojo {

    private static final Logger logger = LoggerFactory.getLogger(CarouselComponent.class);

    private static final String PAGE_PATHS = "pagePaths";

    private static final String DESKTOP_IMG = "desktopImage";

    private static final String MOBILE_IMG = "mobileImage";

    private List<Carousel> carousels;

    @Override
    public void activate() throws Exception {

        ValueMap props = getProperties();

        if (!props.containsKey(PAGE_PATHS)) {
            return;
        }

        String[] pagePaths = getProperties().get(PAGE_PATHS, new String[0]);

        if (null != pagePaths && pagePaths.length > 0) {

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            carousels = new ArrayList<Carousel>();

            for (String path : pagePaths) {
                // JSON from file to Object
                // Map<String, Object> map = mapper.readValue(path, new TypeReference<Map<String,Object>>(){});
                ElementMapping ele = mapper.readValue(path, ElementMapping.class);
                Carousel carousel = parseCarousel(getResourceResolver(), getPageManager(), ele.getPagePath());
                if (null != carousel) {
                    carousels.add(carousel);
                }
            }
        }
    }

    /**
     * @return the carousel
     */
    public Carousel parseCarousel(ResourceResolver resourceResolver, PageManager pageManager, String pagePath) {
        Carousel carousel = null;
        try {
            Page page = pageManager.getContainingPage(resourceResolver.getResource(pagePath));
            if (page == null) {
                return carousel;
            }

            ValueMap props = page.getProperties();

            carousel = new Carousel();

            carousel.setPath(page.getPath());
            carousel.setTitle(SeoUtils.getTitle(page));

            if (props.containsKey(DESKTOP_IMG)) {
                carousel.setDesktopImgPath(props.get(DESKTOP_IMG).toString());
            }
            if (props.containsKey(MOBILE_IMG)) {
                carousel.setMobileImgPath(props.get(MOBILE_IMG).toString());
            }
        } catch (Exception ex) {
            logger.error("error in parsing Carousel", ex);
        }
        return carousel;
    }

    /**
     * @return the carousels
     */
    public List<Carousel> getCarousels() {
        return carousels;
    }

}
