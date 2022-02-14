package com.music.yymusic_website.dao;

import com.music.yymusic_website.YymusicWebsiteApplication;
import com.music.yymusic_website.domain.User;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YymusicWebsiteApplication.class)
public class User2DaoTest {
    @Autowired
    private User2Dao user2Dao;
    @Test
    public void test(){
        User user=new User();
        user.setName("test_tkmtbatis");
        int i=user2Dao.insert(user);
        System.out.println(i);
    }
    @Test
    public void test2(){
        User user=new User(11, "test4","123456",new Byte("1"),"1233466","1424@qq.com",new Date(),"hhh","shanxi","hhh.jpg",new Date(),new Date());
        user2Dao.insert(user);
    }

    @Test
    public void testUpdate(){
        User user=new User(10, "test4","123456",new Byte("1"),"1233466","1424@qq.com",new Date(),"hhh","shanxi","hhh.jpg",new Date(),new Date());
        int i=user2Dao.updateByPrimaryKey(user);
        // 根据自定义条件修改
        //int j=user2Dao.updateByExample(Example example);

    }

    @Test
    public void testSelect1(){
        List<User> list=user2Dao.selectAll();
        for(User a:list){
            System.out.println(a);
        }
    }

    @Test
    public void testSelect2(){
        User user=user2Dao.selectByPrimaryKey(10);
        System.out.println(user);
    }

    @Test   //条件查询
    public void testSelect3(){
        //创建一个Example对象，封装user查询条件
        Example example=new Example(User.class);

        Example.Criteria criteria=example.createCriteria();
        // 查询条件：名字叫 yaoyao 和 名字叫 瑶瑶瑶 的用户
        criteria.andEqualTo("name","yaoyao");
        criteria.orEqualTo("name","瑶瑶瑶");
        criteria.orLike("name","%test%");

        List<User> list=user2Dao.selectByExample(example);
        for(User a:list){
            System.out.println(a);
        }
    }

    @Test  //分页查询
    public void testSelect4(){
        int pageNum=2;
        int pageSize=5;
        int start=(pageNum-1)*pageSize;

        RowBounds rowBounds=new RowBounds(start,pageSize);
        List<User> list=user2Dao.selectByRowBounds(new User(),rowBounds);
        for(User a:list){
            System.out.println(a);
        }
        //查询结果总数
        int i=user2Dao.selectCount(new User());
        System.out.println(i);
    }

    @Test   // 带条件的查询
    public void testSelect5(){
        int pageNum=1;  // 第几页
        int pageSize=5;  // 每页大小
        int start=(pageNum-1)*pageSize;

        RowBounds rowBounds=new RowBounds(start,pageSize);

        Example example=new Example(User.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andLike("name","%a%");

        List<User> list=user2Dao.selectByExampleAndRowBounds(example,rowBounds);
        for(User a:list){
            System.out.println(a);
        }
        //查询结果总数
        int i=user2Dao.selectCountByExample(example);
        System.out.println(i);
    }

    //关联查询

}
