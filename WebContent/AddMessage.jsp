<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- base路径就是WebContent路径 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="UTF-8">

<script src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js"></script>
<title>插入信息页面</title>
</head>
<body>
		<form action="<%=basePath%>AddList.action" id="addListForm" method="post">
		<div id="idDiv">
				id：</br>
				<%  
           			 String id = request.getParameter("totalNum");  
					Integer idInteger = Integer.parseInt(id);
					++idInteger;
        		 %> 
        		 <input name="id" type="text"  value="<%=idInteger %>" />
			</div>
			<div id="commandDiv">
				指令名称：</br>
				<input name="command" type="text"   />
			</div>
			</br>
		    <div id="descriptionDiv">
				描述：</br>
				<input name="description" type="text" />
			</div>
			</br>
		 	<div id="contentDiv">
				内容：</br>
				<input name="content" type="text" />
			</div>
			</br>
			<input type="submit"  value="提 交" /></td>
		</form>
</body>
</html>