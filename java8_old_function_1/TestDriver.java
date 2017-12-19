package java8_old_function_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最原始的做法,每换一种需求都要写一个方法，但是这方法就这一行是不一样的：e.getAge() > 30，那么是否可以优化呢
 */
public class TestDriver {
    @Test
    public void test1() {
        List<Employee> list = Arrays.asList(
                new Employee(1, 3000, 20),
                new Employee(2, 4000, 30),
                new Employee(3, 5000, 40),
                new Employee(4, 6000, 50)
        );
        //获取员工年龄大于30的员工集合
        List<Employee> list1 = getListByage(list);
        for (Employee e : list1) {
            System.out.println(e);
        }
        //获取员工工资大于3000的员工集合
        List<Employee> list2 = getListByage(list);
        for (Employee e : list1) {
            System.out.println(e);
        }
    }

    public List<Employee> getListByage(List<Employee> list) {
        List<Employee> list1 = new ArrayList<Employee>();
        for (Employee e : list) {
            if (e.getAge() > 30) {
                list1.add(e);
            }
        }
        return list1;
    }

    public List<Employee> getListBysalays(List<Employee> list) {
        List<Employee> list1 = new ArrayList<Employee>();
        for (Employee e : list) {
            if (e.getSalary() > 3000) {
                list1.add(e);
            }
        }
        return list1;
    }
}
