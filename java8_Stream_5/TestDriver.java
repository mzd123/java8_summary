package java8_Stream_5;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * 使用stream,是java能像sql一遍查询     http://blog.csdn.net/young4dream/article/details/76794659
 */
public class TestDriver {
    @Test
    public void test1() {
        List<Employee> list = Arrays.asList(
                new Employee(1, 3000, 20, "a"),
                new Employee(3, 4000, 30, "b"),
                new Employee(4, 5000, 40, "c"),
                new Employee(2, 6000, 50, "d")
        );

        System.out.println("获取年龄大于30的前两个员工,并且每个人的工资乘以2");
        //将集合转换为流
        list.stream()
                .filter((e) -> e.getAge() >= 30)
                //取前两个
                .limit(2)
                //去重
                .distinct()
                .mapToInt(e -> e.getSalary() * 2)
                .forEach(System.out::println);

        System.out.println("获取所有员工的id");
        list.stream()
                .map(Employee::getId)
                .forEach(System.out::println);

        long i = list.stream().filter(employee -> employee.getSalary() > 3000).count();
        System.out.println("工资大于3000的员工数:" + i);
        //Employee::getId等价于e->e.getId()
        //注意：sum（）是intStream中的方法，mapToInt返回的是intStream，map返回的是Stream
        long ii = list.stream().filter(employee -> employee.getSalary() > 3000).mapToInt(Employee::getId).sum();
        System.out.println("工资大于3000的员工id和为：" + ii);

        /**
         * skip: 跳过前N个元素，如果原Stream中包含的元素个数小于N，那么返回空Stream
         */
        System.out.println("跳过前3个，然后取出剩下的名字，将名字变成大写");
        list.stream()
                //除去前三个之后剩下的集合
                .skip(3)
                .map(Employee::getName)
                .map(String::toUpperCase)
                .forEach(System.out::println);


        System.out.println("集合除去null的元素个数");
        List<Integer> list1 = Arrays.asList(1, null, 3, 4, null, 6);
        //获取集合中不为null的元素个数，返回的是long类型的
        long l = list1.stream().filter(num -> num != null).count();


        System.out.println("Stream自己创建集合的Stream");
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        integerStream.forEach(System.out::print);
        System.out.println();
        System.out.println("IntStream创建的流");
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        System.out.println();
        System.out.println("rangeClosed右侧开放数组");
        IntStream.range(1, 3).forEach(System.out::print);
        System.out.println();
        System.out.println("rangeClosed闭合数组");
        IntStream.rangeClosed(1, 3).forEach(System.out::print);


        System.out.println();
        /**
         * 将字符串数组转化为流
         */
        System.out.println("将字符串数组转化为流");
        String[] strArray = new String[]{"a", "b", "c"};
        Stream.of(strArray).forEach(System.out::print);

        /**
         * 将流转换为字符串数组
         */
        System.out.println("将流转换为字符串数组");
        List<String> list3 = Arrays.asList(
                "asdf", "ghjk", "qwerty"
        );
        String[] strArray1 = list3.stream().toArray(String[]::new);
        for (String s : strArray1
                ) {
            System.out.println(s);
        }
        /**
         * 将流转换为list集合
         */
        System.out.println("将流转换为list集合");
        List<Employee> list4 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        for (Employee e :
                list4) {
            System.out.println(e.toString());

        }
        /**
         * 将流转换为字符串
         */
        System.out.println("将流转换为字符串");
        String str = list3.stream().collect(Collectors.joining()).toString();
        System.out.println(str);

        /**
         * 一对多的情况下
         * flatMap：将最底层元素抽出来放到一起，即将123456放在一起得到的流
         */
        System.out.println("一对多的情况");
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        inputStream
                .flatMap((x) -> x.stream())
                .forEach(System.out::print);
        /**
         * 如果list6为空 则输出的是：Optional.empty
         * 如果list6有值，则输出的是：Optional[Employee{id=1, salary=3000, age=20, name='a'}]
         */
        System.out.println("获取第一个元素");
        List list6 = new ArrayList<>();
        Optional optional = list6.stream().findFirst();
        System.out.println(optional);


        /**
         * 字符串连接,输出结果为：AABCD
         * reduce的第一个参数是字符串你的头部，即起始值
         */
        System.out.println("字符串拼接");
        String concat = Stream.of("A", "B", "C", "D").reduce("A", String::concat);
        System.out.println(concat);

        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double::min).get();
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();

        System.out.println("集合排序----根据id倒叙排列，然后取每个的年龄");
        list.parallelStream().
                sorted(comparing(Employee::getId).reversed()).
                map(Employee::getAge).
                collect(Collectors.toList()).forEach(System.out::println);


        List<String> list2 = new ArrayList<String>();
        list2.add("I am a boy");
        list2.add("I love the girl");
        list2.add("But the girl loves another girl");
        list2.stream()
                .map(line -> line.split(" "))
                .map(Arrays::stream);
    }


}
