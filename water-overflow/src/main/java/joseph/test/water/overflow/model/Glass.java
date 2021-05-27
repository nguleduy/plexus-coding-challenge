package joseph.test.water.overflow.model;

import joseph.test.water.overflow.model.impl.GlassImpl;

import java.util.List;

public interface Glass {

    Integer getCapacity();

    Integer getAmount();

    List<GlassImpl> getChild();

    Integer doFillWater(Integer amount);

    Boolean isFull();

}
