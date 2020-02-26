package com.bob.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bob.beans.Command;
import com.bob.db.DBAccess;

public class CommandDao {
	 /**
	  * 根据查询条件查询指令列表
	  */
	 public List<Command> queryCommandList(String name,String description) {
		 DBAccess dbAccess = new DBAccess();
		 List<Command> commandList = new ArrayList<Command>();
		 SqlSession sqlSession = null;
		 Command command = new Command();
		 try {
			sqlSession = dbAccess.getSqlSession();
			command.setName(name);
			command.setDescription(description);
			//通过SqlSession执行sql语句
			commandList = sqlSession.selectList("Command.queryCommandList",command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			 //无论如何都需要关闭sqlSession就在finally中配置
			 if(sqlSession != null) {
				 sqlSession.close();
			 }
		}
		 return commandList;
	 }
}
