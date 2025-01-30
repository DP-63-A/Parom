package parom.service;

import parom.model.Parom;

public class ParomService {
    private static volatile Parom instance;

    private ParomService() {}

    public static Parom getInstance() {
        if (instance == null) {
            synchronized (ParomService.class) {
                if (instance == null) {
                    instance = new Parom(100, 4000);
                }
            }
        }
        return instance;
    }
}