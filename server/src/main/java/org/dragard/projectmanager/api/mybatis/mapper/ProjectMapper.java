package org.dragard.projectmanager.api.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dragard.projectmanager.entity.Project;

import java.util.Collection;

public interface ProjectMapper extends JobEntityMapper<Project> {

    String getAll = "SELECT * FROM projects";
    String getByUserId = "SELECT * FROM projects WHERE userId = #{userId}";
    String getById = "select * from projects where id = #{id}";
    String delete = "DELETE from projects WHERE id = #{id}";
    String insert = "insert into projects (id, name, description, userId) values (#{id}, #{name}, #{description}, #{userId})";
    String update = "UPDATE projects SET name = #{name}, description = #{description}, userId = #{userId} WHERE id = #{id}";


    @Override
    @Insert(insert)
    void insert(Project element) throws Exception;

    @Override
    @Update(update)
    void update(Project element) throws Exception;

    @Override
    @Select(getAll)
    Collection<Project> getAll() throws Exception;

    @Override
    @Select(getByUserId)
    Collection<Project> getByUserId(String id) throws Exception;

    @Override
    @Select(getById)
    Project getById(String id);

    @Override
    @Delete(delete)
    void delete(String id) throws Exception;


}
