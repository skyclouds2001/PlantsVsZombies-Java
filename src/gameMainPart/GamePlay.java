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
 * 游戏主控制类：
 * 控制游戏总体运行
 */
public class GamePlay extends JPanel {

	private static final long serialVersionUID = -1640914817254465950L;
	
//	窗体
	public final JFrame frame;
	
//	本Panel的长宽高
	public static final int WIDTH = 1000;
	public static final int HEIGTH = 600;
	
//	游戏状态枚举类
	public static enum STATE {
		EXIT,   // 游戏结束
		START,	// 在主界面
		RUNNING,	// 在运行游戏
		OVER,	// 游戏结束
		PAUSE;	// 在暂停游戏
		
		// 构造方法
		private STATE() {
		}
		
		// 比较方法
		public boolean compare(STATE state) {
			return this == state;
		}
	}
	
//	草地坐标
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
	
//	游戏状态
	public static STATE state;
	
//	构造方法
	public GamePlay(JFrame frame) {
		this.frame = frame;
	}
	
//	游戏运行控制方法
	public void play() {
//		设置初始状态为START
//		state = STATE.START;
		state = STATE.RUNNING;
//		state = STATE.OVER;		
		
		paint(this.getComponentGraphics(this.getGraphics()));
		
		// 添加鼠标监视器
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
		
		// 添加计时执行器
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
	
	
	// 绘画方法
	public void paint(Graphics g) {
		// 画背景
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
		
		// 画植物
		for(Plant p:plantList) {
			p.drawPlantImage();
		}
		// 画僵尸
		for(Zombie z:zombieList) {
			z.drawZombieImage();
		}
		// 画子弹
		for(Bullet b:bulletList) {
			b.drawBulletImage();
		}
	}

/*
 * Frame 的最底层是 RootPane，
 * 然后是 LayeredPane
 * 再上面就是 ContentPane
 * 最顶层是 GlassPane
 * 
 */
	
//	存储入场的植物、僵尸、子弹
	public ArrayList<Plant> plantList = new ArrayList<>();
	public ArrayList<Zombie> zombieList = new ArrayList<>();
	public ArrayList<Bullet> bulletList = new ArrayList<>();
	
//	其他辅助方法
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
