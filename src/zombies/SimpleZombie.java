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
			//	����ͼƬ
				PRELOAD_IMAGE = new ZombieImageIcon("img/plant/Peashooter.gif", 0);
			//	����ͼƬ���������
				WIDTH = PRELOAD_IMAGE.getIconWidth();
				HEIGHT = PRELOAD_IMAGE.getIconHeight();
			//	����ֲ�﹥�������Ѫ��
				LIVE = 200;
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
