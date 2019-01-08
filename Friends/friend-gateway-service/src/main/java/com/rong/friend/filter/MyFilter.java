package com.rong.friend.filter;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StringUtils;

/**
 * 过滤器
 * 
 * @author 荣
 *
 */
@Component
public class MyFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(MyFilter.class);

	private static final String AUTHORIZATION_HEADER = "Authorization";

	private static final String BEARER_TOKEN_TYPE = "bearer";

	// @Autowired
	// private RedisUtil redisUtil;

	// @Autowired
	// HttpServletRequest httpServletRequest;
	// @Autowired
	// HttpServletResponse httpServletResponse;

	/**
	 * 这里可以写逻辑判断是否要过滤，true为永远过滤
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("uri:"+request.getRequestURI());
		if (request.getRequestURI().equals("/api-user/v2/api-docs")
				|| request.getRequestURI().equals("/api-chat/v2/api-docs")
				|| request.getRequestURI().equals("/uaa/user")
				|| request.getRequestURI().equals("/uaa/v2/api-docs")
				|| request.getRequestURI().equals("/uaa/login")
				||request.getRequestURI().equals("/api-chat/endpointWisely/info")) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		String accessTokenHead = request.getHeader("token");
		String accessTokenParam=request.getParameter("token");

		if((StringUtils.isEmpty(accessTokenHead)||accessTokenHead.equals("undefined"))&& (StringUtils.isEmpty(accessTokenParam)||accessTokenParam.equals("undefined"))){
			log.warn("Authorization token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("Authorization token is empty");
			return null;
		}else if(!(StringUtils.isEmpty(accessTokenHead)||accessTokenHead.equals("undefined"))&& (StringUtils.isEmpty(accessTokenParam)||accessTokenParam.equals("undefined"))){
			ctx.addZuulRequestHeader(AUTHORIZATION_HEADER,BEARER_TOKEN_TYPE+accessTokenHead);
		}else if((StringUtils.isEmpty(accessTokenHead)||accessTokenHead.equals("undefined"))&& !(StringUtils.isEmpty(accessTokenParam)||accessTokenParam.equals("undefined"))){
			ctx.addZuulRequestHeader(AUTHORIZATION_HEADER,BEARER_TOKEN_TYPE+accessTokenParam);
		}
		return null;
	}

	/**
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型 pre：路由之前 routing：路由之时 post： 路由之后
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
