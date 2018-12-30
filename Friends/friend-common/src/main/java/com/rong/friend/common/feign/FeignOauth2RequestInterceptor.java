package com.rong.friend.oauthserver.common.feign;

import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;


public class FeignOauth2RequestInterceptor implements RequestInterceptor {

//    private final  static Logger logger= LoggerFactory.getLogger(FeignOauth2RequestInterceptor.class);
//
     private static final String AUTHORIZATION_HEADER = "Authorization";
//
//     private static final String BEARER_TOKEN_TYPE = "bearer";
//
//     @Override
//     public void apply(RequestTemplate template) {
//         logger.debug("feign  begin.......");
//         SecurityContext securityContext = SecurityContextHolder.getContext();
//         Authentication authentication = securityContext.getAuthentication();
//         if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
//             logger.debug("feign not null........");
//             OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//             template.header(AUTHORIZATION_HEADER, details.getTokenValue());
//             logger.debug( details.getTokenValue());
//         }
//         logger.debug("feign  end........");
//     }
//
     @Bean
     public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
         return new FeignHystrixConcurrencyStrategy();
     }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", getHeaders(getHttpServletRequest()).get(AUTHORIZATION_HEADER.toLowerCase()));
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}