package bullet;

import javax.swing.ImageIcon;

// �㶹�ӵ��ࣺ�̳��ӵ�������
public class PeaShooterBullet extends Bullet {
	
//	ͼƬԴ�������ඨ�壬ͨ�����๹�췽����ֵ
	private static final ImageIcon PRELOAD_IMAGE;
//	�ӵ�������
	private static final int PEA_SHOOTER_BULLET_ATK = 20;
//	ֲ��ͼƬ�������ԣ�ͨ����̬�������
	private static final int WIDTH;
	private static final int HEIGHT;
	
//	���췽��
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
	
//	�þ�̬�������ڼ���ͼƬ
	static {
		try {
		//	����ͼƬ
			PRELOAD_IMAGE = new BulletImageIcon("img/bullet/peaShooterBullet.gif", 0);
		//	����ͼƬ�����
			WIDTH = PRELOAD_IMAGE.getIconWidth();
			HEIGHT = PRELOAD_IMAGE.getIconHeight();
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
