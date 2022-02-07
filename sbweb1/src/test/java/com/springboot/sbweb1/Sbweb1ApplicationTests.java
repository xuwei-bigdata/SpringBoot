package com.springboot.sbweb1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.sbweb1.entity.User;
import com.springboot.sbweb1.map.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Sbweb1ApplicationTests {
    // 测试git上传

    @Autowired
    private UserMapper userMapper;

    // 1.查询user表里所有的数据
    @Test
    void findAll() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

    // 2.添加操作
    @Test
    public void addUser(){
        User user = new User();

        user.id(5L);
        user.setAge(66);
        user.setName("jbh");
        //user.setEmail("ho@126.com");

        // 自动填充
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());

        int i = userMapper.insert(user);
        System.out.println(i);
    }

    // 3. 修改操作
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1260229766603128834L);
        user.setAge(10);
        user.setName("xo");
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    // 4. 乐观锁
    @Test
    public void optimisticLocker(){
        // 查询后修改
        User user = userMapper.selectById(5L);
        user.age(48);
        userMapper.updateById(user);
    }

    // 5. 分页
    @Test
    public void tsetPage(){
        // 1. 创建page对象 传入两个参数（当前页，每页展示条数）
        Page<User> page = new Page<>(1,3);
        // 2. 进行查询，并将结果封装到page中
        userMapper.selectPage(page, null);

        System.out.println(page.getCurrent());  // 获取当前页
        System.out.println(page.getRecords());  // 获取当前记录的集合
        System.out.println(page.getSize());     // 获取当前页的数据条数
        System.out.println(page.getTotal());    // 获取总数据数
        System.out.println(page.getPages());    // 获取总页数

        System.out.println(page.hasPrevious()); // 是否有上一页
        System.out.println(page.hasNext());     // 是否有下一页
    }

    // 6. 逻辑删除
    @Test
    public void logicDel(){
        userMapper.deleteById(1260229766603128834L);
    }

    // 7. 复杂查询  需要用到对象QueryWrapper
    @Test
    public void complexSelect(){
        // 7.1 查询年龄大于30的人 gt,ge,lt,le
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*wrapper.ge("age",30);
        System.out.println(userMapper.selectList(wrapper));*/

        // 7.2 查询id不为1的人 eq ,ne
        /*wrapper.ne("id", 1);
        System.out.println(userMapper.selectList(wrapper));*/

        // 7.3 查询年龄在30~40岁之间的人 between
        /*wrapper.between("age",30,40);
        System.out.println(userMapper.selectList(wrapper));*/

        // 7.4 查询名字中带有K的人 like
        /*wrapper.like("name","k");
        System.out.println(userMapper.selectList(wrapper));*/

        // 7.5 拼接 last
        wrapper.ne("id",1);
        wrapper.last("and id <> 2 limit 5");
        System.out.println(userMapper.selectList(wrapper));
    }


}
