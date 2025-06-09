package Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("PolyOE"); // Đảm bảo "TestCar" là tên persistence unit trong persistence.xml
        }
        return factory.createEntityManager();
    }
}
