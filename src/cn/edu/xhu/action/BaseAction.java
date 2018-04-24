package cn.edu.xhu.action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.edu.xhu.consts.MessageConsts;
import cn.edu.xhu.domain.ResponseBody;

public class BaseAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		String remove = path.substring(0, path.lastIndexOf("/") + 1);
		String action = path.replace(remove, "").toLowerCase();
		try {
			Method method = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			ResponseBody<String> body = new ResponseBody<>();
			body.setState(MessageConsts.ERROR_REQUEST_STATE);
			body.setMessage(MessageConsts.ERROR_REQUEST_MESSAGE);
			resp.getWriter().print(new Gson().toJson(body).toString());
		}
	}
}
