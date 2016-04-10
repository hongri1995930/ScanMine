package mine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Title{
	JPanel pan1 = new JPanel();
	//雷数统计初始化
	static JLabel lab2 = new JLabel("COUNT OF MINE:"+ Mines.count,JLabel.CENTER);	 //雷数统计
	static JPanel pan3 = new JPanel(); //脸
	static Time time = new Time();
	static JLabel lab3; //时间的控件
		
	Title(){
		pan1.setBackground(new Color(0,90,170));
		
		JPanel pan2 = new JPanel();
		pan2.setBackground(new Color(255,222,0));
		pan2.setLayout(new BorderLayout());
	
		//添加脸
		pan3.setLayout(Mines.layout);
		pan3.setBackground(new Color(255,222,0));
		JButton btn1 = new JButton();
		JButton btn2 = new JButton();
		String image1 = "./src/Image/smile.jpg";
		String image2 = "./src/Image/cry.jpg";
		btn1.setIcon(new ImageIcon(image1));
		btn2.setIcon(new ImageIcon(image2));
		pan3.add(btn1, "btn1");
		pan3.add(btn2, "btn2");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Title.time.stop();
				MainClass.game.restar();
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Title.time.stop();
				MainClass.game.restar();
				Mines.layout.first(pan3);
			}
		});
		
		//计时初始化
		lab3 = new JLabel("       TIME 00:00     ",JLabel.CENTER);
		
		pan2.add(lab2,"West");
		pan2.add(pan3,"Center");
		pan2.add(lab3,"East");
		
		pan1.add(pan2);
	}
}