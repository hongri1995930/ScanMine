package mine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class Menu {
	JMenuBar mb = new JMenuBar();
	Menu(){
		FgMenu mFile = new FgMenu("Game(G)",KeyEvent.VK_G);
		JMenuItem miQuit = new JMenuItem("Exit(X)",KeyEvent.VK_X),
				  miEasy = new JMenuItem("Easy"),
				  miMedium = new JMenuItem("Medium"),
				  miHard = new JMenuItem("Hard"),
				  miNew = new JMenuItem("New Game(N)",KeyEvent.VK_N),
				  miHelp = new JMenuItem("Help(H)",KeyEvent.VK_H);
			miQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK));
			mFile.add(miNew);
			miNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Mines.layout.first(Title.pan3); //脸回到第一个笑脸
					Title.time.stop();
					MainClass.game.restar();
				}
			});
			mFile.addSeparator();
			mFile.add(miEasy);
			miEasy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Title.time.stop();
					MainClass.game.changeEasy();
				}
			});
			mFile.add(miMedium);
			miMedium.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Title.time.stop();
					MainClass.game.changeMedium();
				}
			});
			mFile.add(miHard);
			miHard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Title.time.stop();
					MainClass.game.changeHard();
				}
			});
			mFile.addSeparator();
			mFile.add(miHelp);
			miHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Help();
				}
			});
			mFile.addSeparator();
			mFile.add(miQuit);
			miQuit.addActionListener(new ActionListener(){	
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		mb.add(mFile);
	}
}

class FgMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	public FgMenu(String label){
		super(label);
	}
	public FgMenu(String label,int nAccelerator){
		super(label);
		setMnemonic(nAccelerator); //设置快捷键
	}
}
