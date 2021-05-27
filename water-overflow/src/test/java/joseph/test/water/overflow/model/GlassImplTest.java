package joseph.test.water.overflow.model;

import joseph.test.water.overflow.model.impl.GlassImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GlassImplTest {

    private static final Integer DEFAULT_GLASS_CAPACITY = 250;
    private static final Integer DEFAULT_GLASS_AMOUNT = 0;

    @Test
    public void testDoFillWaterWithZeroOverflow() {
        final GlassImpl glass = new GlassImpl();
        glass.setCapacity(DEFAULT_GLASS_CAPACITY);
        glass.setAmount(DEFAULT_GLASS_AMOUNT);

        final int overflow = glass.doFillWater(DEFAULT_GLASS_CAPACITY);
        Assertions.assertEquals(0, overflow);
    }

    @Test
    public void testDoFillWaterWithZeroWater() {
        final GlassImpl glass = new GlassImpl();
        glass.setCapacity(DEFAULT_GLASS_CAPACITY);
        glass.setAmount(DEFAULT_GLASS_AMOUNT);

        final int overflow = glass.doFillWater(0);
        Assertions.assertEquals(0, overflow);
    }

    @Test
    public void testDoFillWaterWitHalfWater() {
        final GlassImpl glass = new GlassImpl();
        glass.setCapacity(DEFAULT_GLASS_CAPACITY);
        glass.setAmount(DEFAULT_GLASS_AMOUNT);

        final int overflow = glass.doFillWater(DEFAULT_GLASS_CAPACITY / 2);
        Assertions.assertEquals(0, overflow);
        Assertions.assertEquals(DEFAULT_GLASS_CAPACITY / 2, glass.getAmount());
    }

}
