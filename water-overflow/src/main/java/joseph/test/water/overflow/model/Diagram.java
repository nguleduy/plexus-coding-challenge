package joseph.test.water.overflow.model;

import joseph.test.water.overflow.service.GlassService;
import joseph.test.water.overflow.service.impl.GlassServiceImpl;
import lombok.Data;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Diagram implements Glasses {

    private static final Logger LOGGER = LoggerFactory.getLogger(Diagram.class);

    private Integer depth;
    private Integer capacity;

    private Map<Location, Glass> glasses;

    private GlassService glassService = new GlassServiceImpl();

    /**
     * @param capacity The capacity of each Glass in the diagram.
     * @param depth    values of < 1 will be adjusted to 1. So, minimum depth will be 1.
     */
    public Diagram(Integer capacity, Integer depth) {
        this.capacity = capacity;
        this.depth = Math.max(1, depth);
        glasses = new HashMap<>();
    }

    @Override
    public void createGlass() {
        List<Glass> child = null;

        // Build the diagram from the bottom to the top.
        for (int row = depth - 1; row >= 0; --row) {
            final List<Glass> currentGlass = new ArrayList<>();

            for (int col = 0; col < row + 1; ++col) {
                final Glass glass = glassService.createGlass(capacity);
                glasses.put(new Location(row, col), glass);
                LOGGER.debug(String.format("%d, %d", row, col));
                currentGlass.add(glass);
            }

            child = ListUtils.emptyIfNull(child);

            if (child.size() > 0) {
                for (int i = 0; i < currentGlass.size(); ++i) {
                    final Glass above = currentGlass.get(i);
                    above.getChild().add(child.get(i));
                    above.getChild().add(child.get(i + 1));
                }
            }
            child = currentGlass;
        }
    }

    @Override
    public Glass getEntryGlass() {
        return getGlass(0, 0);
    }

    public Glass getGlass(Integer row, Integer col) {
        final Location location = new Location(row, col);
        if (glasses.containsKey(location)) {
            return glasses.get(location);
        }
        return null;
    }
}
