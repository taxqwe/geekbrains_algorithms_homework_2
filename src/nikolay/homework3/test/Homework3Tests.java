package nikolay.homework3.test;

import nikolay.homework3.datatypes.CustomDeque;
import nikolay.homework3.datatypes.CustomQueue;
import nikolay.homework3.datatypes.CustomStack;
import org.junit.jupiter.api.Test;

class Homework3Tests {

    @org.junit.jupiter.api.Test
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


    @Test
    void dequeTestWithZeroingRight() {
        
        CustomDeque<Integer> deque = new CustomDeque<>();

        deque.insertLeft(3);
        deque.insertLeft(2);
        deque.insertLeft(1);

        deque.insertRight(4);
        deque.insertRight(5);
        deque.insertRight(6);


        System.out.println(deque);

        assert deque.removeLeft() == 1;
        System.out.println(deque);

        assert deque.removeRight() == 6;
        System.out.println(deque);

        assert deque.removeRight() == 5;
        assert deque.removeRight() == 4;
        System.out.println(deque);

        assert deque.removeRight() == 3;
        System.out.println(deque);

        assert !deque.isEmpty();
        assert deque.removeRight() == 2;

        assert deque.isEmpty();
        System.out.println(deque);
    }

    @Test
    void dequeTestWithZeroingLeft() {
        CustomDeque<Integer> deque = new CustomDeque<>();

        deque.insertLeft(3);
        deque.insertLeft(2);
        deque.insertLeft(1);

        deque.insertRight(4);
        deque.insertRight(5);
        deque.insertRight(6);

        assert deque.removeLeft() == 1;
        assert deque.removeLeft() == 2;
        assert deque.removeLeft() == 3;
        assert deque.removeLeft() == 4;

        assert deque.removeLeft() == 5;
        assert deque.removeLeft() == 6;
        assert deque.isEmpty();

        deque = new CustomDeque<>();
        deque.insertLeft(5);
        deque.insertLeft(4);
        deque.insertLeft(3);
        deque.insertLeft(2);
        deque.insertLeft(1);
        deque.insertLeft(0);
        deque.insertRight(8);
        assert deque.removeRight() == 8;
        assert deque.removeRight() == 5;
        assert deque.removeRight() == 4;
        assert deque.removeRight() == 3;
        assert deque.removeLeft() == 0;
        assert deque.removeRight() == 2;
        assert deque.removeRight() == 1;
        assert deque.isEmpty();
    }


    private void fullfillIntegerStack(CustomStack<Integer> stack, int countOfValues) {
        for (int i = 0; i < countOfValues; i++) {
            stack.push((int) (Math.random() * 50 + 1));
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
