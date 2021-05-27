package joseph.test.water.overflow.model;

import java.util.List;

public interface Glass {

    Integer getCapacity();

    Integer getAmount();

    List<Glass> getChild();

    Integer doFillWater(Integer amount);

    Boolean isFull();

}
