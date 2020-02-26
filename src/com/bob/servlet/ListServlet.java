package com.bob.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bob.beans.Message;
import com.bob.entity.Page;
import com.bob.service.QueryService;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
  * 列表页面初始化控制
 * @author Bob
 *
 */
//添加一个注解去掉序列化警告
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置编码
		req.setCharacterEncoding("UTF-8");
		//接收页面的值
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		String currentPage = req.getParameter("currentPage");
		//创建分页对象
		Page page = new Page();
		//正则表达式来控制输入，[0-9]表示内容是数字0-9，{1,9}表示输入最少一位，最多9位
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null ||  !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);//默认是第一页
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		QueryService listService = new QueryService();
		//向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		//查询消息列表并传给页面,可以在页面调用,直接根据这个id进行调用即可，如value="${page.currentPage}"
		req.setAttribute("messageList", listService.queryMessageListByPage(command, description, page));
		//采用重定向实现页面跳转,WEB-INF下的文件一般不能直接访问，需要通过后台这样重定向等操作进行访问
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
