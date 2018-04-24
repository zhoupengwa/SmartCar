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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cn.edu.xhu.consts.MessageConsts;
import cn.edu.xhu.domain.ResponseBody;

@WebFilter(urlPatterns = { "/user/lookInfo","/user/updateInfo" ,"/user/updatePassword","/user/addCar","/user/deleteCar","/user/updateCar","/user/listCars","/user/addOrder","/user/listOrders"})
public class UserLoginValidateFilter implements Filter {
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
		HttpSession session = request.getSession();
		ResponseBody<String> responseBody = new ResponseBody<>();
		if (session.getAttribute("user_id") == null || session.getAttribute("user_id").equals("0")) {
			responseBody.setState(MessageConsts.NOT_LOGIN_STATE);
			responseBody.setMessage(MessageConsts.NOT_LOGIN_MESSAGE);
			response.getWriter().print(new Gson().toJson(responseBody));
			return;
		}
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
