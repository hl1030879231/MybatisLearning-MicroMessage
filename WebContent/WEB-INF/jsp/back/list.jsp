<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- base路径就是WebContent路径 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 注意一般所有的页面都放在WEB-INF文件夹下，这样访问页面都需要通过后台拦截，方便控制 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<title>内容列表页面</title>
<link href="<%=basePath%>resources/css/all.css" rel="stylesheet"
	type="text/css" />
<script src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js"></script>
<script src="<%=basePath%>resources/js/back/list.js"></script>
</head>
<body style="background: #e1e9eb;">
	<form id="addForm" method="post">
		<input type="hidden" name="totalNum" id="totalNum" value="${page.totalNumber}"/>
	</form>
	<!-- 对表单绑定action动作 -->
	<form action="<%=basePath%>List.action" id="mainForm" method="post">
	<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>
	
		<div class="right">
			<div class="current">
				当前位置：<a href="javascript:void(0)" style="color: #6E6E6E;">内容管理</a>
				&gt; 内容列表
			</div>
			<div class="rightCont">
				<p class="g_title fix">
					内容列表 <a class="btn03" href="javascript:changeToAddView('<%=basePath%>');">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="btn03" href="javascript:deleteBatch('<%=basePath%>');">删
						除</a>
				</p>
				<table class="tab1">
					<tbody>
						<tr>
							<td width="90" align="right">指令名称:</td>
							<td>
								<!-- value="${command}表示获取输入的值并存在名为command的Parameter中，可以在servlet中用getParameter获取 -->
								<input name="command" type="text" class="allInput"
								value="${command}" />
							</td>
							<td width="90" align="right">描述:</td>
							<td><input name="description" type="text" class="allInput"
								value="${description}" /></td>
							<td width="85" align="right"><input type="submit"
								class="tabSub" value="查 询" /></td>
						</tr>
					</tbody>
				</table>
				<div class="zixun fix">
					<table class="tab2" width="100%">
						<tbody>
							<tr>
								<th><input type="checkbox" id="all" onclick="#" /></th>
								<th>序号</th>
								<th>指令名称</th>
								<th>描述</th>
								<th>内容</th>
							</tr>
							<!-- EL表达式中的循环遍历和if条件语句 -->
							<c:forEach items="${messageList}" var="message"
								varStatus="status">
								<tr
									<c:if test="${status.index % 2 != 0}">style='background-color:#ECF6EE;'</c:if>>
									<!-- checkbox被选中时，id才会被提交 -->
									<td><input type="checkbox" name="id" value="${message.id}" /></td>
									<td>${status.index + 1}</td>
									<td>${message.command}</td>
									<td>${message.description}</td>
									<td><a href="#">修改</a>&nbsp;&nbsp;&nbsp; <!-- 这边用get方法不是很好，最好用post方法提交表单，这边为了方便起见 -->
										<a href="${basePath}DeleteOneServlet.action?id=${message.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class='page fix'>
						共 <b>${page.totalNumber}</b> 条
						<c:if test="${page.currentPage != 1}">
							<a href="javascript:changeCurrentPage('1')" class='first'>首页</a>
							<a href="javascript:changeCurrentPage('${page.currentPage-1}')"
								class='pre'>上一页</a>
						</c:if>
						当前第<span>${page.currentPage}/${page.totalPage}</span>页
						<c:if test="${page.currentPage != page.totalPage}">
							<a href="javascript:changeCurrentPage('${page.currentPage+1}')"
								class='next'>下一页</a>
							<a href="javascript:changeCurrentPage('${page.totalPage}')"
								class='last'>末页</a>
						</c:if>
						跳至&nbsp;<input id="currentPageText" type='text'
							value='${page.currentPage}' class='allInput w28' />&nbsp;页&nbsp;
						<a
							href="javascript:changeCurrentPage($('#currentPageText').val())"
							class='go'>GO</a>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>