package mine;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

class Help extends JFrame{
	private static final long serialVersionUID = 1L;
	Help(){
		super("Help");
		setSize(450,100);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JLabel lab = new JLabel("         You don't know how to play Scan Mines? Go Back to Baidu!");
		add(lab);
		centerWindow();
		setVisible(true);
	}
	private void centerWindow(){
		Toolkit tk=getToolkit();//��ȡ��ʾ�����洰�ڵĴ�С
		Dimension dm=tk.getScreenSize();
		setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
	}
}
