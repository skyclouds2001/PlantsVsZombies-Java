package zombies;

public class SimpleZombie extends Zombie {

	public SimpleZombie(int x, int y) {
		super(x, y);
		
		this.blood = LIVE;
		this.image = PRELOAD_IMAGE;
		this.width = WIDTH;
		this.height = HEIGHT;
	}
	
	protected static final ZombieImageIcon PRELOAD_IMAGE;

	private static final int WIDTH;
	private static final int HEIGHT;
	private static final int LIVE;

	static {
		try {
			//	加载图片
				PRELOAD_IMAGE = new ZombieImageIcon("img/plant/Peashooter.gif", 0);
			//	复制图片长宽度属性
				WIDTH = PRELOAD_IMAGE.getIconWidth();
				HEIGHT = PRELOAD_IMAGE.getIconHeight();
			//	设置植物攻击间隔及血量
				LIVE = 200;
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
