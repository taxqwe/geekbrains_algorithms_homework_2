package nikolay.homework3.test;

import nikolay.homework3.datatypes.CustomQueue;
import nikolay.homework3.datatypes.CustomStack;
import org.junit.jupiter.api.Test;

public class Homework3Tests {

    @Test
    void stackTest() {
        CustomStack<Integer> stack = new CustomStack<>();
        assert stack.size() == 0 && stack.isEmpty();
        fullfillIntegerStack(stack, 10);
        assert stack.size() == 10 && !stack.isEmpty();
        System.out.println(stack);
        popTimes(stack, 9);
        assert !stack.isEmpty();
        assert stack.size() == 1;
        stack.pop();
        assert stack.size() == 0 && stack.isEmpty();
        System.out.println(stack);
        fullfillIntegerStack(stack, 20);
        assert stack.size() == 20 && !stack.isEmpty();
        System.out.println(stack);
    }

    @Test
    void queueTest() {
        CustomQueue<Integer> queue = new CustomQueue<>() {
            {
                fillIntegerQueue(this);
            }
        };

        assert queue.dequeue() == 1;
        System.out.println(queue);

        queue.enqueue(99);

        System.out.println(queue);

        assert queue.dequeue() == 2;

        System.out.println(queue);

        fillIntegerQueue(queue);

        System.out.println(queue);
    }


    private void fullfillIntegerStack(CustomStack<Integer> stack, int countOfValues) {
        for (int i = 0; i < countOfValues; i++) {
            stack.push((int)(Math.random() * 50 + 1));
        }
    }

    private void popTimes(CustomStack stack, int times) {
        for (int i = 0; i < times; i++) {
            stack.pop();
        }
    }

    private void fillIntegerQueue(CustomQueue<Integer> queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + 1);
        }
    }
}
