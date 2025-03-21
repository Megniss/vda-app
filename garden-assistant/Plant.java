import java.util.Calendar;
import java.util.Date;

public class Plant {
    private String name;
    private int wateringInterval;
    private Date nextWateringDate;

    public Plant(String name, int wateringInterval) {
        this.name = name;
        this.wateringInterval = wateringInterval;
        updateWateringDate();
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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, wateringInterval);
        this.nextWateringDate = calendar.getTime();
    }

    public void setNextWateringDate(Date date) {
        this.nextWateringDate = date;
    }
}
