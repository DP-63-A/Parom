package parom;

import parom.model.Parom;
import parom.model.Vehicle;
import parom.service.ParomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ParomApp {
    private static final Logger logger = LogManager.getLogger(ParomApp.class);

    public static void main(String[] args) {
        try {


            List<String> vehicleData = Files.readAllLines(Paths.get("C:\\Users\\morji\\IdeaProjects\\WD\\Parom\\src\\main\\java\\parom\\vehicles.txt"));
            List<Vehicle> vehicles = vehicleData.stream().map(Vehicle::fromString).collect(Collectors.toList());

            Parom parom = ParomService.getInstance();

            ExecutorService executor = Executors.newFixedThreadPool(vehicles.size());

            for (Vehicle vehicle : vehicles) {
                executor.submit(() -> {
                    try {
                        parom.boardVehicle(vehicle);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        logger.error("Thread interrupted", e);
                    }
                });
            }

            executor.shutdown();
        } catch (IOException e) {
            logger.error("Error reading input file", e);
            System.out.println("Current working directory: " + System.getProperty("user.dir"));

        }
    }
}