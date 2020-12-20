import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;


public class Calculator extends JFrame implements ActionListener{
	private JFrame frame = new JFrame();
	private String[] keys = {"√x","Pow","C","⏪","sin","cos","tan","÷","7","8","9","×","4","5","6","-","1","2","3","+","+/-","0",".","="} ;
	private JButton buttons[] = new JButton[keys.length]; 
	private JTextField resultText = new JTextField("0");
	private boolean firstDigit = true;
	private double resultNum = 0.0000;
	private String operator = "=";
	private boolean operateValidFlag = true;
	Point pressedPoint;

	//initialize Calculator,layout
	public Calculator() {
        Dimension size = getToolkit().getScreenSize();
        frame.setLocation((size.width-100)/2,(size.height-300)/2);
        init();  
        frame.setTitle("Calculator");
        frame.setSize(280, 400);
        frame.setLocation(500, 300);
        frame.setUndecorated(true);
        frame.setOpacity(0.8f);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }
    private void init(){
        Color color1 = new Color(181, 181, 181);  
        Color color2 = new Color(126, 126, 126);  
        Color color3 = new Color(232, 232, 232);  
        Color color4 = new Color(236,109,81);
        Color color5 = new Color(255,255,255,100);
        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(300, 10));
        textPanel.setLayout(new BorderLayout());
        textPanel.add(resultText);
        resultText.setFont(new Font("楷体",Font.BOLD,43));  
        resultText.setHorizontalAlignment(JTextField.RIGHT); 
        resultText.setEditable(false); 
        resultText.setBorder(null);  
        resultText.setPreferredSize(new Dimension (10,10));
        resultText.setBackground(color1);
        JPanel keysPanel = new JPanel();
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.RIGHT));
        keysPanel.setLayout(new GridLayout(6, 4, 2, 2));

        for(int i = 0; i < 8; i++) {
            buttons[i] = new JButton(keys[i]);
            keysPanel.add(buttons[i]);
            buttons[i].setBackground(color3);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            buttons[i].setBorderPainted(false); 

        }

        for(int i = 8; i < keys.length; i++) {
            buttons[i] = new JButton(keys[i]);
            keysPanel.add(buttons[i]); 
            if((i+1)%4==0) buttons[i].setBackground(color3);
            else buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            buttons[i].setBorderPainted(false); 
 
        }
        	ImageIcon icon = new ImageIcon("ico.png");
        	Dimension preferredSize = new Dimension(20,20);
        	icon.setImage(icon.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT));
            JButton b = new JButton(icon);

            b.setPreferredSize(preferredSize);

            header.add(b);
            header.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) { 
			pressedPoint = e.getPoint(); 
			}
		});
            header.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point point = e.getPoint();
				Point locationPoint = getLocation();
				int x = locationPoint.x + point.x - pressedPoint.x;
				int y = locationPoint.y + point.y - pressedPoint.y;
				frame.setLocation(x, y);
			}
		});
            b.setBackground(Color.white);
            b.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            b.setBorderPainted(false);
           
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

        buttons[23].setBackground(color2);
        buttons[3].setBackground(color4);
        buttons[3].setForeground(Color.WHITE);
        for (int i = 0; i < keys.length; i++) {
            buttons[i].addActionListener(this);
        }
        frame.getContentPane().add("Center", textPanel);
        frame.getContentPane().add("South", keysPanel);
        frame.getContentPane().add("North", header);
        textPanel.setBorder(BorderFactory.createMatteBorder(6,3,1,3,color1));
        keysPanel.setBorder(BorderFactory.createMatteBorder(6,3,3,3,color1));
        header.setBorder(BorderFactory.createMatteBorder(6,3,3,3,color5));
}

public void actionPerformed(ActionEvent ev) {

        String command = ev.getActionCommand(); 
        if (command.equals(keys[3])) {

            doBackspace();
        } else if (command.equals(keys[2])) {

            doC();
        } else if ("0123456789.".indexOf(command) >= 0) {

            doNumber(command);
        } else if(command.equals(keys[0]) || command.equals(keys[4]) || command.equals(keys[5]) ||
                command.equals(keys[6]) || command.equals(keys[20])) {

            doOperator1(command);
        } else {
            doOperator2(command);
        }
    }


    private void doBackspace() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1); 
            if (text.length() == 0) {
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } 
            else {
                resultText.setText(text);
            }
        }
    }


    private void doC() {

        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }


    private void doNumber(String key) {
        if (firstDigit) {

            resultText.setText(key);
        } else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {

            resultText.setText(resultText.getText() + ".");
        } else if (!key.equals(".")) {

            resultText.setText(resultText.getText() + key);
        }
        firstDigit = false;
    }


    private void doOperator1(String key) {
        operator = key;  
        if (operator.equals("tan")) {
 
            if (resultNum%90 == 0) {
                operateValidFlag = false;  
                resultText.setText("Syntax Error");
            } else {
                resultNum = Math.tan(Math.toRadians(getNumberFromText())) ;
            }
        } else if (operator.equals("√x")) {

            if (resultNum < 0) {
                operateValidFlag = false;  
                resultText.setText("Syntax Error");
            } else {
                resultNum = Math.sqrt(getNumberFromText());
            }
        } else if (operator.equals("sin")) {

            resultNum = Math.sin(Math.toRadians(getNumberFromText())) ;
        } else if (operator.equals("cos")) {
 
            resultNum = Math.cos(Math.toRadians(getNumberFromText())) ;
        } else if (operator.equals("+/-")) {

            resultNum = getNumberFromText() * (-1);
            if (operateValidFlag) {

                long t1;
                double t2;
                t1 = (long) resultNum;
                t2 = resultNum - t1;
                if (t2 == 0) {
                    resultText.setText(String.valueOf(t1));
                } else {
                    resultText.setText(String.valueOf(new DecimalFormat("0.0000").format(resultNum)));
                }
            }
            firstDigit = true;
            operateValidFlag = true;
        }
    }


    private void doOperator2(String key) {
        if (operator.equals("÷")) {

            if (getNumberFromText() == 0.0) {
                operateValidFlag = false;  
                resultText.setText("Syntax Error");
            } else {
                resultNum /= getNumberFromText();
            }
        } else if (operator.equals("+")) {

            resultNum += getNumberFromText();
        } else if (operator.equals("-")) {

            resultNum -= getNumberFromText();
        } else if (operator.equals("×")) {

            resultNum *= getNumberFromText();
        } else if (operator.equals("Pow")) {

            resultNum = Math.pow(resultNum,getNumberFromText());
        }else if (operator.equals("=")) {

            resultNum = getNumberFromText();
        }
        if (operateValidFlag) {

            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            } else {
                resultText.setText(String.valueOf(new DecimalFormat("0.0000").format(resultNum)));
            }
        }
        operator = key; 
        firstDigit = true;
        operateValidFlag = true;
    }


    private double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }

		
    public static void main(String[] args) {
        Calculator calculator= new Calculator();
    }
}

