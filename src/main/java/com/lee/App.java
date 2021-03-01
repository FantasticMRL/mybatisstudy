package com.lee;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {


        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//        System.out.println(sqlSessionFactory);

        SqlSession session = sqlSessionFactory.openSession();
//
//        Employee employee = session.selectOne("com.lee.selectEmp", 1);
//
//        System.out.println(employee);


        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

        System.out.println(mapper);
        Employee employee = mapper.selectEmp(1);
        System.out.println();
//        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
//        System.out.println(mapper.selectEmp(1));
//        Employee employee = new Employee();
//        employee.setEmail("11111111111111");
//        employee.setGender("男");
//        employee.setdId(1);
//        employee.setLastName("测试8888");
//        Boolean aBoolean = mapper.addEmp(employee);
//
//        System.out.println(employee);

//        System.out.println(mapper.getByIdAndLastName(1, "admin"));

        Page<Object> page = PageHelper.startPage(4, 3);
        List<Employee> byLastNameLike = mapper.getByLastNameLike("%测试%");
        byLastNameLike.forEach(System.out::println);
//
//        System.out.println("total:"+page.getTotal());
//        System.out.println("PageNum:"+page.getPageNum());
//        System.out.println(page.getPages());

        PageInfo<Employee> employeePageInfo = new PageInfo<>(byLastNameLike,5);
        System.out.println(employeePageInfo.getPages());
        System.out.println(employeePageInfo.getTotal());
        System.out.println(employeePageInfo.isIsFirstPage());

        for (int navigatepageNum : employeePageInfo.getNavigatepageNums()) {
            System.out.println(navigatepageNum);
        }

//        System.out.println(mapper.selectEmp2(1));
//

//        System.out.println(mapper.getDept(1).getDeptName());
//        System.out.println(mapper.getDept(1).getId());
//        for (Employee employee : mapper.getDept(1).getEmployees()) {
//            System.out.println(employee);
//        }




//        Map<Integer, Employee> byLastNameLike2 = mapper.getByLastNameLike2("%tom%");
//        System.out.println(byLastNameLike2);

//        System.out.println(departmentMapper.getDep(1).getDeptName());


//        Employee employee = new Employee();
//        employee.setLastName("admin");
//        employee.setGender("");
//        employee.setEmail("");
//        System.out.println(mapper.getEmsByCondition(employee));
//
//        Employee employee1 = new Employee();
//        employee1.setEmail("测试@111");
//        employee1.setId(1);
//        System.out.println(mapper.updateByC(employee1));

        //admin
        //apple
        //测试
//        System.out.println(mapper.getByIds(Arrays.asList(1, 2, 3)));
//        System.out.println(mapper.getByNamess(Arrays.asList("admin", "apple", "测试")));

        session.commit();





    }
}
