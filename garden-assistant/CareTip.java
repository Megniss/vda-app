import java.util.Random;

public class CareTip {
    private static final String[] CARE_TIPS = {
        "Regulāri pārbaudiet augsnes mitrumu pirms laistīšanas.",
        "Novietojiet augu atbilstoši tā gaismas prasībām.",
        "Izvairieties no pārmērīgas laistīšanas – saknes var sākt pūt.",
        "Mēslojiet augus pavasarī un vasarā, kad tie visvairāk aug.",
        "Ja lapas sāk dzeltēt, pārbaudiet, vai augs nav pārlaistīts."
    };

    public static String getRandomTip() {
        Random random = new Random();
        return CARE_TIPS[random.nextInt(CARE_TIPS.length)];
    }
}
