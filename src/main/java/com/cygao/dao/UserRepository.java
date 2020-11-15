package com.cygao.dao;

import com.cygao.entity.User;
import com.cygao.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cygao
 */
@Mapper
public interface UserRepository {

  /**
   * 通过用户名与密码查询用户，{@code @Param} 注解的作用是用于传递参数，从而与数据库中的字段名一一对应
   *
   * @param name     用户名
   * @param password 密码
   * @return 用户
   */
  User findUser(@Param("name") String name, @Param("password") String password);

  /**
   * 通过用户名查询用户
   *
   * @param name 用户名
   * @return 用户
   */
  User findUserByName(@Param("name") String name);

  /**
   * 通过用户id查找用户
   * @param id 用户id
   * @return 用户
   */
  User findUserById(@Param("id") int id);

  /**
   * 通过用户名查找id
   * @param name 用户名
   * @return id
   */
  int findIdByName(@Param("name") String name);

  /**
   * 添加一名新用户
   *
   * @param name 用户名
   * @param password 密码
   */
  void addUser(@Param("name") String name, @Param("password") String password);

}
