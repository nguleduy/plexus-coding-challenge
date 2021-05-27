package joseph.test.water.overflow.factory;

import joseph.test.water.overflow.factory.impl.GlassFactoryImpl;
import joseph.test.water.overflow.model.Diagram;
import joseph.test.water.overflow.model.Glass;
import joseph.test.water.overflow.model.Glasses;
import lombok.Data;

@Data
public class DiagramFactory implements GlassesFactory {

    private Integer depth = 0;

    @Override
    public int init(Glasses glasses) {
        final Diagram diagram = (Diagram) glasses;
        final GlassFactory glassFactory = new GlassFactoryImpl();

        int numberOfGlasses = 0;
        for (int i = 0; i < depth; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                final Glass glass = diagram.getGlass(i, j);
                System.out.print(String.format("%d.%d : %s\n", i, j, glassFactory.init(glass)));
                numberOfGlasses++;
            }
        }
        System.out.println("Number of Glasses: " + numberOfGlasses);
        return 0;
    }
}
