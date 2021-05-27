package joseph.test.water.overflow.model;

import joseph.test.water.overflow.service.impl.GlassServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiagramTest {

    private static final Integer DEFAULT_DIAGRAM_DEPTH = 4;
    private static final Integer DEFAULT_DIAGRAM_CAPACITY = 250;

    private Diagram diagram;

    @BeforeEach
    public void setUp() throws Exception {
        diagram = new Diagram(DEFAULT_DIAGRAM_CAPACITY, DEFAULT_DIAGRAM_DEPTH);
        diagram.setGlassService(new GlassServiceImpl());
        diagram.createGlass();
    }

    @Test
    public void testGetEntryPoint() {
        final Glass glass1 = diagram.getEntryGlass();
        assertNotNull(glass1);
        final Glass glass2 = diagram.getGlass(0, 0);
        assertNotNull(glass2);

        assertEquals(glass1, glass2);
    }

    @Test
    public void testCreateGlass() {
        assertNotNull(diagram.getGlasses());
        assertFalse(diagram.getGlasses().isEmpty());
        assertEquals(10, diagram.getGlasses().size());
    }

    @Test
    public void testStructureTop() {
        final Glass glassTop = diagram.getGlass(0, 0);
        assertNotNull(glassTop);
        assertEquals(2, glassTop.getChild().size());
    }

    @Test
    public void testStructure() {
        // Check that all Glasses on all but the bottom row
        // have two child glasses.
        for (int i = 0; i < DEFAULT_DIAGRAM_DEPTH - 1; i++) {
            for (int j = 0; j < i + 1; ++j) {
                final Glass glass = diagram.getGlass(i, j);
                checkGlass(glass, 2);
            }
        }

        // Check that the Glasses on the bottom row do not have
        // child glasses.
        for (int j = 0; j < DEFAULT_DIAGRAM_DEPTH; ++j) {
            final Glass glass = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, j);
            checkGlass(glass, 0);
        }
    }

    @Test
    public void testFillFirstTwoRows() {
        final Glass glassTop = diagram.getGlass(0, 0);
        int overflow;

        checkGlass(glassTop, 2);

        assertTrue(verifyEmptyRow(0));
        assertTrue(verifyEmptyRow(1));

        overflow = glassTop.doFillWater(240);
        assertEquals(0, overflow);
        assertFalse(verifyFullRow(0));

        overflow = glassTop.doFillWater(10);
        assertEquals(0, overflow);
        assertFalse(verifyFullRow(0));

        overflow = glassTop.doFillWater(490);
        assertEquals(490, overflow);
        assertFalse(verifyFullRow(1));

        overflow = glassTop.doFillWater(10);
        assertEquals(10, overflow);
        assertFalse(verifyFullRow(0));
        assertFalse(verifyFullRow(1));
    }

    @Test
    public void testFillingRowEndsDiff() {
        final Glass glassTop = diagram.getGlass(0, 0);
        checkGlass(glassTop, 2);

        glassTop.doFillWater(2000);

        assertFalse(verifyFullRow(2));

        final Glass leBottom = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, 0);
        final Glass lmBottom = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, 1);
        final Glass rmBottom = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, 2);
        final Glass reBottom = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, DEFAULT_DIAGRAM_DEPTH - 1);

        assertEquals(leBottom.getAmount(), reBottom.getAmount());
        assertEquals(lmBottom.getAmount(), rmBottom.getAmount());

        assertNotEquals(leBottom.getAmount(), lmBottom.getAmount());
        assertNotEquals(rmBottom.getAmount(), reBottom.getAmount());
    }

    @Test
    public void testFillAll() {
        final Glass glassTop = diagram.getGlass(0, 0);
        checkGlass(glassTop, 2);

        glassTop.doFillWater(1234);

        for (int row = 0; row < DEFAULT_DIAGRAM_DEPTH; ++row) {
            assertFalse(verifyFullRow(row));
        }
    }

    @Test
    public void testFillBottomInner() {
        final Glass glassTop = diagram.getGlass(0, 0);
        checkGlass(glassTop, 2);
        glassTop.doFillWater(1234);

        final Glass leftGlass = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, 1);
        final Glass rightGlass = diagram.getGlass(DEFAULT_DIAGRAM_DEPTH - 1, 2);

        assertFalse(leftGlass.isFull());
        assertFalse(rightGlass.isFull());
    }

    private boolean verifyEmptyRow(int row) {
        for (int j = 0; j < row + 1; ++j) {
            final Glass glass = diagram.getGlass(row, j);
            if (glass.getAmount() != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyFullRow(int row) {
        for (int j = 0; j < row + 1; ++j) {
            final Glass glass = diagram.getGlass(row, j);
            if (!glass.isFull()) {
                return false;
            }
        }
        return true;
    }

    private void checkGlass(Glass glass, int childCount) {
        assertNotNull(glass);
        assertEquals(childCount, glass.getChild().size());
        assertEquals(DEFAULT_DIAGRAM_CAPACITY, glass.getCapacity());
    }
}
