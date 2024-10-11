package flower.store;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<FlowerBucket> stock;
    public Store() {
        this.stock = new ArrayList<FlowerBucket>();
    }

    public Store(List<FlowerBucket> listFlowers) {
        this.stock = List.copyOf(listFlowers);
    }

    public void addToStock(FlowerBucket bucket) {
        stock.add(bucket);
    }

    public List<FlowerBucket> search(List<FlowerType> flowerTypes, 
    int quantityMin, int quantityMax) {
        List<FlowerBucket> result = new ArrayList<>();
        for (FlowerBucket el : stock) {
            boolean check = true;
            int counter = 0;
            for (FlowerPack flowerPack : el.getFlowerPacks()) {
                if (! flowerTypes.contains(flowerPack.getFlower()
                .getFlowerType())) {
                    check = false;
                }
                counter += flowerPack.getQuantity();
            }
            if (check && counter <= quantityMax &&
            counter >= quantityMin) {
                result.add(el);
            }
        }
        return result;
    }
    
}
