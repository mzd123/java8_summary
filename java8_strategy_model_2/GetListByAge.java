package java8_strategy_model_2;

import java.util.ArrayList;
import java.util.List;

public class GetListByAge implements IGetmoel<Employee> {
    @Override
    public boolean getModel(Employee e) {
        return e.getAge() > 30;
    }
}
