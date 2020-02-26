package com.bob.dao;

import java.util.List;
import java.util.Map;

import com.bob.beans.Message;

public interface IMessage {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(Map<String,Object> parameter);
	
	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message);
	
	/**
	 * 根据查询条件分页查询消息列表，使用拦截器来实现
	 */
	public List<Message> queryMessageListByPage(Map<String,Object> parameter);
	
	/**
	 * 插入一条信息
	 */
	public void insertOne(Message parameter);
	
	/**
	 * 删除一条信息
	 */
	public void deleteOne(Integer parameter);
	
	
	/**
	 * 批量删除信息
	 */
	public void deleteBatch(List<Integer> parameter);
}
