package com.cygao;

import com.cygao.dao.MessageRepository;
import com.cygao.dao.UserRepository;
import com.cygao.dao.RolesRepository;
import com.cygao.entity.Message;
import com.cygao.entity.User;
import com.cygao.entity.UserRole;
import com.cygao.enums.MessageTypeEnum;
import com.cygao.service.UserService;
import com.cygao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
@MapperScan("com.cygao.dao")
class FreeTalkApplicationTests {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	UserRepository userRepo;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	RolesRepository rolesRepo;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	MessageRepository messageRepo;

	@Autowired
	UserServiceImpl userService;

	@Test
	void test2() {
		rolesRepo.addRole("admin", "管理员");
		rolesRepo.addRole("ordinary", "普通用户");
		rolesRepo.addRole("vip", "超级用户");
		System.out.println(rolesRepo.findRoleByName("admin"));
		List<UserRole> roles = rolesRepo.findAllRoles();
		System.out.println(roles);

		System.out.println("GOGO");

	}

	@Test
	void userServiceTest() {
		if (userService.isExsitByUsername("cygao1")) userService.addUser("cygao1", "123");
	}

	@Test
	void isExistByUsername() {
		log.info(Boolean.toString(userService.isExsitByUsername("asdasdasd")));
	}


}
