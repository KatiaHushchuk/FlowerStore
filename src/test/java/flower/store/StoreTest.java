package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

public class StoreTest {
    private static final double PRICE_ROSE = 10.0;
    private static final double PRICE_TULIP = 15.0;
    private static final double PRICE_CHAMOMILE = 5.0;

    private static final int QUANTITY_ROSE = 5;
    private static final int QUANTITY_TULIP = 10;
    private static final int QUANTITY_CHAMOMILE = 15;
    private static final int QUANTITY_UPDATED = 10;

    // Constants for search ranges
    private static final int SEARCH_MIN_QUANTITY = 1;
    private static final int SEARCH_MAX_QUANTITY = 10;
    private static final int SEARCH_MAX_QUANTITY_ALL = 50;
    private static final int SEARCH_QUANTITY_BELOW = 20;
    private static final int SEARCH_QUANTITY_ABOVE = 5;

    private Store store;
    private List<FlowerBucket> flowerBuckets;

    @BeforeEach
    public void init() {
        // Initialize flowers
        Flower rose = new Flower();
        rose.setFlowerType(FlowerType.ROSE);
        rose.setPrice(PRICE_ROSE);

        Flower tulip = new Flower();
        tulip.setFlowerType(FlowerType.TULIP);
        tulip.setPrice(PRICE_TULIP);

        Flower chamomile = new Flower();
        chamomile.setFlowerType(FlowerType.CHAMOMILE);
        chamomile.setPrice(PRICE_CHAMOMILE);

        List.of(rose, tulip, chamomile);

        // Initialize flower packs
        FlowerPack packRose = new FlowerPack(rose, QUANTITY_ROSE);
        FlowerPack packTulip = new FlowerPack(tulip, QUANTITY_TULIP);
        FlowerPack packChamomile = new FlowerPack(chamomile,
            QUANTITY_CHAMOMILE);

        List.of(packRose, packTulip, packChamomile);

        // Initialize flower buckets
        FlowerBucket bucketRose = new FlowerBucket();
        bucketRose.add(packRose);

        FlowerBucket bucketTulip = new FlowerBucket();
        bucketTulip.add(packTulip);

        FlowerBucket bucketMix = new FlowerBucket();
        bucketMix.add(packRose);
        bucketMix.add(packTulip);
        bucketMix.add(packChamomile);

        flowerBuckets = List.of(bucketRose, bucketTulip, bucketMix);

        // Initialize store
        store = new Store();
    }

    @Test
    public void testDefaultConstructor() {
        Assertions.assertTrue(
            store.search(List.of(FlowerType.ROSE), SEARCH_MIN_QUANTITY,
            SEARCH_MAX_QUANTITY).isEmpty());
    }

    @Test
    public void testConstructorWithList() {
        List<FlowerBucket> buckets = List.of(flowerBuckets.get(0),
            flowerBuckets.get(1));
        store = new Store(buckets);
        Assertions.assertTrue(store.search(List.of(FlowerType.ROSE),
            SEARCH_MIN_QUANTITY, SEARCH_MAX_QUANTITY)
            .contains(flowerBuckets.get(0)));
    }

    @Test
    public void testAddToStock() {
        store.addToStock(flowerBuckets.get(0));
        store.addToStock(flowerBuckets.get(1));
        Assertions.assertTrue(store.search(List.of(FlowerType.ROSE),
            SEARCH_MIN_QUANTITY, SEARCH_MAX_QUANTITY)
            .contains(flowerBuckets.get(0)));
        Assertions.assertTrue(store.search(List.of(FlowerType.TULIP),
            SEARCH_MIN_QUANTITY, SEARCH_MAX_QUANTITY)
            .contains(flowerBuckets.get(1)));
    }

    @Test
    public void testSearchSingleFlowerType() {
        store.addToStock(flowerBuckets.get(0));
        store.addToStock(flowerBuckets.get(1));
        List<FlowerBucket> result = store.search(
            List.of(FlowerType.ROSE), QUANTITY_ROSE, QUANTITY_ROSE);
        Assertions.assertTrue(result.contains(flowerBuckets.get(0)));
        Assertions.assertTrue(result.size() == 1);
    }

    @Test
    public void testSearchWithMultipleFlowerTypes() {
        store.addToStock(flowerBuckets.get(0));
        store.addToStock(flowerBuckets.get(1));
        List<FlowerBucket> result = store.search(List.of(
            FlowerType.ROSE, FlowerType.TULIP), QUANTITY_ROSE,
            QUANTITY_CHAMOMILE);
        Assertions.assertTrue(result.contains(flowerBuckets.get(0)));
        Assertions.assertTrue(result.contains(flowerBuckets.get(1)));
        Assertions.assertTrue(result.size() == 2);
    }

    @Test
    public void testSearchAllFlowerTypes() {
        store.addToStock(flowerBuckets.get(2));
        List<FlowerBucket> result = store.search(List.of(
            FlowerType.ROSE, FlowerType.TULIP, FlowerType.CHAMOMILE),
            SEARCH_MIN_QUANTITY, SEARCH_MAX_QUANTITY_ALL);
        Assertions.assertTrue(result.contains(flowerBuckets.get(2)));
        Assertions.assertTrue(result.size() == 1);
    }

    @Test
    public void testSearchNoFlowerTypeMatches() {
        store.addToStock(flowerBuckets.get(0));
        store.addToStock(flowerBuckets.get(1));
        List<FlowerBucket> result = store.search(
            List.of(FlowerType.CHAMOMILE), SEARCH_MIN_QUANTITY,
            SEARCH_QUANTITY_BELOW);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchWithinQuantityRange() {
        store.addToStock(flowerBuckets.get(0));
        List<FlowerBucket> result = store.search(List.of(
            FlowerType.ROSE), QUANTITY_ROSE, QUANTITY_ROSE);
        Assertions.assertTrue(result.contains(flowerBuckets.get(0)));
    }

    @Test
    public void testSearchQuantityBelowRange() {
        store.addToStock(flowerBuckets.get(0));
        List<FlowerBucket> result = store.search(List.of(
            FlowerType.ROSE), QUANTITY_UPDATED, SEARCH_QUANTITY_BELOW);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchQuantityAboveRange() {
        store.addToStock(flowerBuckets.get(1));
        List<FlowerBucket> result = store.search(List.of(
            FlowerType.TULIP), SEARCH_MIN_QUANTITY,
            SEARCH_QUANTITY_ABOVE);
        Assertions.assertTrue(result.isEmpty());
    }
}
