package parom.status;

import parom.model.Parom;
import parom.model.Vehicle;

public abstract class ParomStatus {
    protected Parom parom;

    public ParomStatus(Parom parom) {
        this.parom = parom;
    }

    public abstract void loadVehicle(Vehicle vehicle);
}