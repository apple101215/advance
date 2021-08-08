package com.sundy.advance.homework.week7.question9.test;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sundy.advance.homework.week7.question9.CurDataSource;
import com.sundy.advance.homework.week7.question9.DataSourceNames;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findUserByFirstDb(long id) {
        return this.baseMapper.selectById(id);
    }

    @CurDataSource(name = DataSourceNames.SECOND)
    @Override
    public SysUser findUserBySecondDb(long id) {
        return this.baseMapper.selectById(id);
    }

}
