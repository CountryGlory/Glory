package com.rong.friend.filter;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.rong.friend.model.User;
import com.rong.friend.service.SessionService;

/**
 * 过滤器
 * @author 荣
 *
 */
@Component
public class MyFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(MyFilter.class);
	
	@Autowired
	private SessionService sessionService;
	
//	@Autowired
//    HttpServletRequest httpServletRequest;
//    @Autowired
//    HttpServletResponse httpServletResponse;
	
	/**
	 * 这里可以写逻辑判断是否要过滤，true为永远过滤
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if(request.getRequestURI().equals("/api-user/v2/api-docs") ||
				request.getRequestURI().equals("/api-chat/v2/api-docs")) {
			return false;
		}else {
			return true;
		}	
		
	}

	/**
	 * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		HttpServletResponse response=ctx.getResponse();
		HttpSession session=request.getSession();
		String sessionId=session.getId();
		System.out.println(sessionId);
		ctx.addZuulRequestHeader("Cookie", "SESSION="+sessionId);
		ctx.setSendZuulResponse(true);//对该请求进行路由
		ctx.setResponseStatusCode(200);//返回200正确响应
		if(request.getRequestURI().equals("/api-user/login"))
				return null;
		if(sessionService.getsession(sessionId)==null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.set("error.status_code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			ctx.set("error.exception",new RuntimeException("sessionId is null"));
			log.error("sessionId is null");
		}
		return null;
	}
	/**
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
	 * pre：路由之前
	 * routing：路由之时
	 * post： 路由之后
	 * error：发送错误调用
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}
	
	/**
	 * 过滤的顺序
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}


}
