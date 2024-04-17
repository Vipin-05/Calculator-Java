import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    
    Font myFont = new Font("Ink Free", Font.BOLD, 30);
    
    String equation = ""; // Stores the equation string
    double result = 0;
    
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                equation += String.valueOf(i);
                textfield.setText(equation);
            }
        }
        if (e.getSource() == decButton) {
            equation += ".";
            textfield.setText(equation);
        }
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            equation += " " + ((JButton)e.getSource()).getText() + " ";
            textfield.setText(equation);
        }
        if (e.getSource() == equButton) {
            String[] parts = equation.split(" ");
            double num1 = Double.parseDouble(parts[0]);
            String op = parts[1];
            double num2 = Double.parseDouble(parts[2]);
            double result = 0;
            switch (op) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textfield.setText("Error: Division by zero!");
                        equation = "";
                        return;
                    }
                    break;
            }
            textfield.setText(String.valueOf(result));
            equation = String.valueOf(result);
        }
        if (e.getSource() == clrButton) {
            equation = "";
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            if (equation.length() > 0) {
                equation = equation.substring(0, equation.length() - 1);
                textfield.setText(equation);
            }
        }
        if (e.getSource() == negButton) {
            if (equation.length() > 0) {
                double temp = Double.parseDouble(equation);
                temp *= -1;
                equation = String.valueOf(temp);
                textfield.setText(equation);
            }
        }
    }
}
