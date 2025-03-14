import java.util.Random;

public class CareTip {
    private static final String[] CARE_TIPS = {
        "Regulāri pārbaudiet augsnes mitrumu pirms laistīšanas.",
        "Novietojiet augu atbilstoši tā gaismas prasībām.",
        "Reizi mēnesī noslaukiet putekļus no lapām, lai augs elpotu labāk.",
        "Izvairieties no pārmērīgas laistīšanas – saknes var sākt pūt.",
        "Rudenī un ziemā augus laistiet retāk.",
        "Mēslojiet augus pavasarī un vasarā, kad tie visvairāk aug.",
        "Ja lapas sāk dzeltēt, pārbaudiet, vai augs nav pārlaistīts.",
        "Izmantojiet lietus ūdeni vai nostādinātu krāna ūdeni laistīšanai.",
        "Regulāri apgrieziet nokaltušās vai bojātās lapas, lai veicinātu jaunu augšanu.",
        "Pārstādiet augus reizi gadā, ja tie kļuvuši pārāk lieli podam."
    };

    public static String getRandomTip() {
        Random random = new Random();
        return CARE_TIPS[random.nextInt(CARE_TIPS.length)];
    }
}
