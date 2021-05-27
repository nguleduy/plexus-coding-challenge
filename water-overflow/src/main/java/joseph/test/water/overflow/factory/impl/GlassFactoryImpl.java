package joseph.test.water.overflow.factory.impl;

import joseph.test.water.overflow.factory.GlassFactory;
import joseph.test.water.overflow.model.Glass;

public class GlassFactoryImpl implements GlassFactory {

    @Override
    public String init(Glass glass) {
        return String.format("%4dml", glass.getAmount());
    }
}
