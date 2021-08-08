package com.sundy.advance.homework.week7.question9.test;

import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUser> {

    SysUser findUserByFirstDb(long id);

    SysUser findUserBySecondDb(long id);

}