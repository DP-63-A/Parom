package parom.model;

import parom.status.ParomStatus;
import parom.status.LoadingStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parom {
    private static final Logger logger = LogManager.getLogger(Parom.class);
    private final Lock lock = new ReentrantLock();
    private ParomStatus status;
    private int availableCapacity;
    private int availableWeight;

    public Parom(int capacity, int weightLimit) {
        this.availableCapacity = capacity;
        this.availableWeight = weightLimit;
        this.status = new LoadingStatus(this);
    }

    public void boardVehicle(Vehicle vehicle) throws InterruptedException {
        lock.lock();
        try {
            status.loadVehicle(vehicle);
        } finally {
            lock.unlock();
        }
    }

    public boolean canAccommodate(Vehicle vehicle) {
        return availableCapacity >= vehicle.getSize() && availableWeight >= vehicle.getWeight();
    }

    public void updateCapacityAndWeight(int size, int weight) {
        availableCapacity -= size;
        availableWeight -= weight;
    }

    public void changeStatus(ParomStatus newStatus) {
        this.status = newStatus;
    }
}