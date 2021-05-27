package joseph.test.water.overflow;

import joseph.test.water.overflow.constant.Const;
import joseph.test.water.overflow.factory.DiagramFactory;
import joseph.test.water.overflow.model.Diagram;
import joseph.test.water.overflow.model.Glass;
import joseph.test.water.overflow.model.Glasses;
import joseph.test.water.overflow.service.impl.GlassServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WaterOverflowApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaterOverflowApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WaterOverflowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Start Water Overflow");
        LOGGER.info(String.format("%s", args.length > 0 ? args[0] : ""));

        int amount = Const.DEFAULT_FILL_WATER_AMOUNT;

        LOGGER.info(String.format("Input amount water = %dml", amount));
        init(amount);
    }

    private void init(Integer amount) {
        final Glasses glasses = setup();
        final Glass glass = glasses.getEntryGlass();

        LOGGER.info("Filling from input");
        glass.doFillWater(amount);
        show(glasses);
    }

    private Glasses setup() {
        LOGGER.info("Setup Diagram");
        final Diagram diagram = new Diagram(Const.DEFAULT_GLASS_CAPACITY, Const.DEFAULT_DIAGRAM_DEPTH);
        diagram.setGlassService(new GlassServiceImpl());
        diagram.createGlass();
        return diagram;
    }

    private void show(Glasses glasses) {
        LOGGER.info("Show Diagram");
        final DiagramFactory diagramFactory = new DiagramFactory();
        diagramFactory.setDepth(Const.DEFAULT_DIAGRAM_DEPTH);
        diagramFactory.init(glasses);
    }
}
