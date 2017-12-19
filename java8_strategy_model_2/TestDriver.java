package java8_strategy_model_2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用策略模式,这种模式优点就是test中的代码只需要改变new就行，其他代码不需要变
 * 但是，我多一个需求任然需要写一个类来继承这个接口才行，还需要优化
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
        List<Employee> list1 = getList(list, new GetListByAge());
        for (Employee e : list1
                ) {
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
