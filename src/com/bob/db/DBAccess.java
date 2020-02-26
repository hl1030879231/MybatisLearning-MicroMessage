package com.bob.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/*
 * 访问数据库类
 */
public class DBAccess {
	public SqlSession getSqlSession() throws IOException {
		//1.通过配置文件获取数据库连接信息,resources都是默认从src文件夹开始的
		Reader reader = Resources.getResourceAsReader("com/bob/config/Configuration.xml");
		//2.通过配置信息构建一个SqlSessionFactory
		SqlSessionFactory sqlsessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过SqlSessionFactory打开一个SqlSession
		SqlSession sqlSession = sqlsessionFactory.openSession();
		return sqlSession;
	}
}
