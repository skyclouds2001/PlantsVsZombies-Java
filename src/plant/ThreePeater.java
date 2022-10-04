package plant;

import javax.swing.ImageIcon;

import bullet.Bullet;
import bullet.PeaShooterBullet;

public class ThreePeater  extends Plant implements Shoot {

//	ͼƬԴ�������ඨ�壬ͨ�����๹�췽����ֵ
	private static final ImageIcon PRELOAD_IMAGE;
//	ֲ�﹥��ʱ����(��λ������)�������ඨ�壬ͨ�����๹�췽����ֵ
	private static final int ATTACK_SPEED;
//	ֲ��Ѫ���������ඨ�壬ͨ�����๹�췽����ֵ
	private static final int LIVE;
//	ֲ��ͼƬ��������
	private static final int WIDTH;
	private static final int HEIGHT;
	
//	�þ�̬�����������ó�ʼ�๲������
	static {
		try {
			//	����ͼƬ
				PRELOAD_IMAGE = new PlantImageIcon("img/plant/Threepeater.gif", 1);
			//	����ͼƬ�����
				WIDTH = PRELOAD_IMAGE.getIconWidth();
				HEIGHT = PRELOAD_IMAGE.getIconHeight();
			//	����ֲ�﹥�������Ѫ��
				LIVE = 300;
				ATTACK_SPEED = 1400;
				
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
//	���췽��
	public ThreePeater(int x, int y) {
		super(x, y);
		
		this.isAttackPlant = true;
		this.blood = LIVE;
		this.attackingTime = ATTACK_SPEED;
		this.image = PRELOAD_IMAGE;
		this.width = WIDTH;
		this.height = HEIGHT;
	}

//	��ȡ�ӵ�ʵ������	
	@Override
	public Bullet[] getBullet(int x, int y) {
		Bullet [] b = new Bullet[3];
		
		b[0] = new PeaShooterBullet(x, y);		// ***��Ҫ�޸�����***
		b[1] = new PeaShooterBullet(x, y);
		b[2] = new PeaShooterBullet(x, y);
		
		return b;
	}

	
	
}
