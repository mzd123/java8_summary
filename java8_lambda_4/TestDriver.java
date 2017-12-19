package java8_lambda_4;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用lambda表达式,简介明了，只要两行代码
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
        List<Employee> list1 = getList(list, (employee) -> employee.getAge() > 30);
        list1.forEach(System.out::println);
    }

    public List<Employee> getList(List<Employee> list, IGetmoel<Employee> iGetmoel) {
        List<Employee> list1 = new ArrayList<Employee>();
        for (Employee e : list) {
            if (iGetmoel.getModel(e)) {
                list1.add(e);
            }
        }
        return list1;
    }

}
