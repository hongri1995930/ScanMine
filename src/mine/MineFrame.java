package mine;

import java.awt.*;
import javax.swing.*;

public class MineFrame {
	JPanel pan = new JPanel();
	MineFrame(){
		pan.setLayout(new GridLayout(Mines.WIDE,Mines.LENGTH)); //设置卡牌式布局
		Mines mine = new Mines();
		for (int i=0;i<Mines.WIDE;i++){
			for (int j=0;j<Mines.LENGTH;j++){
				pan.add(mine.pan[i][j]);
				mine.pan[i][j].setBorder(BorderFactory.createLineBorder(Color.black,1)); //设置边界线
			}
		}
	}
}
