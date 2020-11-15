package com.cygao.dao;

import com.cygao.entity.LoginInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author cygao
 */
@Repository
public interface LoginInfoRepository {

  /**
   * 添加一条登入登出消息
   * @param loginInfo {@link com.cygao.entity.LoginInfo}
   */
  void addLoginInfo(@Param("loginInfo") LoginInfo loginInfo);

}
