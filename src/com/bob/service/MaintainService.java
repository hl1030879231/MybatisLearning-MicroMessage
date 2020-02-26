package com.bob.service;

import java.util.ArrayList;
import java.util.List;

import com.bob.beans.Message;
import com.bob.dao.MessageDao;

/**
 * 维护service，用来控制增删改操作
 * 此外service层还可以用来控制一些业务逻辑，判断等等
 * @author Bob
 *
 */
public class MaintainService {
	/*
	 * 删除单条功能
	 */
	public void deleteOne(String id) {
		MessageDao messageDao =  new MessageDao();
		if(id != null && !"".equals(id.trim())) {
			messageDao.deleteOne(Integer.parseInt(id));
		}
	}
	
	
	/*
	 * 批量删除功能
	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao =  new MessageDao();
		List<Integer> idList = new ArrayList<Integer>();
		for (String id: ids) {
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idList);
	}
	
	/*
	 * 单条插入功能
	 */
	public void addOne(Message message) {
		MessageDao messageDao =  new MessageDao();
		messageDao.addOne(message);
	}
	
}
