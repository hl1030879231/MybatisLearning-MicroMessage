/**
 * 调用后台批量删除方法
 * 使用post方法进行提交表单
 * $("#mainForm")用jquery方式获取id对象，attr用于修改元素的属性值,这里是修改action动作跳转
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action",basePath + "DeleteBatchServlet.action");
	$("#mainForm").submit();
}

/**
 * 跳转到新增页面
 * 使用post方法进行提交表单
 * 使用表单进行跳转带参数更加放吧
 */
function changeToAddView(basePath) {
	$("#addForm").attr("action",basePath + "AddMessage.jsp");
	$("#addForm").submit();
}

/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}