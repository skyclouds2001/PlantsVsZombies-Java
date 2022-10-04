package bullet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gameMainPart.GameMain;
import zombies.Zombie;

// 子弹抽象父类
public abstract class Bullet {
	
//	子弹图片坐标
	protected int x;
	protected int y;
	
//	子弹所在行的编号
	protected int line;
	
//	构造方法
	public Bullet(int x, int y) {
		this();
		
		this.x = x;
		this.y = y;
	}
	public Bullet() {
		this.label = new JLabel();
	}
	
//	子弹状态
	protected static final int LIVE = 0;
	protected static final int DEAD = 1;
	protected int state = LIVE;
	
//	子弹移动方向：true表示正向，false表示反向
//	由子类构造方法赋值
	protected boolean position;
	
//	标记子弹是否是冰冻的
//	由子类构造方法赋值
	protected boolean isIce;
	
//	子弹移动速度：每10ms
	protected static final int MOVE_SPEED = 2;
	
//	子弹攻击力
//	由子类构造方法赋值
	protected int attackPoint;

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
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public int getATK() {
		return this.attackPoint;
	}
	public int getLine() {
		return this.line;
	}
	public boolean isIce() {
		return this.isIce;
	}
	
//	设置器方法
	public void setState(int state) {
		this.state = state;
	}
	public void setLine(int line) {
		this.line = line;
	}
	
//	判断状态方法
	public boolean isLive() {
		return this.state == LIVE;
	}
	public boolean isDead() {
		return this.state == DEAD;
	}
	
//	修改状态方法
	public void goLive() {
		this.state = LIVE;
	}
	public void goDead() {
		this.state = DEAD;
	}
	
//	移动子弹方法
	public void move() {
		if(this.position) {
			this.y += MOVE_SPEED;
		}
		else {
			this.y -= MOVE_SPEED;
		}
	}
	
//	判断子弹是否已击中目标
	public boolean isHit(Zombie z) {
		if(z == null)
			throw new NullPointerException();
		
		// 通过判断子弹是否与僵尸在同一行并且子弹是否与僵尸有接触
		return (z.getLine() == this.line &&
				this.y <= z.getY() + z.getWidth() &&
				this.y + this.width >= z.getY());
	}
	
//	判断子弹是否越界
	public boolean isOutOfBound() {
		return (this.y >= GameMain.WIDTH || this.y <= 0);
	}
	
//	图片长宽属性
	protected int width;
	protected int height;
	protected ImageIcon image;
	protected JLabel label;
	
//	绘画植物图片方法
	public JLabel drawBulletImage() {
		
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
	public void removeBulletImage() {
		if(this.label != null)
			gameMainPart.GameMain.frame.getContentPane().remove(this.label);
		this.label = null;
	}
	
}
