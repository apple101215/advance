package com.sundy.advance.homework.week7.question9.test;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDynamicCurDataSourceApplicationTests {

    @Autowired
    private SysUserService userService;

    @Test
    public void test() {
        SysUser user = userService.findUserByFirstDb(1);
        log.info("第一个数据库 : [{}]", user.toString());
        SysUser user2 = userService.findUserBySecondDb(1);
        log.info("第二个数据库 : [{}]", user2.toString());
    }
}
