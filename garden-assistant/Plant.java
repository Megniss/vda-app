import java.util.Date;
import java.util.Calendar;

public class Plant {
    private String name;
    private int wateringInterval;
    private Date nextWateringDate;

    public Plant(String name, int wateringInterval) {
        this.name = name;
        this.wateringInterval = wateringInterval;
        this.updateWateringDate();
    }

    public String getName() {
        return name;
    }

    public Date getNextWateringDate() {
        return nextWateringDate;
    }

    public void updateWateringDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, wateringInterval);
        this.nextWateringDate = calendar.getTime();
    }
}
