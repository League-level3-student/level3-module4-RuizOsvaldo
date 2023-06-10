package _03_Hangman;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.util.Stack;

public class Hangman {
	
	JFrame frame = new JFrame("Hangman");
	JLabel instructionsLabel = new JLabel();
	JLabel wordLabel = new JLabel();
	JLabel attemptsLabel = new JLabel();
	JLabel hangmanLabel = new JLabel();
	JPanel panel = new JPanel();
	
	int attemptsLeft = 5;
	String userAnswer = "";
	String secretWord = "";
	String answer = "";
	String hangman = "";
	
	Stack<String> wordStack = new Stack<String>();
	
	public void run() {
		createGUI();
		
		userAnswer = JOptionPane.showInputDialog("Choose a number from 0-266");
		int amountOfWords = Integer.parseInt(userAnswer);
	
		fillStack(amountOfWords);		
		gameOn();
		
	
	}
	
	public void createGUI() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4,1));
		frame.setSize(200, 400);
		
		instructionsLabel.setText("Guess the following word:");
		wordLabel.setText("");
		attemptsLabel.setText("Attempts left: " + attemptsLeft);
		wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hangmanLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		frame.add(instructionsLabel);
		frame.add(wordLabel);
		frame.add(attemptsLabel);
		frame.add(hangmanLabel);
	}
	
	public void fillStack(int amountOfWords) {
		for(int i = 0; i < amountOfWords; i++) {
			String randomWord = Utilities.readRandomLineFromFile("dictionary.txt");
			int j = i+1 ;
			//use while loop
			if(wordStack.contains(randomWord)) {
				System.out.println("'" + randomWord + "' already exist in the stack.");
				while(wordStack.contains(randomWord)) {
					System.out.println("Searching for a new word to add.");
					randomWord = Utilities.readRandomLineFromFile("dictionary.txt");
					System.out.println("Checking to see if '" + randomWord + "' is in the stack");
					if(!wordStack.contains(randomWord)) {
						System.out.println("About to push '" + randomWord + "' into Stack");
						wordStack.push(randomWord);
						System.out.println(j + ". '" + randomWord + "' pushed to stack");
						break;
					}else {
						System.out.println("'" + randomWord + "' already exists in the stack");
					}
					
				}
			}else {
				wordStack.push(randomWord);
				System.out.println(j + ". '" + randomWord + "' pushed to stack");
			}
		}
	}
	
	public void gameOn() {
		answer = wordStack.pop();
		secretWord = answer.replaceAll("[a-zA-Z]", "_");
		wordLabel.setText(secretWord);
		
		char[] answerArr = answer.toCharArray();
		char[] secretWordArr = secretWord.toCharArray();
		int guessesCorrect = 0;
		Stack<Character> lettersGuessed = new Stack<Character>();
		while(attemptsLeft > 0) {
			
			String userGuess = JOptionPane.showInputDialog("There are " + answer.length() + " letters the word. You have "
					+ "guessed " + guessesCorrect + " correct. Guess a letter in the word.");
			char letterGuess = userGuess.charAt(0);
			while(lettersGuessed.contains(letterGuess)) {
				userGuess = JOptionPane.showInputDialog("You've already guessed that letter. Try another one.");
				letterGuess = userGuess.charAt(0);
			}
			
			lettersGuessed.push(letterGuess);
			
			if(answer.contains(String.valueOf(letterGuess))) {
				
				for(int i = 0; i < answerArr.length; i++) {
					if(letterGuess == answerArr[i]) {
						guessesCorrect++;
						secretWordArr[i] = letterGuess;
						secretWord = new String(secretWordArr);
						wordLabel.setText(secretWord);
					}
				}
				
				if(guessesCorrect == answer.length()) {
					JOptionPane.showMessageDialog(null, "YOU WON!");
					playAgain();
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect guess");
				attemptsLeft--;
				attemptsLabel.setText("Attempts left: " + attemptsLeft);
				hangmanLabel.setText("<html>" + drawHangman(attemptsLeft) + "</html>");
				
				if(attemptsLeft <= 0 ) {
					playAgain();
				}
			}
			
			
		
		}
	}
	
	void playAgain() {
		String playAgain = JOptionPane.showInputDialog("Do you want to play again? (Y/N)");
		if(playAgain.equalsIgnoreCase("Y")) {
			attemptsLeft = 5;
			gameOn();
		}else {
			System.exit(0);
		}
	}
	
	public String drawHangman(int attemptsLeft) {
		char[] hangmanArr = {'O', '|', '^', '|', '^'};
		if(attemptsLeft == 4) {
			return hangman += Character.toString(hangmanArr[0]) + "<br>";
		}else if(attemptsLeft == 3) {
			return hangman += "\t" + Character.toString(hangmanArr[1]) + "<br>";
		}else if(attemptsLeft == 2) {
			return hangman += Character.toString(hangmanArr[2]) + "<br>";
		}else if(attemptsLeft == 1) {
			return hangman += "\t" + Character.toString(hangmanArr[3]) + "<br>";
		}else if(attemptsLeft == 0) {
			instructionsLabel.setText("GAME OVER");
			return hangman += Character.toString(hangmanArr[4]);
		}
		
		return "";
		
	}
}
