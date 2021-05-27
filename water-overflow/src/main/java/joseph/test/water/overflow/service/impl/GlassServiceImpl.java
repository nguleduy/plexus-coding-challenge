package joseph.test.water.overflow.service.impl;

import joseph.test.water.overflow.model.Glass;
import joseph.test.water.overflow.model.impl.GlassImpl;
import joseph.test.water.overflow.service.GlassService;

public class GlassServiceImpl implements GlassService {

    @Override
    public Glass createGlass(Integer capacity) {
        final GlassImpl glass = new GlassImpl();
        glass.setCapacity(capacity);
        return glass;
    }
}
