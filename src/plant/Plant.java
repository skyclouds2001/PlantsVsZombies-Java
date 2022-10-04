package plant;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bullet.Bullet;
import gameMainPart.GameMain;

// 植物类父类
public abstract class Plant {
	
//	总的植物数量:亦即植物子类数量
	public static final int PLANT_NUMBER = 2;
	
//	图像坐标x，y
	protected int x;
	protected int y;
	
//	格子坐标（1~5,1~9）默认（0,0） 行|列
//	在植物种下后起效
	protected int row;
	protected int column;
	
//	父类构造方法
	public Plant(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.label = new JLabel();
	}
	
//	植物类图片基本参数
	protected ImageIcon image;
	protected int width;
	protected int height;
	protected JLabel label;
	
//	绘画植物图片方法
	public JLabel drawPlantImage() {
		
		this.label = new JLabel();
		
	//	设置标签背景图片及标签位置大小
		this.label.setIcon(image);
		this.label.setBounds(this.x, this.y,
				width, height);
		
	//	放置标签
		GameMain.frame.getContentPane().add(this.label);

		return this.label;
	}
	
//	移除植物图片方法
	public void removePlantImage() {
		if(this.label != null)
			gameMainPart.GameMain.frame.getContentPane().remove(label);
		this.label = null;
	}
	
//	各种植物状态
	protected static final int FLOW = 0;		// 掉落过程
	protected static final int MOVE = 1; 		// 被点击后的移动
	protected static final int LIFE = 2;		// 种植：活着
	protected static final int DEAD = 3;		// 种植：死亡
//	类对象的植物状态
	protected int state = FLOW;
	
//	植物实例血量：由子类构造方法赋值
	protected int blood;
//	植物血量：由子类构造方法赋值
	protected int attackingTime;
	
//	访问器方法
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getState() {
		return this.state;
	}
	public int getLive() {
		return this.blood;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public boolean isAttackPlant() {
		return this.isAttackPlant;
	}
	
//	设置器方法
	public void setxy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
// 判断植物的状态
	public boolean isFlow() {
		return this.state == FLOW;
	}
	public boolean isMove() {
		return this.state == MOVE;
	}
	public boolean isLife() {
		return this.state == LIFE;
	}
	public boolean isDead() {
		return this.state == DEAD;
	}
	
//	改变植物状态
	public void goFlow() {
		this.state = FLOW;
	}
	public void goMove() {
		this.state = MOVE;
	}
	public void goLife() {
		this.state = LIFE;
	}
	public void goDead() {
		this.state = DEAD;
	}
	
//	僵尸每10毫秒攻击植物，植物下降的血量
	protected static final int ZOMBIE_HIT_BLOOD_POINTS = 1;
//	植物被攻击扣血方法
	public void loseLive() {
		this.blood -= ZOMBIE_HIT_BLOOD_POINTS;
	}
	
//	植物被选中后的移动，参数x,y为鼠标的坐标
	public void moveTo(int x, int y) {
		this.x = x + this.height / 2;
		this.y = y + this.width / 2;
	}
	
//	标记当前植物是否是攻击植物
//	由子类构造方法赋值	
	protected boolean isAttackPlant;
//	检测植物当前是否应当攻击
	public boolean isAttackable() {
		
		// 标记是否可执行攻击
		boolean flag = false;
		// 记录植物当前行数
		int row = this.row;
		
		synchronized(GameMain.playPanel.zombieList) {
			// 判断有无僵尸与植物同行，有则可进行攻击
			for(zombies.Zombie z: GameMain.playPanel.zombieList) {
				if(z.getLine() == row) {
					flag = true;
					break;
				}
			}
		}
		
		return flag;
	}
	
//	获取子弹方法：子类需实现
	protected abstract Bullet [] getBullet(int x, int y);
	
//	植物攻击方法
	public void plantAttack() {
		int startx = this.x;
		int starty = this.y;
		
		for(Bullet bullet: this.getBullet(startx, starty)) {
			bullet.drawBulletImage();
			GameMain.playPanel.bulletList.add(bullet); 
		}
	}
}
