/**
 *  |***植物大战僵尸游戏***|
 *  参考暴风雨之夜制作关卡
 * 
 * @author 陈思远
 */

package gameMainPart;

import javax.swing.JFrame;

/*
 * 主类：
 * 存放主方法
 * 启动游戏控制方法
 */
public class GameMain {

//	游戏窗体及窗体有关参数
	public static final int WIDTH = 1000;
	public static final int HEIGTH = 630;
	public static JFrame frame;
	public static GamePlay playPanel;
	
//	主方法：加载窗体，启动音乐，启动游戏
	public static void main(String[] args) {

		frame = new JFrame();
	//	加载窗体属性
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	//	启动线程加载音乐
		Runnable music = new GameBackGroundMusic("BackGroundMusic.wav");
		Thread go = new Thread(music);
		go.start();

	//	新建面板容器并添加到窗体
		playPanel = new GamePlay(frame);
		frame.add(playPanel);
		
	//	启动游戏	//
		playPanel.play();
	}

//	禁阻本类的实例化
	private GameMain() {
	}
}
