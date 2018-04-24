package cn.edu.xhu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xhu.consts.CommonConsts;

@WebFilter(urlPatterns = { "/*" })
public class ConfigFilter implements Filter {
	protected FilterConfig config;

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		request.setCharacterEncoding(CommonConsts.ENCODED_FORMAT);
		response.setContentType("application/json;charset=utf-8");
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
