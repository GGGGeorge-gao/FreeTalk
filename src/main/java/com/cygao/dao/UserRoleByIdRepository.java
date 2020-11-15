package com.cygao.dao;

import com.cygao.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
public class UserRoleByIdRepository implements Converter<Integer, UserRole> {

  private final RolesRepository rolesRepo;

  @Autowired
  public UserRoleByIdRepository(RolesRepository rolesRepo) {
    this.rolesRepo = rolesRepo;
  }

  @Override
  public UserRole convert(Integer id) {
    return rolesRepo.findRoleById(id);
  }
}
