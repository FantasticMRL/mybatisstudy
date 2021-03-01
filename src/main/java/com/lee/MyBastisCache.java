package com.lee;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyBastisCache
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/28 13:26
 * @Version 1.0
 */
public class MyBastisCache {
    public static void main(String[] args) throws IOException {

//        firstCache();
        secondCache();

    }

    private static void secondCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println(mapper.firtLevelCache(1));
            sqlSession.close();

            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            System.out.println(mapper2.firtLevelCache(1));
        } finally {
            sqlSession2.close();
        }
    }

    private static void firstCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println(mapper.firtLevelCache(1));
            System.out.println(mapper.firtLevelCache(1));
            TimeUnit.SECONDS.sleep(15);
            System.out.println(mapper.firtLevelCache(1));
            System.out.println(mapper.firtLevelCache(1));
            System.out.println(mapper.firtLevelCache(1));
            System.out.println(mapper.firtLevelCache(1));
            System.out.println(mapper.firtLevelCache(1));
            System.out.println(mapper.firtLevelCache(1));


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
