package joseph.test.water.overflow.model;

import joseph.test.water.overflow.service.GlassService;
import joseph.test.water.overflow.service.impl.GlassServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GlassServiceTest {

    private final int CAPACITY_100 = 100;
    private final int CAPACITY_250 = 250;

    @Test
    public void testCreateGlass() {
        final GlassService glassService = new GlassServiceImpl();

        Glass glass;

        glass = glassService.createGlass(CAPACITY_100);
        assertTrue(checkGlass(glass, CAPACITY_100));

        glass = glassService.createGlass(CAPACITY_250);
        assertTrue(checkGlass(glass, CAPACITY_250));
    }

    private boolean checkGlass(Glass glass, int capacity) {
        assertNotNull(glass);
        assertEquals(capacity, glass.getCapacity());
        assertTrue(glass.getChild().isEmpty());
        return true;
    }
}
