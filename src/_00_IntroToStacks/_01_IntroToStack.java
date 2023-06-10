package _00_IntroToStacks;

import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class _01_IntroToStack {
	
    public static void main(String[] args) {
        // 1. Create a Stack of Doubles
        //    Don't forget to import the Stack class
    	Stack<Double> numbers = new Stack<Double>();
        // 2. Use a loop to push 100 random doubles between 0 and 100 to the Stack.
    	for (int i = 0; i < 101; i++) {
			numbers.add((0 + (100 - 0) )*new Random().nextDouble());
		}
        // 3. Ask the user to enter in two numbers between 0 and 100, inclusive. 
    	int num1 = 0;
    	int num2 = 0;
    	boolean q = true;
    	while(q) {
	    	String q1 = JOptionPane.showInputDialog("Enter a number from 0-100.");
	    	String q2 = JOptionPane.showInputDialog("Enter another number from 0-100.");
	    	num1 = Integer.parseInt(q1);
	    	num2 = Integer.parseInt(q2);
	    	if(num1 > num2) {
				int temp = num1;
				num1 = num2;
				num2 = temp;
				q = false;
			}else if(num1 == num2) {
				JOptionPane.showMessageDialog(null, "Please enter different numbers");
			}else {
				q = false;
			}
    	}	
        // 4. Pop all the elements off of the Stack. Every time a double is popped that is
        //    between the two numbers entered by the user, print it to the screen.
    	for (int i = 0; i < numbers.size(); i++) {
			Double num = numbers.pop();
			if(num < num2 && num > num1) {
				System.out.println(num);
			}
		}

        // EXAMPLE:
        // NUM 1: 65
        // NUM 2: 75

        // Popping elements off stack...
        // Elements between 65 and 75:
        // 66.66876846
        // 74.51651681
        // 70.05110654
        // 69.21350456
        // 71.54506465
        // 66.47984807
        // 74.12121224
    }
}
