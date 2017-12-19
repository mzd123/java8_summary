package java8_lambda_grammar_6;


@FunctionalInterface
public interface IGetmoel<T> {
    T getModel(T t);

    //boolean getModel2(T t);

    /**
     * 为什么？有了@FunctionalInterface注解之后还能有第二个抽象方法？？？
     * 函数式接口中可以额外定义多个抽象方法，但这些抽象方法签名必须和Object的public方法一样
     */
    String toString();
}
