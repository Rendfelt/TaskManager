package org.dragard.projectmanager.api.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.dragard.projectmanager.entity.User;

import java.util.Collection;

public interface UserMapper extends EntityMapper<User> {

    String getAll = "SELECT * FROM users";
    String getByLogin = "SELECT * FROM users WHERE name = #{login}";
    String getById = "select * from users where id = #{id}";
    String delete = "DELETE from users WHERE id = #{id}";
    String insert = "insert into users (id, name, password) values (#{id}, #{name}, #{password})";
    String update = "UPDATE users SET password = #{password} WHERE id = #{id}";


    @Override
    @Insert(insert)
    void insert(User element) throws Exception;

    @Override
    @Update(update)
    void update(User element) throws Exception;

    @Override
    @Select(getAll)
    Collection<User> getAll() throws Exception;

    @Select(getByLogin)
    User getByLogin(String id) throws Exception;

    @Override
    @Select(getById)
    User getById(String id);

    @Override
    @Delete(delete)
    void delete(String id) throws Exception;


}
