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
public interface RolesRepository {

  /**
   * 添加新的角色
   * @param role 角色名
   * @param comment 角色解释
   */
  void addRole(@Param("role") String role, @Param("comment") String comment);

  /**
   * 添加新用户
   * @param user 用户
   */
  void addUser(@Param("user") User user);

  /**
   * 为用户增加一个新角色
   * @param user 用户
   * @param role 角色
   */
  void addRoleForUser(@Param("user") User user, @Param("role") UserRole role);

  /**
   * 根据角色名查找该角色
   * @param role 角色名
   * @return 角色对象
   */
  UserRole findRoleByName(@Param("role") String role);

  /**
   * 根据角色ID查找该角色
   * @param id 角色id
   * @return 角色对象
   */
  UserRole findRoleById(@Param("id") int id);

  /**
   * 查找所有角色
   * @return 所有角色
   */
  List<UserRole> findAllRoles();

  /**
   * 查找用户的角色
   * @param user 用户
   * @return 角色
   */
  List<UserRole> findUserRoles(@Param("user") User user);


}
