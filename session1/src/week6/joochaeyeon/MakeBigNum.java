package week6.joochaeyeon;

import java.util.Stack;

public class MakeBigNum {
    public String makeBigNum(String number, int k) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<number.length(); i++){
            char c = number.charAt(i);

            while(!stack.isEmpty() && k>0 && stack.peek() <c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        while(k>0){
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        for(char c : stack){
            sb.append(c);
        }
        return sb.toString();
    }
}


