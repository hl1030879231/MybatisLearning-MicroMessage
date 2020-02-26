package com.bob.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bob.beans.Message;
import com.bob.db.DBAccess;

/**
 * 和message表相关的数据库操作
 * @author Bob
 *
 */
 public class MessageDao {
	 
	 /**
	  * 根据查询条件查询消息列表
	  */
	 public List<Message> queryMessageList(Map<String,Object> parameter) {
		 DBAccess dbAccess = new DBAccess();
		 List<Message> messagesList = new ArrayList<Message>();
		 SqlSession sqlSession = null;
		 Message message = new Message();
		 try {
			sqlSession = dbAccess.getSqlSession();
			//通过SqlSession执行sql语句,这边采用接口式编程来实现
			//首先实例化IMessage接口,然后直接调用接口方法即可
			IMessage iMessage = sqlSession.getMapper(IMessage.class);
			messagesList = iMessage.queryMessageList(parameter);
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
		 return messagesList;
	 }
	 
	 /**
		 * 根据查询条件查询消息列表的条数
		 */
		public int count(Message message) {
			DBAccess dbAccess = new DBAccess();
			SqlSession sqlSession = null;
			int result = 0;
			try {
				sqlSession = dbAccess.getSqlSession();
				// 通过sqlSession执行SQL语句
				IMessage imessage = sqlSession.getMapper(IMessage.class);
				result = imessage.count(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(sqlSession != null) {
					sqlSession.close();
				}
			}
			return result;
		}
		
		/**
		 * 根据查询条件分页查询消息列表
		 */
		public List<Message> queryMessageListByPage(Map<String,Object> parameter) {
			DBAccess dbAccess = new DBAccess();
			List<Message> messageList = new ArrayList<Message>();
			SqlSession sqlSession = null;
			try {
				sqlSession = dbAccess.getSqlSession();
				// 通过sqlSession执行SQL语句，用接口式编程实现
				IMessage imessage = sqlSession.getMapper(IMessage.class);
				messageList = imessage.queryMessageListByPage(parameter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(sqlSession != null) {
					sqlSession.close();
				}
			}
			return messageList;
		}
		
	 
	 /**
	  * 单条数据删除功能
	  */
	 public void deleteOne(int id) {
		 DBAccess dbAccess = new DBAccess();
		 SqlSession sqlSession = null;
		 try {
			sqlSession = dbAccess.getSqlSession();
			//通过SqlSession执行sql语句
			// 通过sqlSession执行SQL语句，用接口式编程实现
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteOne(id);
			//sqlSession.delete(id);
			//增删改与查询不用，需要修改数据库，因此需要提交session
			sqlSession.commit();
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
	 }
	 
	 
	 /**
	  * 批量数据删除功能
	  */
	 public void deleteBatch(List<Integer> ids) {
		 DBAccess dbAccess = new DBAccess();
		 SqlSession sqlSession = null;
		 try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句，用接口式编程实现
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteBatch(ids);
			//增删改与查询不用，需要修改数据库，因此需要提交session
			sqlSession.commit();
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
	 }
	 
	 /**
	  * 单条数据插入功能
	  */
	 public void addOne(Message message) {
		 DBAccess dbAccess = new DBAccess();
		 SqlSession sqlSession = null;
		 try {
			sqlSession = dbAccess.getSqlSession();
			//通过SqlSession执行sql语句
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.insertOne(message);
			//增删改与查询不用，需要修改数据库，因此需要提交session
			sqlSession.commit();
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
	 }
	 
	 
	/*
	 * //可以直接在java中测试 public static void main(String[] args) { MessageDao msDao =
	 * new MessageDao(); msDao.queryMessageList("", ""); }
	 */
	 
	 
	 
	 
	 
	 
//	 public List<Message> queryMessageList(String command,String description) {
//		 	List<Message> messagesList = new ArrayList<Message>();
//			try {
//				//1.加载驱动
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				//2.获取链接,其中 ?useSSL=false&serverTimezone=GMT用来控制时区
//				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message?useSSL=false&serverTimezone=GMT","root","root");
//				//3.执行sql语句,最好使用StringBuilder，这个可以动态修改，并在where添加条件1=1,可以动态添加条件
//				StringBuilder sqlString = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT FROM MESSAGE where 1=1");
//				List<String> paramList = new ArrayList<String>();
//				//由于在提交sql请求前就需要获得内容，因此可以用一个list来缓存
//				//非空判断，trim()用来排除输入空格
//				if(command != null && !"".equals(command.trim())) {
//					sqlString.append(" and COMMAND=?");
//					paramList.add(command);
//				}
//				if(description != null && !"".equals(description.trim())) {
//					sqlString.append(" and DESCRIPTION like '%' ? '%'" );
//					paramList.add(description);
//				}
//				PreparedStatement statement = connection.prepareStatement(sqlString.toString());
//				for (int i = 0; i < paramList.size(); i++) {
//					//这步是用来给sql语句中的？添加参数
//					statement.setString(i+1, paramList.get(i));
//				}
//				ResultSet re = statement.executeQuery();
//				//4.遍历返回的结果集
//				
//				while(re.next()) {
//					Message message = new Message();
//					message.setId(re.getString("ID"));
//					message.setCommand(re.getString("COMMAND"));
//					message.setDescription(re.getString("DESCRIPTION"));
//					message.setContent(re.getString("CONTENT"));
//					messagesList.add(message);
//				}
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return messagesList;
//	 }
}
