package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

public class StoreTest {

    private Store store;
    private FlowerBucket flowerBucket1;
    private FlowerBucket flowerBucket2;
    private FlowerBucket flowerBucket3;
    private FlowerPack flowerPack1;
    private FlowerPack flowerPack2;
    private FlowerPack flowerPack3;
    private Flower flower1;
    private Flower flower2;
    private Flower flower3;

    @BeforeEach
    public void init() {
        flower1 = new Flower();
        flower1.setFlowerType(FlowerType.ROSE);
        flower1.setPrice(10.0);

        flower2 = new Flower();
        flower2.setFlowerType(FlowerType.TULIP);
        flower2.setPrice(15.0);

        flower3 = new Flower();
        flower3.setFlowerType(FlowerType.CHAMOMILE);
        flower3.setPrice(5.0);

        flowerPack1 = new FlowerPack(flower1, 5);
        flowerPack2 = new FlowerPack(flower2, 10);
        flowerPack3 = new FlowerPack(flower3, 15);

        flowerBucket1 = new FlowerBucket();
        flowerBucket1.add(flowerPack1);

        flowerBucket2 = new FlowerBucket();
        flowerBucket2.add(flowerPack2);

        flowerBucket3 = new FlowerBucket();
        flowerBucket3.add(flowerPack1);
        flowerBucket3.add(flowerPack2);
        flowerBucket3.add(flowerPack3);

        store = new Store();
    }

    @Test
    public void testDefaultConstructor() {
        Assertions.assertTrue(store.search(List.of(FlowerType.ROSE), 1, 10).isEmpty());
    }

    @Test
    public void testConstructorWithList() {
        List<FlowerBucket> buckets = List.of(flowerBucket1, flowerBucket2);
        store = new Store(buckets);
        Assertions.assertTrue(
            store.search(List.of(FlowerType.ROSE), 1, 10).contains(flowerBucket1)
        );
        Assertions.assertTrue(
            store.search(List.of(FlowerType.TULIP), 1, 10).contains(flowerBucket2)
        );
    }

    @Test
    public void testAddToStock() {
        store.addToStock(flowerBucket1);
        store.addToStock(flowerBucket2);
        Assertions.assertTrue(
            store.search(List.of(FlowerType.ROSE), 1, 10).contains(flowerBucket1)
        );
        Assertions.assertTrue(
            store.search(List.of(FlowerType.TULIP), 1, 10).contains(flowerBucket2)
        );
    }

    @Test
    public void testSearchSingleFlowerType() {
        store.addToStock(flowerBucket1);
        store.addToStock(flowerBucket2);
        List<FlowerBucket> result = store.search(List.of(FlowerType.ROSE), 1, 10);
        Assertions.assertTrue(result.contains(flowerBucket1));
        Assertions.assertTrue(result.size() == 1);
    }

    @Test
    public void testSearchWithMultipleFlowerTypes() {
        store.addToStock(flowerBucket1);
        store.addToStock(flowerBucket2);
        List<FlowerBucket> result = store.search(
            List.of(FlowerType.ROSE, FlowerType.TULIP), 5, 15
        );
        Assertions.assertTrue(result.contains(flowerBucket1));
        Assertions.assertTrue(result.contains(flowerBucket2));
        Assertions.assertTrue(result.size() == 2);
    }

    @Test
    public void testSearchAllFlowerTypes() {
        store.addToStock(flowerBucket3);
        List<FlowerBucket> result = store.search(
            List.of(FlowerType.ROSE, FlowerType.TULIP, FlowerType.CHAMOMILE), 1, 50
        );
        Assertions.assertTrue(result.contains(flowerBucket3));
        Assertions.assertTrue(result.size() == 1);
    }

    @Test
    public void testSearchNoFlowerTypeMatches() {
        store.addToStock(flowerBucket1);
        store.addToStock(flowerBucket2);
        List<FlowerBucket> result = store.search(
            List.of(FlowerType.CHAMOMILE), 1, 20
        );
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchWithinQuantityRange() {
        store.addToStock(flowerBucket1);
        List<FlowerBucket> result = store.search(List.of(FlowerType.ROSE), 5, 5);
        Assertions.assertTrue(result.contains(flowerBucket1));
    }

    @Test
    public void testSearchQuantityBelowRange() {
        store.addToStock(flowerBucket1);
        List<FlowerBucket> result = store.search(List.of(FlowerType.ROSE), 6, 10);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchQuantityAboveRange() {
        store.addToStock(flowerBucket2);
        List<FlowerBucket> result = store.search(List.of(FlowerType.TULIP), 1, 5);
        Assertions.assertTrue(result.isEmpty());
    }
}
