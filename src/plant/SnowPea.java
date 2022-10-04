package plant;

import javax.swing.ImageIcon;

import bullet.Bullet;
import bullet.SnowPeaBullet;

public class SnowPea extends Plant implements Shoot {

//	图片源：需子类定义，通过子类构造方法赋值
	private static final ImageIcon PRELOAD_IMAGE;
//	植物攻击时间间隔(单位：毫秒)：需子类定义，通过子类构造方法赋值
	private static final int ATTACK_SPEED;
//	植物血量：需子类定义，通过子类构造方法赋值
	private static final int LIVE;
//	植物图片基本属性
	private static final int WIDTH;
	private static final int HEIGHT;
	
//	该静态语句块用于设置初始类共用属性
	static {
		try {
			//	加载图片
				PRELOAD_IMAGE = new PlantImageIcon("img/plant/SnowPea.gif", 1);
			//	设置图片长宽度
				WIDTH = PRELOAD_IMAGE.getIconWidth();
				HEIGHT = PRELOAD_IMAGE.getIconHeight();
			//	设置植物攻击间隔及血量
				LIVE = 300;
				ATTACK_SPEED = 1400;
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
//	构造方法
	public SnowPea(int x, int y) {
		super(x, y);
		
		this.isAttackPlant = true;
		this.blood = LIVE;
		this.attackingTime = ATTACK_SPEED;
		this.image = PRELOAD_IMAGE;
		this.width = WIDTH;
		this.height = HEIGHT;
	}

//	获取子弹实例对象	
	@Override
	public Bullet[] getBullet(int x, int y) {
		Bullet [] b = new Bullet[1];
		b[0] = new SnowPeaBullet(x, y);
		
		return b;
	}
}
