package mariocat;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class ButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e){ 
		if(e.getActionCommand().equals("START")) {
			System.out.println("NOW START!");
			MainWindow.clearAll();
			//換成生命值介面
			MainWindow.ready();
//			MainWindow.stage1();
			
			MainWindow.setF(1);
		}
	}
}
