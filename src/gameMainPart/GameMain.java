/**
 *  |***ֲ���ս��ʬ��Ϸ***|
 *  �ο�������֮ҹ�����ؿ�
 * 
 * @author ��˼Զ
 */

package gameMainPart;

import javax.swing.JFrame;

/*
 * ���ࣺ
 * ���������
 * ������Ϸ���Ʒ���
 */
public class GameMain {

//	��Ϸ���弰�����йز���
	public static final int WIDTH = 1000;
	public static final int HEIGTH = 630;
	public static JFrame frame;
	public static GamePlay playPanel;
	
//	�����������ش��壬�������֣�������Ϸ
	public static void main(String[] args) {

		frame = new JFrame();
	//	���ش�������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGTH);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	//	�����̼߳�������
		Runnable music = new GameBackGroundMusic("BackGroundMusic.wav");
		Thread go = new Thread(music);
		go.start();

	//	�½������������ӵ�����
		playPanel = new GamePlay(frame);
		frame.add(playPanel);
		
	//	������Ϸ	//
		playPanel.play();
	}

//	���豾���ʵ����
	private GameMain() {
	}
}
