package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlowerPackTest {
    private static final double INITIAL_PRICE = 10.0;
    private static final int INITIAL_QUANTITY = 5;
    private static final double NEW_PRICE = 15.0;
    private static final int UPDATED_QUANTITY = 10;
    private static final double UPDATED_FLOWER_PRICE = 20.0;

    private Flower flower;
    private FlowerPack flowerPack;

    @BeforeEach
    public void init() {
        flower = new Flower();
        flower.setPrice(INITIAL_PRICE);
        flowerPack = new FlowerPack(flower, INITIAL_QUANTITY);
    }

    @Test
    public void testConstructor() {
        Flower newFlower = new Flower();
        newFlower.setPrice(NEW_PRICE);
        int quantity = UPDATED_QUANTITY;

        FlowerPack newFlowerPack = new FlowerPack(newFlower, quantity);

        Assertions.assertEquals(newFlower.getFlowerType(),
                newFlowerPack.getFlower().getFlowerType());
        Assertions.assertEquals(quantity, newFlowerPack.getQuantity());
    }

    @Test
    public void testGetFlower() {
        Assertions.assertEquals(flower.getFlowerType(),
                flowerPack.getFlower().getFlowerType());
    }

    @Test
    public void testSetFlower() {
        Flower newFlower = new Flower();
        newFlower.setPrice(UPDATED_FLOWER_PRICE);
        flowerPack.setFlower(newFlower);
        Assertions.assertTrue(newFlower == flowerPack.getFlower());
    }

    @Test
    public void testGetQuantity() {
        Assertions.assertTrue(INITIAL_QUANTITY == flowerPack.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        flowerPack.setQuantity(UPDATED_QUANTITY);
        Assertions.assertTrue(UPDATED_QUANTITY == flowerPack.getQuantity());
    }
}
