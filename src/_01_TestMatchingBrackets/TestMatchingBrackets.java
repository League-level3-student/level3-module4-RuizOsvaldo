package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
    /*
     * Use a Stack to complete the method for checking if every opening bracket
     * has a matching closing bracket
     */
    public static boolean doBracketsMatch(String b) {
    	Stack<Character> brackets = new Stack<Character>();
    	int openBracket = 0;
    	int closedBracket = 0;
    	for (int i = 0; i < b.length(); i++) {
			if(b.charAt(i) == '{') {
				openBracket++;
				brackets.push(b.charAt(i));
				
				if (b.charAt(i+1) == '}') {
					closedBracket++;
					brackets.push(b.charAt(i+1));
				}
			}
		}
    	
    	if(openBracket == closedBracket) {
    		return true;
    	}else {
    		return false;
    	}
        
    }
}