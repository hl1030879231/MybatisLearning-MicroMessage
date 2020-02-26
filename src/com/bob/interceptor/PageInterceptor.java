package com.bob.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.bob.entity.Page;

/**
    * 分页拦截器
 *注意创建完拦截器后必须第一时间到Configuration配置文件中注册
 * @author Bob
 *
 */
//这里提供Mybatis中拦截源码方法的注解，type是拦截的类，method是拦截的方法，args是参数
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{
	
	private String test;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//获取到statementHandler，在预编译的时候就拦截
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		// 配置文件中SQL语句的ID，所有前面操作的目的都是为了拿到sql语句的id
	    String id = mappedStatement.getId();
	    //用正则表达式匹配，以“ByPage”结尾的默认是拦截器实现分页，当然这个可以自己定义，整个工程统一即可
	    if(id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			// 原始的SQL语句
			String sql = boundSql.getSql();
			// 查询总条数的SQL语句
			String countSql = "select count(*) from (" + sql + ")a";
			Connection connection = (Connection)invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			
			Map<?,?> parameter = (Map<?,?>)boundSql.getParameterObject();
			Page page = (Page)parameter.get("page");
			if(rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			// 改造后带分页查询的SQL语句
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			//把改造后的sql语句替换回去
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		System.out.println(this.test);
		//Mybatis提供的拦截方法，如果是需要拦截的对象，则返回一个代理类；否则取消拦截，不执行intercept方法，没有代理权
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.test = properties.getProperty("test");
		
	}

}
