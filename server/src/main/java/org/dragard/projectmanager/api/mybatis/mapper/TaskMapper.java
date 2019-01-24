package org.dragard.projectmanager.api.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dragard.projectmanager.entity.Task;

import java.util.Collection;

public interface TaskMapper extends JobEntityMapper<Task> {

    String getAll = "SELECT * FROM tasks";
    String getByUserId = "SELECT * FROM tasks WHERE userId = #{userId}";
    String getById = "select * from tasks where id = #{id}";
    String delete = "DELETE from tasks WHERE id = #{id}";
    String insert = "insert into tasks (id, name, description, projectId, userId) values (#{id}, #{name}, #{description}, #{projectId}, #{userId})";
    String update = "UPDATE tasks SET name = #{name}, description = #{description}, projectId = #{projectId}, userId = #{userId} WHERE id = #{id}";


    @Override
    @Insert(insert)
    void insert(Task element) throws Exception;

    @Override
    @Update(update)
    void update(Task element) throws Exception;

    @Override
    @Select(getAll)
    Collection<Task> getAll() throws Exception;

    @Override
    @Select(getByUserId)
    Collection<Task> getByUserId(String id) throws Exception;

    @Override
    @Select(getById)
    Task getById(String id);

    @Override
    @Delete(delete)
    void delete(String id) throws Exception;


}
