package cn.edu.xhu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.edu.xhu.consts.MessageConsts;
import cn.edu.xhu.domain.Car;
import cn.edu.xhu.domain.RefuelInfo;
import cn.edu.xhu.domain.ResponseBody;
import cn.edu.xhu.domain.User;
import cn.edu.xhu.exception.CarNotExistException;
import cn.edu.xhu.exception.MapToBeanConvertException;
import cn.edu.xhu.exception.QRCodeCreateException;
import cn.edu.xhu.exception.UserExistException;
import cn.edu.xhu.exception.UserNotExistException;
import cn.edu.xhu.service.UserService;
import cn.edu.xhu.util.CommUtils;
import cn.edu.xhu.util.QRCodeUtil;

@WebServlet(urlPatterns = { "/user/*" })
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 注册
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> userMap = request.getParameterMap();
		ResponseBody<User> responseBody = new ResponseBody<>();
		try {
			User user = CommUtils.convertMapToBean(userMap, User.class);
			if (new UserService().register(user)) {
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);// 注册成功
				responseBody.setMessage(MessageConsts.SUCCEED_REGISTER_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);// 注册失败-内部错误
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		} catch (MapToBeanConvertException e) {// 参数提交错误
			responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
		} catch (Exception e) {
			if (e instanceof UserExistException) {// 用户存在
				responseBody.setState(MessageConsts.FAILED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.USER_EXIST_MESSAGE);
			} else {// 异常-内部错误
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		ResponseBody<String> responseBody = new ResponseBody<>();
		try {
			int userId = new UserService().login(phone, password);
			if (userId > 0) {
				request.getSession().setAttribute("user_id", userId);// 登录成功
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCEED_LOGIN_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.FAILED_OPERA_STATE);// 登录失败-密码错误
				responseBody.setMessage(MessageConsts.FAILED_LOGIN_MESSAGE);
			}
		} catch (Exception e) {
			responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 修改密码——修改后需要重新登录
	public void updatepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		ResponseBody<String> responseBody = new ResponseBody<>();
		String password = request.getParameter("password");
		String newPassword = request.getParameter("new_password");
		try {
			if (new UserService().updatePassword(password, newPassword, userId)) {
				request.getSession().setAttribute("user_id", null);
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCCEED_UPDATE_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.FAILED_OPERA_STATE);// 修改失败-原密码错误
				responseBody.setMessage(MessageConsts.FAILED_UPDATE_PASSWORD_MESSAGE);
			}
		} catch (Exception e) {
			responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 查看个人信息
	public void lookinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		ResponseBody<User> responseBody = new ResponseBody<>();
		try {
			User user = new UserService().lookinfo(userId);
			responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
			responseBody.setMessage(MessageConsts.SUCCEED_QUERY_MESSAGE);
			List<User> userList = new ArrayList<>();
			userList.add(user);
			responseBody.setResultData(userList);
		} catch (Exception e) {
			responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 修改个人信息
	public void updateinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		ResponseBody<String> responseBody = new ResponseBody<>();
		Map<String, String[]> userMap = request.getParameterMap();
		try {
			User user = CommUtils.convertMapToBean(userMap, User.class);
			user.setId(userId);
			if (new UserService().updateUserInfo(user)) {
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCCEED_UPDATE_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);// 修改失败-内部错误
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		} catch (MapToBeanConvertException e) {
			responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
			e.printStackTrace();
		} catch (Exception e) {
			responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 录入车辆
	public void addcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		Map<String, String[]> carMap = request.getParameterMap();
		ResponseBody<String> responseBody = new ResponseBody<>();
		try {
			Car car = CommUtils.convertMapToBean(carMap, Car.class);
			car.setUserid(userId);
			if (new UserService().addCar(car)) {
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCCEED_ADD_CAR_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);// 录入失败-内部错误
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		} catch (MapToBeanConvertException e) {
			responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
		} catch (Exception e) {
			if (e instanceof UserNotExistException) {
				responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 查询所有车辆
	public void listcars(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		ResponseBody<Car> responseBody = new ResponseBody<>();
		try {
			List<Car> cars = new UserService().getAllCars(userId);
			responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
			responseBody.setMessage(MessageConsts.SUCCEED_QUERY_MESSAGE);
			responseBody.setResultData(cars);
		} catch (Exception e) {
			responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
		}
		response.getWriter().print(new Gson().toJson(responseBody));

	}

	// 修改车辆信息
	public void updatecar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = (int) request.getSession().getAttribute("user_id");
		Map<String, String[]> carMap = request.getParameterMap();
		ResponseBody<String> responseBody = new ResponseBody<>();
		try {
			Car car = CommUtils.convertMapToBean(carMap, Car.class);
			car.setUserid(userId);
			if (new UserService().updateCar(car)) {
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCCEED_UPDATE_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		} catch (MapToBeanConvertException e) {
			responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
		} catch (Exception e) {
			if (e instanceof CarNotExistException) {
				responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 删除车辆
	public void deletecar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResponseBody<String> responseBody = new ResponseBody<>();
		int userId = (int) request.getSession().getAttribute("user_id");
		String carId = request.getParameter("car_id");
		try {
			if (new UserService().deleteCar(Integer.parseInt(carId), userId)) {// 删除失败-内部错误
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCCEED_DELETE_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		} catch (NumberFormatException e) {
			responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
		} catch (Exception e) {
			if (e instanceof CarNotExistException) {
				responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 预约加油
	public void addorder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResponseBody<String> responseBody = new ResponseBody<>();
		int userId = (int) request.getSession().getAttribute("user_id");
		Map<String, String[]> orderMap = request.getParameterMap();
		try {
			RefuelInfo refuelInfo = CommUtils.convertMapToBean(orderMap, RefuelInfo.class);
			Map<String, String> nameMap = QRCodeUtil.createPath(request);
			refuelInfo.setUserid(userId);
			String showPath = nameMap.get("showPath");
			String savePath = nameMap.get("savePath");
			refuelInfo.setPath(showPath);
			if (new UserService().addRefuelInfo(refuelInfo, savePath)) {
				responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.SUCCEED_ADD_ORDER);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		} catch (MapToBeanConvertException e1) {
			responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
		} catch (Exception e) {
			if (e instanceof CarNotExistException) {
				responseBody.setState(MessageConsts.ERROR_PARAMETER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_PARAMETER_MESSAGE);
			} else if (e instanceof QRCodeCreateException) {
				responseBody.setState(MessageConsts.FAILED_OPERA_STATE);
				responseBody.setMessage(MessageConsts.ERROR_CREATE_QRCODE);
			} else {
				responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
				responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
			}
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}

	// 查询所有订单
	public void listorders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResponseBody<RefuelInfo> responseBody = new ResponseBody<>();
		int userId = (int) request.getSession().getAttribute("user_id");
		try {
			List<RefuelInfo> refuelInfos = new UserService().getAllRefuelInfos(userId);
			responseBody.setState(MessageConsts.SUCCEED_OPERA_STATE);
			responseBody.setMessage(MessageConsts.SUCCEED_QUERY_MESSAGE);
			responseBody.setResultData(refuelInfos);
		} catch (Exception e) {
			responseBody.setState(MessageConsts.ERROR_SERVER_STATE);
			responseBody.setMessage(MessageConsts.ERROR_SERVER_MESSAGE);
		}
		response.getWriter().print(new Gson().toJson(responseBody));
	}
}
