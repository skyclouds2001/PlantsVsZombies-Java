package bullet;

import javax.swing.ImageIcon;

// 豌豆子弹类：继承子弹抽象类
public class PeaShooterBullet extends Bullet {
	
//	图片源：需子类定义，通过子类构造方法赋值
	private static final ImageIcon PRELOAD_IMAGE;
//	子弹攻击力
	private static final int PEA_SHOOTER_BULLET_ATK = 20;
//	植物图片基本属性：通过静态语句块加载
	private static final int WIDTH;
	private static final int HEIGHT;
	
//	构造方法
	public PeaShooterBullet(int x, int y) {
		super(x, y);
		
		this.isIce = false;
		this.position = true;
		this.attackPoint = PEA_SHOOTER_BULLET_ATK;
		this.image = PRELOAD_IMAGE;
		this.width = WIDTH;
		this.height = HEIGHT;
	}
	public PeaShooterBullet(int x, int y, boolean isPositiveDirection) {
		this(x, y);
		
		this.position = false;
	}
	
//	该静态语句块用于加载图片
	static {
		try {
		//	加载图片
			PRELOAD_IMAGE = new BulletImageIcon("img/bullet/peaShooterBullet.gif", 0);
		//	设置图片长宽度
			WIDTH = PRELOAD_IMAGE.getIconWidth();
			HEIGHT = PRELOAD_IMAGE.getIconHeight();
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
