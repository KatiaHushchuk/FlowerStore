package flower.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlowerColorTest {

    @Test
    public void testEnumValueOf() {
        Assertions.assertTrue(
            FlowerColor.valueOf("RED") == FlowerColor.RED);
        Assertions.assertTrue(
            FlowerColor.valueOf("BLUE") == FlowerColor.BLUE);
    }

    @Test
    public void testToString() {
        Assertions.assertTrue("#FF0000".equals
        (FlowerColor.RED.toString()));
        Assertions.assertTrue("#0000FF".equals
        (FlowerColor.BLUE.toString()));
    }

    @Test
    public void testGetStringRepresentation() {
        Assertions.assertTrue("#FF0000".equals(
            FlowerColor.RED.getStringRepresentation()));
        Assertions.assertTrue("#0000FF".equals(
            FlowerColor.BLUE.getStringRepresentation()));
    }
}
