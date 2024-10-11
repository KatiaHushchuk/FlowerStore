package flower.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flower {
    private FlowerColor color;
    private double price;
    private double sepallength;
    private FlowerType flowerType;

    public Flower(Flower flower) {
        this.color = flower.color;
        this.flowerType = flower.flowerType;
        this.price = flower.price;
        this.sepallength = flower.sepallength;
    }

    public String getColor() {
        return color.getStringRepresentation();
    }
}
