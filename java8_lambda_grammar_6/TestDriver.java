package java8_lambda_grammar_6;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lambda表达式的语法
 * java8中引入一个新的操作符"->"，成为箭头操作符，即lambda操作符，主要分为两个部分：左侧和右侧
 * 左侧：lambda表达式的参数列表
 * 右侧：lambda表达式中所需要执行的功能，即lambda体
 */
public class TestDriver {
    /**
     * 语法一：无参数，无返回值
     */
    @Test
    public void test1() {
        //java8之后，局部内部类中使用了同一级别的局部变量，不需要定义这个变量为final类型，但是实际上还是final类型，只是不需要写了，8之前的都是需要写的
        int i = 1;
        System.out.println("匿名内部类的实现");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("helloworld" + i);
            }
        };
        runnable.run();
        System.out.println("lambda的实现");
        Runnable runnable1 = () -> System.out.println("helloLambda" + i);
        runnable1.run();
    }

    /**
     * 语法二：有一个参数，没有返回值
     * 小括号可以省略不写
     */
    @Test
    public void test2() {
        Consumer consumer = (x) -> System.out.println(x);
        consumer.accept("mzd");
        System.out.println("=============================");
        Consumer consumer1 = x -> System.out.println(x);
        consumer1.accept("mzd");
    }

    /**
     * 语法三：有两个以上的参数，有返回值，并且lambda体中有多条语句
     * 如果lambda体中的语句只有一条的话，return,大括号和lambda体中的";"可以省略
     * 注意：这三个要不一起不写，要不全写
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("我要比价大小");
            return Integer.compare(x, y);
        };
        int i = comparator.compare(2, 3);
        System.out.println(i);
        System.out.println("=============================");
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        int i1 = comparator1.compare(2, 3);
        System.out.println(i1);
    }

    /**
     * 语法四：写到这发现,lambda表达式的参数列表中的参数类型可以省略不写，因为jvm编译器能通过上下文推断出数据类型，即“类型推断”
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        int i = comparator.compare(2, 3);
        System.out.println(i);
        System.out.println("=============================");
        Comparator<Integer> comparator1 = (Integer x, Integer y) -> Integer.compare(x, y);
        int i1 = comparator1.compare(2, 3);
        System.out.println(i1);
    }
    /**
     * 注意：所有的lambda表达式都需要“函数式接口”的支持,上面所有的例子都是函数式接口
     * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface
     * 一旦被@FunctionalInterface注解修饰后的接口，如果有两个抽象方法（排除Object自己的public方法，这也许是你们前面看到Comparator中有两个抽象函数产生的疑惑），就会报错。
     * 例子：可以查看IGetmoel这个接口
     */

    /**
     * 例子：对一个数进行运算
     */
    @Test
    public void test5() {
        Integer ii = doTest(100, (x) -> x * 2);
        System.out.println(ii);
    }

    public Integer doTest(Integer i, IGetmoel<Integer> iGetmoel) {
        return iGetmoel.getModel(i);
    }
}
