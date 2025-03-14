import java.util.*;

public class Plant {
    private String name;
    private int wateringInterval;
    private Date nextWateringDate;

    public Plant(String name, int wateringInterval) {
        this.name = name;
        this.wateringInterval = wateringInterval;
        this.nextWateringDate = calculateNextWateringDate();
    }

    private Date calculateNextWateringDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, wateringInterval);
        return calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    public Date getNextWateringDate() {
        return nextWateringDate;
    }

    public void updateWateringDate() {
        this.nextWateringDate = calculateNextWateringDate();
    }
}
