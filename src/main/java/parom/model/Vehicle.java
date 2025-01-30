package parom.model;

public class Vehicle {
    private final String type;
    private final int size;
    private final int weight;

    public Vehicle(String type, int size, int weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public static Vehicle fromString(String line) {
        String[] parts = line.split(",");
        return new Vehicle(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}
