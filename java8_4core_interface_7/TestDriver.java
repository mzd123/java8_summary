package java8_4core_interface_7;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8的四大核心接口：
 * 1.消费型接口：Consumer
 * 2.供给型接口：Supplier
 * 3.函数型接口：Function
 * 4.断言型接口：Predicate
 */
public class TestDriver {
    /**
     * 消费型接口:Consumer<String> consumer，指定进来的是String类型，表明进来一个字符串，什么都没返回，这个字符串被怎么怎么消费了
     */
    @Test
    public void test1() {
        Consumer("我是消费型接口，", (x) -> System.out.println(x + "所有进来的都别想出去！"));
    }

    public void Consumer(String string, Consumer<String> consumer) {
        consumer.accept(string);
    }

    /**
     * 供给型接口：Supplier<String> supplier，指定返回值类型，表明我自己产生了一个String类型的字符串并返回出去
     */
    @Test
    public void test2() {
        String string = Supplier(() -> {
            return "我是供给型接口，我返回了一个字符串！";
        });
        System.out.println(string);
    }

    public String Supplier(Supplier<String> supplier) {
        return supplier.get();
    }

    /**
     * 函数型接口: Function<List<Employee>, String> function,第一个参数是被进行处理的参数，第二个参数是返回值类型，至于进行什么操作是lambda体中决定
     */
    @Test
    public void test3() {
        List<Employee> list = Arrays.asList(
                new Employee(1, 3000, 20, "a"),
                new Employee(3, 4000, 30, "b"),
                new Employee(4, 5000, 40, "c"),
                new Employee(2, 6000, 50, "d")
        );
        String string = Function(list, (x) -> {
            String str = "";
            for (Employee e : x
                    ) {
                str += e.getName();
            }
            return str;
        });
        //string和string2等价
        String string2 = Function(list, (x) -> {
            return x.stream().map(Employee::getName).reduce("", String::concat);
        });
        System.out.println(string);
    }

    public String Function(List<Employee> list, Function<List<Employee>, String> function) {
        return function.apply(list);
    }

    /**
     * 断言型接口:Predicate<String> predicate ,指定传入的参数的类型是String类型，然后对这个字符串进行怎么样的断言在lambda中决定，返回值类型是固定的为boolean
     */
    @Test
    public void test4() {
        boolean isF = Predicate("asdfg", (s) -> s.length() > 2);
        System.out.println(isF);
    }

    public boolean Predicate(String string, Predicate<String> predicate) {
        return predicate.test(string);
    }

}
