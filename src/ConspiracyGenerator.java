import java.util.Random;

public class ConspiracyGenerator {
    private static final String[] CONSPIRACY_PHRASES = {
            "Это знает только Илон Маск!",
            "Правительство скрывает правду!",
            "5G управляет этим процессом!",
            "Инопланетяне уже здесь!",
            "Это всё из-за плоской Земли!",
            "Коты — шпионы рептилоидов!"
    };

    public String addConspiracy(String text) {
        Random random = new Random();
        String phrase = CONSPIRACY_PHRASES[random.nextInt(CONSPIRACY_PHRASES.length)];
        return text + "\n[Тайна раскрыта]: " + phrase;
    }
}