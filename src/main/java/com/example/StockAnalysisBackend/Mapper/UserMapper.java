package com.example.StockAnalysisBackend.Mapper;

import com.example.StockAnalysisBackend.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Alex
 * @date 下午5:11
 */
@Mapper
public interface UserMapper {
    //查找用户
    @Select("select id,username,password from User where username = #{username}")
    User findByUserName(String username);
    //插入用户
    @Insert("INSERT INTO User (id,username, password) VALUES(#{id},#{username}, #{password})")
    int addUser(User user);

}
