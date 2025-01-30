package parom.status;

import parom.model.Parom;
import parom.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadingStatus extends ParomStatus {
    private static final Logger logger = LogManager.getLogger(LoadingStatus.class);

    public LoadingStatus(Parom parom) {
        super(parom);
    }

    @Override
    public void loadVehicle(Vehicle vehicle) {
        if (parom.canAccommodate(vehicle)) {
            parom.updateCapacityAndWeight(vehicle.getSize(), vehicle.getWeight());
            logger.info("{} loaded on board the parom", vehicle.getType());
        } else {
            logger.warn("{} cannot be loaded due to capacity/weight constraints", vehicle.getType());
        }
    }
}