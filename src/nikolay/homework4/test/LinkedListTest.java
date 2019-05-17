package nikolay.homework4.test;

import nikolay.homework4.datatypes.CustomLinkedList;
import nikolay.homework4.datatypes.LinkedList;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedListTest {

    @Test
    void linkedListTest() {
        LinkedList<Integer> list = new CustomLinkedList<>(){
            {
                addLast(1);
                addLast(2);
                addLast(3);
                addLast(4);
                addLast(5);
                addLast(6);
                addLast(7);
            }
        };

        assert list.get(5) == 6;

        assert list.removeLast() == 7;

        assert list.removeFirst() == 1;

        assert list.contains(5);

        list.remove(5);

        assert !list.contains(5);

        list.addLast(9);

        list.set(3, 99);

        assert list.get(3) == 99;

        assert list.indexOf(9) == 4;

        // test foreach
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i + " ");
        }

        assert list.isEmpty() || list.toString().equals(sb.toString());

        list = new CustomLinkedList<>();

        assert list.isEmpty();


        try {
            list.removeLast();
        } catch (Exception ex) {
            assert ex instanceof NoSuchElementException;
        }

        try {
            list.removeFirst();
        } catch (Exception ex) {
            assert ex instanceof NoSuchElementException;
        }

        try {
            list.get(3773);
        } catch (Exception ex) {
            assert ex instanceof IndexOutOfBoundsException;
        }

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assert list.get(0) == 1;
        assert list.get(1) == 2;
        assert list.get(2) == 3;

        list.set(1, 828);

        assert list.get(1) == 828;

        System.out.println(list);
        assert list.removeByIndex(1) == 828;
        assert list.removeByIndex(0) == 1;
        assert list.removeFirst() == 3;
        assert list.isEmpty();
    }
}
