package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlowerPackTest {

    private Flower flower;
    private FlowerPack flowerPack;

    @BeforeEach
    public void init() {
        flower = new Flower();
        flower.setPrice(10.0);
        flowerPack = new FlowerPack(flower, 5);
    }

    @Test
    public void testConstructor() {
        Flower newFlower = new Flower();
        newFlower.setPrice(15.0);
        int quantity = 10;

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
        newFlower.setPrice(20.0);
        flowerPack.setFlower(newFlower);
        Assertions.assertTrue(newFlower == flowerPack.getFlower());
    }

    @Test
    public void testGetQuantity() {
        Assertions.assertTrue(5 == flowerPack.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        flowerPack.setQuantity(10);
        Assertions.assertTrue(10 == flowerPack.getQuantity());
    }
}
