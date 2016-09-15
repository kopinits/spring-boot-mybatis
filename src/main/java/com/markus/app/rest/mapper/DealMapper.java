package com.markus.app.rest.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

import com.markus.app.rest.model.Deal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DealMapper{

	@Select("select * from deal where id = #{id}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Deal findById(@Param("id") Long id);

	@Select("select * from deal")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	List<Deal> findAll();

	@Insert("INSERT into DEAL(id, name, startDate, version, creationTime) VALUES(#{id}, #{name}, #{startDate}, 0, CURRENT_TIMESTAMP)")
	@SelectKey(statement="SELECT DEAL_SEQUENCE.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Long insert(Deal deal);
	
	@Update("UPDATE DEAL SET name=#{name}, startDate=#{startDate}, version=version+1, lastUpdateTime=CURRENT_TIMESTAMP WHERE id =#{id} and version=#{version}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	int update(Deal deal);
}
