package mine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mines extends JFrame implements MouseListener{	
	private static final long serialVersionUID = 1L;
	
	static int MINE_COUNT;
	static int WIDE;
	static int LENGTH;
	static int count; 		//当前旗帜的数量
	static CardLayout layout = new CardLayout();
	JButton[][] btn = new JButton[WIDE][LENGTH];
	JLabel[][] lab = new JLabel[WIDE][LENGTH];
	String[][] map = new String[WIDE][LENGTH];
	JPanel[][] pan = new JPanel[WIDE][LENGTH]; 
	boolean[][] flag = new boolean[WIDE][LENGTH];
	
	Mines(){
		for (int i=0;i<WIDE;i++){
			for (int j=0;j<LENGTH;j++){
				map[i][j] = new String();
				flag[i][j] = false;
			}
		}
		makeMine();
		makeNum();
		for (int i=0;i<WIDE;i++){
			for (int j=0;j<LENGTH;j++){
				btn[i][j] = new JButton();
				lab[i][j] = new JLabel(map[i][j],JLabel.CENTER);
				pan[i][j] = new JPanel(layout);
				btn[i][j].setBounds((i-1)*10, (j-1)*10, 10, 10);
				pan[i][j].setBackground(new Color(175,215,237));
				pan[i][j].add(btn[i][j],"button");
				btn[i][j].addMouseListener(this);
				pan[i][j].add(lab[i][j],"label");
				if (lab[i][j].getText() != "0")
					lab[i][j].addMouseListener(this);
			}
		}
	}
	
	private void makeMine(){
		int i=0, x=0 , y=0;
		for (;i< MINE_COUNT;){
			x = (int) ((Math.random()) * WIDE);
			y = (int) ((Math.random()) * LENGTH);
			if (map[x][y].equals("*") == false){
				map[x][y] = "*";
				i++;
			}
		}
	}
	
	//分四角定点，四边，中间三种情况统计
	private void makeNum(){
		int num = 0;
		if (map[0][0].equals("*") == false){
			if (map[0][1].equals("*")) num++;
			if (map[1][1].equals("*")) num++;
			if (map[1][0].equals("*")) num++;
			map[0][0]= num + "";
			num = 0;
		}
		
		if (map[WIDE - 1][LENGTH - 1].equals("*") == false){
			if (map[WIDE - 2][LENGTH - 2].equals("*")) num++;
			if (map[WIDE - 2][LENGTH - 1].equals("*")) num++;
			if (map[WIDE - 1][LENGTH - 2].equals("*")) num++;
			map[WIDE - 1][LENGTH - 1]= num + "";
			num = 0;
		}
		
		if (map[0][LENGTH - 1].equals("*") == false){
			if (map[0][LENGTH - 2].equals("*")) num++;
			if (map[1][LENGTH - 2].equals("*")) num++;
			if (map[1][LENGTH - 1].equals("*")) num++;
			map[0][LENGTH - 1]= num + "";
			num = 0;
		}
		
		if (map[WIDE - 1][0].equals("*") == false){
			if (map[WIDE - 2][0].equals("*")) num++;
			if (map[WIDE - 2][1].equals("*")) num++;
			if (map[WIDE - 1][1].equals("*")) num++;
			map[WIDE - 1][0]= num + "";
			num = 0;
		}
		
		for (int i=1;i<LENGTH - 1;i++){
			if (map[0][i].equals("*") == false){
				if (map[1][i-1].equals("*")) num++;
				if (map[1][i].equals("*")) num++;
				if (map[1][i+1].equals("*")) num++;
				if (map[0][i-1].equals("*")) num++;
				if (map[0][i+1].equals("*")) num++;
				map[0][i] = num +"";
				num = 0;
			}
		}
		
		for (int i=1;i<WIDE - 1;i++){
			if (map[i][0].equals("*") == false){
				if (map[i-1][1].equals("*")) num++;
				if (map[i][1].equals("*")) num++;
				if (map[i+1][1].equals("*")) num++;
				if (map[i-1][0].equals("*")) num++;
				if (map[i+1][0].equals("*")) num++;
				map[i][0] = num +"";
				num = 0;
			}
		}
		
		for (int i=1;i<LENGTH - 1;i++){
			if(map[WIDE - 1][i].equals("*") == false){
				if (map[WIDE - 2][i-1].equals("*")) num++;
				if (map[WIDE - 2][i].equals("*")) num++;
				if (map[WIDE - 2][i+1].equals("*")) num++;
				if (map[WIDE - 1][i-1].equals("*")) num++;
				if (map[WIDE - 1][i+1].equals("*")) num++;
				map[WIDE - 1][i] = num +"";
				num = 0;
			}
		}
		
		for (int i=1;i<WIDE - 1;i++){
			if(map[i][LENGTH - 1].equals("*") == false){
				if (map[i-1][LENGTH - 2].equals("*")) num++;
				if (map[i][LENGTH - 2].equals("*")) num++;
				if (map[i+1][LENGTH - 2].equals("*")) num++;
				if (map[i-1][LENGTH - 1].equals("*")) num++;
				if (map[i+1][LENGTH - 1].equals("*")) num++;
				map[i][LENGTH - 1] = num + "";
				num = 0;
			}
		}
		
		for (int i=1;i<WIDE - 1;i++){
			for (int j=1;j<LENGTH - 1;j++){
				if (map[i][j].equals("*") == false){
					if (map[i-1][j-1].equals("*")) num++;
					if (map[i][j-1].equals("*")) num++;
					if (map[i-1][j].equals("*")) num++;
					if (map[i+1][j-1].equals("*")) num++;
					if (map[i-1][j+1].equals("*")) num++;
					if (map[i+1][j+1].equals("*")) num++;
					if (map[i][j+1].equals("*")) num++;
					if (map[i+1][j].equals("*")) num++;
					map[i][j] = num + "";
					num =0;
				}
			}
		}
	}
	
	//运用递归，若单击的目标map为0则向四周展开
	private void Deal(int i,int j){
		if (map[i][j].equals("0")){
			lab[i][j].setText("");
			layout.next(pan[i][j]);
			flag[i][j] = true;
			try{
				if (flag[i-1][j]==false){
					Deal(i-1,j);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i][j-1]==false){
					Deal(i,j-1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i][j+1]==false){
					Deal(i,j+1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i+1][j]==false){
					Deal(i+1,j);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i-1][j-1]==false){
					Deal(i-1,j-1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i-1][j+1]==false){
					Deal(i-1,j+1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i+1][j-1]==false){
					Deal(i+1,j-1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i+1][j+1]==false){
					Deal(i+1,j+1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		}
		else {
			layout.next(pan[i][j]);
			flag[i][j] = true;
		}
	}

	//双击判断这个数字周围的雷数是否与此位置标签显示的数字相同，相同则展开，否则只有动画效果
	//并且增加判断功能，旗子下不是雷直接结束游戏
	private void DealDoublePress(int i,int j){
		int c=0;
		boolean f = true;
		try {
			if (btn[i-1][j-1].getText() == "!"){
				if (map[i-1][j-1] == "*")c++;
				else { DealFail(i-1,j-1); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i-1][j].getText() == "!"){
				if (map[i-1][j] == "*") c++;
				else {DealFail(i-1,j); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i-1][j+1].getText() == "!"){
				if(map[i-1][j+1] == "*")c++;
				else {DealFail(i-1,j+1); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i][j-1].getText() == "!"){
				if(map[i][j-1] == "*")c++;
				else { DealFail(i,j-1); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i][j+1].getText() == "!"){
				if(map[i][j+1] == "*")c++;
				else {DealFail(i,j+1); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i+1][j-1].getText() == "!"){
				if (map[i+1][j-1] == "*")c++;
				else {DealFail(i+1,j-1); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i+1][j].getText() == "!"){
				if(map[i+1][j] == "*")c++;
				else {DealFail(i+1,j); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		try {
			if (btn[i+1][j+1].getText() == "!"){
				if(map[i+1][j+1] == "*")c++;
				else {DealFail(i+1,j+1); f = false;}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		
		if ((c == Integer.parseInt(map[i][j])) && (f == true)){
			try{
				if (flag[i-1][j]==false){
					Deal(i-1,j);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i][j-1]==false){
					Deal(i,j-1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i][j+1]==false){
					Deal(i,j+1);
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i+1][j]==false){
					Deal(i+1,j);
					}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i-1][j-1]==false){
					Deal(i-1,j-1);
					}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i-1][j+1]==false){
					Deal(i-1,j+1);
					}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i+1][j-1]==false){
					Deal(i+1,j-1);
					}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i+1][j+1]==false){
					Deal(i+1,j+1);
					}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		}
		else {	
			try{
				if (flag[i-1][j]==false){
					btn[i-1][j].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
			
			try{
				if (flag[i][j-1]==false){
					btn[i][j-1].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		
			try{
				if (flag[i][j+1]==false){
					btn[i][j+1].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		
			try{
				if (flag[i+1][j]==false){
					btn[i+1][j].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		
			try{
				if (flag[i-1][j-1]==false){
					btn[i-1][j-1].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		
			try{
				if (flag[i-1][j+1]==false){
					btn[i-1][j+1].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		
			try{
				if (flag[i+1][j-1]==false){
					btn[i+1][j-1].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		
			try{
				if (flag[i+1][j+1]==false){
					btn[i+1][j+1].doClick();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	
			}
		}
	}

	//判断胜利，若旗子下正好是雷，并且雷数统计刚好为0直接胜利，或者选出所有不是雷的方块直接胜利，展开所有并移除监听锁定界面，不需要点满所有button
	private void ChargeVictory(){
		int c = 0;
		int g = 0;
		for(int i=0;i<WIDE;i++){
			for (int j=0;j<LENGTH;j++){
				if ((btn[i][j].getText() == "!") && (map[i][j] == "*")) { c++; g--;}
				if (flag[i][j]==true) g++;
			}
		}
		if ((c == MINE_COUNT && count == 0) || (g == (WIDE * LENGTH - MINE_COUNT))){
			Title.time.stop();
			JOptionPane.showMessageDialog(null, "You win!","Scan Mine",JOptionPane.YES_OPTION);
			Title.lab2.setText("COUNT OF MINE:" + 0);
			for (int i=0;i<WIDE;i++){
				for (int j=0;j<LENGTH;j++){
					if (flag[i][j] == false) { lab[i][j].setText(""); layout.next(pan[i][j]);}
					if (map[i][j].equals("*")) lab[i][j].setText("!");
					if (btn[i][j].getText() == "!"){
						lab[i][j].setText("!");
						layout.next(pan[i][j]);
					}
					btn[i][j].removeMouseListener(this);
					lab[i][j].removeMouseListener(this);
				}
			}
		}
	}

	//判断失败，用红色标明你失败的点，并展开后判断现有旗子和雷是否一一对应，最后锁定界面
	private void DealFail(int n,int m){
		pan[n][m].setBackground(new Color(255,0,0));
		for(int k=0;k<WIDE;k++){
			for(int l=0;l<LENGTH;l++){
				if (flag[k][l] == false){
					if (map[k][l].equals("0")) lab[k][l].setText("");
					layout.next(pan[k][l]); 
				}
				//判断旗帜下是否为雷，不是显示为X
				if (btn[k][l].getText() == "!"){
					if (map[k][l] == "*") lab[k][l].setText("!");
					else lab[k][l].setText("X");
					layout.next(pan[k][l]); 
				}
			}
		}
		layout.next(Title.pan3);
		for (int i=0;i<WIDE;i++){
			for(int j=0;j<LENGTH;j++){
				btn[i][j].removeMouseListener(this);
				lab[i][j].removeMouseListener(this);
			}
		}
		Title.time.stop();
		JOptionPane.showMessageDialog(null,"Fail!","Scan Mine",JOptionPane.ERROR_MESSAGE);
	}
	
	//单击处理左右键
	public void mouseClicked(MouseEvent e){
		for (int i=0;i<WIDE;i++){
			for(int j=0;j<LENGTH;j++){
				if (e.getSource() == btn[i][j]){
					if (e.getButton() == MouseEvent.BUTTON1){
						//第一个按键后开始计时
						int con = 0;
						for(int m=0;m<WIDE;m++){
							for(int n=0;n<LENGTH;n++){
								if (flag[m][n] == true) con++;
							}
						}
						if (con == 0) Title.time.start(); 
						//处理点击的情况
						Deal(i,j);		
						//处理点到雷
						if (map[i][j].equals("*")){
							DealFail(i,j);
						}
					}
					else if (e.getButton() == MouseEvent.BUTTON3){
						if(btn[i][j].getText() == ""){
 						btn[i][j].setText("!");
 						Title.lab2.setText("COUNT OF MINE:" + (--count));
 						flag[i][j] = true;
 						}
						else{
							btn[i][j].setText("");
							Title.lab2.setText("COUNT OF MINE:" + (++count));
							flag[i][j] = false;
						}
 					}
				}
			}
		}
		ChargeVictory(); //每次点击后判断是否胜利结束游戏
	}

	//双击处理
	public void mousePressed(MouseEvent e) {
		int onmask = InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON3_DOWN_MASK;
		for (int i=0;i<WIDE;i++){
			for(int j=0;j<LENGTH;j++){
				if (e.getSource() == lab[i][j]){
					if((e.getModifiersEx() & onmask) == onmask){
						DealDoublePress(i,j);
					}
				}
			}
		}
		ChargeVictory();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
