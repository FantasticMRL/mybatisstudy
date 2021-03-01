package com.lee;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {


    Employee selectEmp(Integer id);
    Employee selectEmp2(Integer id);
    Boolean addEmp(Employee employee);
    void del(Integer id);
    Integer update(Employee employee);


    Employee getByIdAndLastName(Integer id,String name);
    List<Employee> getByLastNameLike( String name);

    Map<String,Object> getEmpById(Integer id);

    @MapKey("id")
    Map<Integer,Employee> getByLastNameLike2(String name);

    Department getDept(Integer id);

    List<Employee> getEmpByDeptId(Integer id);

    List<Employee> getEmsByCondition(Employee employee);


    Integer updateByC(Employee e);

    List<Employee> getByIds(@Param("ids") List<Integer> ids);
    List<Employee> getByNamess(@Param("names") List<String> names);

    /*===========================================================================*/

    public Employee firtLevelCache(Integer id);


}
