package com.bob.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bob.beans.Message;
import com.bob.entity.Page;
import com.bob.service.MaintainService;
import com.bob.service.QueryService;

@SuppressWarnings("serial")
public class AddListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//接收页面的值
		String id = req.getParameter("id");
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		String content = req.getParameter("content");
		Message message = new Message();
		message.setId(String.valueOf((id)));
		message.setCommand(command);
		message.setContent(content);
		message.setDescription(description);
		MaintainService addListService = new MaintainService();
		addListService.addOne(message);
		//采用重定向实现页面跳转,WEB-INF下的文件一般不能直接访问，需要通过后台这样重定向等操作进行访问
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
