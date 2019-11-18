package com.ysk.source.dao;

/**
 * 常量--dao（未实现）
 */
import com.ysk.source.entity.Constant;
import java.util.List;

public interface ConstantMapper {

	int deleteByPrimaryKey(String constantId);

	int insert(Constant record);

	Constant selectByPrimaryKey(String constantId);

	List<Constant> selectAll();

	int updateByPrimaryKey(Constant record);
}