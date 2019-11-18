package com.ysk.source.dao;

import com.ysk.source.entity.CollectRecords;
import java.util.List;
import java.util.Map;

/**
 * 收藏记录--dao
 * 
 * @author admin
 *
 */
public interface CollectRecordsMapper {

	// 添加收藏记录
	int insert(CollectRecords record);

	// 取消收藏
	int deleteCollectPharmacy(Map<String, Object> maps);

	// 根据主键id查询收藏记录
	CollectRecords selectByPrimaryKey(String collectId);

	// 查询用户是否收藏该药店
	CollectRecords selectWhetherCollectByuserId(Map<String,Object> maps);

	// 查询用户收藏列表
	List<CollectRecords> getCollectListByUserId(Map<String, Object> maps);

	// 查询所有的收藏记录
	List<CollectRecords> selectAll();

	// 修改收藏记录
	int updateByPrimaryKey(CollectRecords record);

	// 删除收藏记录
	int deleteByPrimaryKey(String collectId);
}