package gameMainPart;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bullet.Bullet;
import bullet.BulletControl;
import plant.Plant;
import plant.PlantControl;
import zombies.Zombie;
import zombies.ZombieControl;

/*
 * ��Ϸ�������ࣺ
 * ������Ϸ��������
 */
public class GamePlay extends JPanel {

	private static final long serialVersionUID = -1640914817254465950L;
	
//	����
	public final JFrame frame;
	
//	��Panel�ĳ����
	public static final int WIDTH = 1000;
	public static final int HEIGTH = 600;
	
//	��Ϸ״̬ö����
	public static enum STATE {
		EXIT,   // ��Ϸ����
		START,	// ��������
		RUNNING,	// ��������Ϸ
		OVER,	// ��Ϸ����
		PAUSE;	// ����ͣ��Ϸ
		
		// ���췽��
		private STATE() {
		}
		
		// �ȽϷ���
		public boolean compare(STATE state) {
			return this == state;
		}
	}
	
//	�ݵ�����
	public static Point [][] glassPos = new Point[9][5];
	static {
		for(int x = 0; x < 9; ++x) {
			for(int y = 0; y < 5; ++y) {
				int px = 210 + 100 * x;
				int py = 90 + 80 * y;
				glassPos[x][y] = new Point(px, py);
			}
		}
	}
	public static Point visitGlass(int row, int column) {
		return glassPos[row][column];
	}
	public static Point getPlantGlass(int x, int y) {
		Point p = null;
		for(int i = 0; i < 9; ++i) {
			for(int j = 0; j < 5; ++j) {
				if(x >= glassPos[i][j].x && y >= glassPos[i][j].y &&
						x <= glassPos[i][j].x + 100 && y <= glassPos[i][j].y + 80)
					p = glassPos[i][j];
			}
		}
		return p;
	}
	
//	��Ϸ״̬
	public static STATE state;
	
//	���췽��
	public GamePlay(JFrame frame) {
		this.frame = frame;
	}
	
//	��Ϸ���п��Ʒ���
	public void play() {
//		���ó�ʼ״̬ΪSTART
//		state = STATE.START;
		state = STATE.RUNNING;
//		state = STATE.OVER;		
		
		paint(this.getComponentGraphics(this.getGraphics()));
		
		// �����������
		MouseAdapter ma = new MouseAdapter() {
			
			private boolean hasClicked = false;
			private Plant plant = null;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				if(state == STATE.RUNNING) {
					if(hasClicked) {
						Plant plant = PlantControl.searchPlant(x, y);
						
						if(this.plant == null) {
							Point p = getPlantGlass(x, y);
							plant.setxy(p.x, p.y);
							plant.goLife();
						}
					}
					else {
						Plant plant = PlantControl.searchPlant(x, y);
						
						if(plant != null) {
							plant.goMove();
							this.plant = plant;
						}
					}
				}
				if(state == STATE.START) {
					
				}
				if(state == STATE.OVER) {
					
				}
				
				repaint();
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state == STATE.RUNNING) {
					for(Plant plant: plantList) {
						if(plant.isMove()) {
							plant.moveTo(e.getX(), e.getY());
							break;
						}
					}
				}
			}
			
		};
		this.addMouseListener(ma);
		this.addMouseMotionListener(ma);
		this.addMouseWheelListener(ma);
		
		// ��Ӽ�ʱִ����
		final int CHECK_TIME = 10;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			int time = 0;
			
			@Override
			public void run() {
				if(state == STATE.RUNNING) {
					this.time += CHECK_TIME;
					
					PlantControl.addPlant(this.time);
					PlantControl.plantSituationCheck();
					BulletControl.bulletSituationCheck();
					ZombieControl.addZombie(this.time);
					ZombieControl.ZombieSituationCheck();
				}
				else
					this.time = 0;
				
				repaint();
			}

		}, CHECK_TIME, CHECK_TIME);
	}
	
	
	// �滭����
	public void paint(Graphics g) {
		// ������
		if(state == STATE.START) {
			Picture.drawBackgroundPicture();
			Picture.drawPicture(3);
			Picture.drawPicture(4);
			Picture.drawPicture(5);
			Picture.drawPicture(6);
			Picture.drawPicture(12);
			Picture.drawPicture(13);
			Picture.drawPicture(14);
		}else if(state == STATE.RUNNING) {
			Picture.drawBackgroundPicture();
			Picture.drawPicture(7);
			Picture.drawPicture(8);
			Picture.drawPicture(9);
			Picture.drawPicture(10);
			Picture.drawPicture(11);
			Picture.drawPicture(15);
		}else if(state == STATE.OVER) {
			Picture.drawBackgroundPicture();
		}
		
		// ��ֲ��
		for(Plant p:plantList) {
			p.drawPlantImage();
		}
		// ����ʬ
		for(Zombie z:zombieList) {
			z.drawZombieImage();
		}
		// ���ӵ�
		for(Bullet b:bulletList) {
			b.drawBulletImage();
		}
	}

/*
 * Frame ����ײ��� RootPane��
 * Ȼ���� LayeredPane
 * ��������� ContentPane
 * ����� GlassPane
 * 
 */
	
//	�洢�볡��ֲ���ʬ���ӵ�
	public ArrayList<Plant> plantList = new ArrayList<>();
	public ArrayList<Zombie> zombieList = new ArrayList<>();
	public ArrayList<Bullet> bulletList = new ArrayList<>();
	
//	������������
	public JFrame visitFrame() {
		return this.frame;
	}
	public JPanel visitThis() {
		return this;
	}
	public void setState(STATE s) {
		state = s;
	}
}
