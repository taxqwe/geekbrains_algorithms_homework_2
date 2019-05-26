package nikolay.homework6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CustomTreeMapTest {

    @Test
    public void example() {
        CustomTreeMap<Character, Integer> myTreeMap = new CustomTreeMap<>();
        myTreeMap.put('S', 1);
        myTreeMap.put('E', 1);
        myTreeMap.put('A', 1);
        myTreeMap.put('R', 1);
        myTreeMap.put('C', 1);
        myTreeMap.put('H', 1);
        myTreeMap.put('X', 1);
        myTreeMap.remove('E');
    }

    @Test
    public void heightTest() {
        CustomTreeMap<Character, Integer> myTreeMap = new CustomTreeMap<>();
        myTreeMap.put('S', 1);
        myTreeMap.put('E', 1);
        myTreeMap.put('A', 1);
        myTreeMap.put('R', 1);
        myTreeMap.put('C', 1);
        myTreeMap.put('H', 1);

        System.out.println(myTreeMap.height());

        myTreeMap.removeMin();
        myTreeMap.removeMin();
        myTreeMap.removeMin();

        System.out.println(myTreeMap.height());

        System.out.println(myTreeMap.isBalanced());
    }

    @Test
    public void homework() {
        int countOfMaps = 10000;

        int balanced = 0;
        for (int i = 0; i < countOfMaps; i++) {
            CustomTreeMap<Integer, Integer> map = new CustomTreeMap<>();
            fillMap(map);
            if (map.isBalanced()) {
                balanced++;
            }
        }

        System.out.println("from " + countOfMaps + " CustomTreeMaps balanced is " + balanced + "\r\nnot balanced is " + (countOfMaps - balanced) + " trees");
        System.out.println("not balanced is " + ((((double) balanced) * 100) / (double) countOfMaps) + " %");
        System.out.println("p.s. При большой выборке (в 10000 деревьев) несбалансировынными оказываются 16-17 (ближе к 17) процентов деревьев");

    }

    private void fillMap(CustomTreeMap<Integer, Integer> customTreeMap) {
        while (customTreeMap.height() < 6) {
            customTreeMap.put(ThreadLocalRandom.current().nextInt(-100, 100), 0);
        }
    }
}
