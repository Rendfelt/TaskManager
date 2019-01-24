package org.dragard.projectmanager.repository;

import org.apache.ibatis.session.SqlSession;
import org.dragard.projectmanager.api.mybatis.mapper.UserMapper;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;

import java.util.Collection;

public class UserMybatisRepositoryImpl extends AbstractMybatisRepository<User>
    implements UserRepository {

    public UserMybatisRepositoryImpl() {
        super(UserMapper.class);
    }

    @Override
    public User getElementByLogin(String login) throws Exception {
        final SqlSession session = getSessionFactory().openSession();
        final UserMapper tm = session.getMapper(UserMapper.class);
        final User result = tm.getByLogin(login);
        session.commit();
        session.close();
        return result;
    }

}
