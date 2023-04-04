package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT into USERS(username, salt, password, firstname, lastname) " +
            "VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty= "userId", keyColumn = "userid")
    int insert(User user);

    @Select("SELECT * from USERS WHERE username = #{username}")
    User getUserByName(String username);

    @Select("SELECT * from USERS WHERE userid = #{userId}")
    User getUser(int userId);

    @Delete("DELETE from USERS WHERE userid = # userId}")
    void deleteUser(int userId);
}
