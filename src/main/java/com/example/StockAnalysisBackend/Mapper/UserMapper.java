package com.example.StockAnalysisBackend.Mapper;

import com.example.StockAnalysisBackend.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Alex
 * @date 下午5:11
 */
@Mapper
public interface UserMapper {
    //查找用户
    @Select("select id,username,password from Users where username = #{username}")
    User findByUserName(String username);
    //插入用户
    @Insert("INSERT INTO Users (id,username, password,email) VALUES(#{id},#{username}, #{password},#{email})")
    int addUser(User user);
    //修改用户

    @Update("UPDATE Users SET username=#{username}, password=#{password} WHERE username=#{username}")
    int  updateUser(User user);

}
