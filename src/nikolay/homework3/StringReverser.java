package nikolay.homework3;

import nikolay.homework3.datatypes.CustomStack;

public class StringReverser {

    public String revert(String s) {
        CustomStack<Character> stack = new CustomStack<>();
        char[] charArray = s.toCharArray();
        for (char c: charArray) {
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
