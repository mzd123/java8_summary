package java8_AnonymousInnerClass_3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用匿名内部类,发现写了那么多代码，其实就一行是有用的：e.getAge() > 30，并且，每次都要写增强型for循环遍历，还需要优化
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
        List<Employee> list1 = getList(list, new IGetmoel<Employee>() {
            @Override
            public boolean getModel(Employee employee) {
                return employee.getAge() > 30;
            }
        });
        for (Employee e :
                list1) {
            System.out.println(e);
        }
    }

    public List<Employee> getList(List<Employee> list, IGetmoel iGetmoel) {
        List<Employee> list1 = new ArrayList<Employee>();
        for (Employee e : list) {
            if (iGetmoel.getModel(e)) {
                list1.add(e);
            }
        }
        return list1;
    }

}
