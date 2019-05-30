package nikolay.homework8;

import org.junit.jupiter.api.Test;

public class HashMapTest {

    @Test
    public void homework() {
        CustomChainingHashMap<String, String> map = new CustomChainingHashMap<>();

        map.put("Собака", "Der Hund");
        map.put("Кот", "Die Katze");
        map.put("Хотеть", "Wollen");
        map.put("Смотреть", "zu beobachten");
        map.put("Слушать", "hör zu");
        map.put("Лес", "der Wald");
        map.put("Гриб", "der Pilz");
        map.put("Картина", "das Bild");
        map.put("Площадь", "der Platz");
        map.put("Москва", "Moscau");

        assert !map.isEmpty();
        assert map.size() == 10;
        assert map.get("Кот").equals("Die Katze");

        assert map.contains("Кот");

        try {
            map.put(null, "ex");
        } catch (Exception ex) {
            assert ex instanceof IllegalArgumentException;
        }
    }
}
