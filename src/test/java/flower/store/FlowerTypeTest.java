package flower.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlowerTypeTest {

    @Test
    public void testEnumValueOf() {
        Assertions.assertTrue(FlowerType.valueOf("CHAMOMILE") == FlowerType.CHAMOMILE);
        Assertions.assertTrue(FlowerType.valueOf("ROSE") == FlowerType.ROSE);
        Assertions.assertTrue(FlowerType.valueOf("TULIP") == FlowerType.TULIP);
    }
}
