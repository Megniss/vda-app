import java.text.SimpleDateFormat;
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

    // ✅ Papildu konstruktors priekš faila ielādes ar datumu kā tekstu
    public Plant(String name, int interval, String dateStr) {
        this.name = name;
        this.wateringInterval = interval;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.nextWateringDate = sdf.parse(dateStr);
        } catch (Exception e) {
            updateWateringDate(); // fallback, ja parsēšana neizdodas
        }
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

    public void setNextWateringDate(Date date) {
        this.nextWateringDate = date;
    }

    public int getDaysUntilWatering() {
        long millis = nextWateringDate.getTime() - new Date().getTime();
        return (int) Math.ceil(millis / (1000.0 * 60 * 60 * 24));
    }

    public void updateWateringDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, wateringInterval);
        nextWateringDate = cal.getTime();
    }
}