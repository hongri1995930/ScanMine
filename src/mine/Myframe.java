package mine;

import java.awt.*;

import javax.swing.*;

public class Myframe extends JFrame{
	private static final long serialVersionUID = 1L;

	Myframe(){
		super("Scan Mine");
		Mines.MINE_COUNT = 10;
		Mines.count = 10;
		Mines.WIDE = 9;
		Mines.LENGTH = 9;
		setSize(325,385);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new Title().pan1,"North");
		setJMenuBar(new Menu().mb);
		add(new MineFrame().pan,"Center");
		centerWindow();
		setResizable(false); //�����϶��ı�����С
		setVisible(true);
	}
	
	public void restar(){
		MainClass.game.getContentPane().removeAll();
		Mines.count = Mines.MINE_COUNT;
		Title.lab2 = new JLabel("COUNT OF MINE:"+ Mines.MINE_COUNT,JLabel.CENTER);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new Title().pan1,"North");
		setJMenuBar(new Menu().mb);
		add(new MineFrame().pan,"Center");
		Title.time = new Time();
		Mines.layout.first(Title.pan3);
		centerWindow();
		setResizable(false); //�����϶��ı�����С
		setVisible(true);
	}
	
	void changeEasy(){
		MainClass.game.getContentPane().removeAll();
		Mines.MINE_COUNT = 10;
		Mines.count = 10;
		Title.lab2 = new JLabel("COUNT OF MINE:"+ Mines.count,JLabel.CENTER);	
		Mines.WIDE = 9;
		Mines.LENGTH = 9;
		setSize(325,385);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new Title().pan1,"North");
		setJMenuBar(new Menu().mb);
		add(new MineFrame().pan,"Center");
		Title.time = new Time();
		Mines.layout.first(Title.pan3);
		centerWindow();
		setResizable(false); //�����϶��ı�����С
		setVisible(true);
	}
	
	void changeMedium(){		
		MainClass.game.getContentPane().removeAll();
		Mines.MINE_COUNT = 40;
		Mines.count = 40;
		Title.lab2 = new JLabel("COUNT OF MINE:"+ Mines.count,JLabel.CENTER);	
		Mines.WIDE = 16;
		Mines.LENGTH = 16;
		setSize(580,620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new Title().pan1,"North");
		setJMenuBar(new Menu().mb);
		add(new MineFrame().pan,"Center");
		Title.time = new Time();
		Mines.layout.first(Title.pan3);
		centerWindow();
		setResizable(false); //�����϶��ı�����С
		setVisible(true);
	}
	
	void changeHard(){
		MainClass.game.getContentPane().removeAll();
		Mines.MINE_COUNT = 99;
		Mines.count = 99;
		Title.lab2 = new JLabel("COUNT OF MINE:"+ Mines.count,JLabel.CENTER);	
		Mines.WIDE = 16;
		Mines.LENGTH = 30;
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new Title().pan1,"North");
		setJMenuBar(new Menu().mb);
		add(new MineFrame().pan,"Center");
		Title.time = new Time();
		Mines.layout.first(Title.pan3);
		centerWindow();
		setResizable(false); //�����϶��ı�����С
		setVisible(true);
	}
	
	private void centerWindow(){
		Toolkit tk=getToolkit();//��ȡ��ʾ�����洰�ڵĴ�С
		Dimension dm=tk.getScreenSize();
		setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
	}
}