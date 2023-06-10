package _00_IntroToStacks;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class _02_TextUndoRedo implements KeyListener{
    /* 
     * Create a JFrame with a JPanel and a JLabel.
     * 
     * Every time a key is pressed, add that character to the JLabel. It should
     * look like a basic text editor.
     * 
     * Make it so that every time the BACKSPACE key is pressed, the last
     * character is erased from the JLabel.
     * 
     * Save that deleted character onto a Stack of Characters.
     * 
     * Choose a key to be the Undo key. Make it so that when that key is
     * pressed, the top Character is popped  off the Stack and added back to
     * the JLabel.
     */

	JFrame frame;
	JLabel label;
	JPanel panel;
	Stack<Character> removedText = new Stack<Character>();
	
	public void run() {
		
		frame = new JFrame("Text Editor");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		
		label = new JLabel("Press any key to enter here: ");
		panel = new JPanel();
		
		panel.add(label);
		frame.add(panel);
		frame.pack();
	}
	
	@Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        String currentText = label.getText();
        if(c == KeyEvent.VK_BACK_SPACE && !currentText.isEmpty()) {
        	
        	System.out.println(currentText.charAt(currentText.length()-1) + " has been removed.");
        	label.setText(currentText.substring(0, currentText.length()-1));
        	removedText.push(currentText.charAt(currentText.length()-1));
        	
        }else if (c == ']') {
            if (!removedText.empty()) {
                char lastDeleted = removedText.pop();
                label.setText(label.getText() + lastDeleted);
                System.out.println(lastDeleted + " has been popped.");
            }
        }else {
        	System.out.println(c + " has been added.");
        	label.setText(currentText + c);
        }
        panel.add(label);
        frame.add(panel);
        frame.pack();
    }

	public static void main(String[] args) {
		new _02_TextUndoRedo().run();
	}	
	
}
