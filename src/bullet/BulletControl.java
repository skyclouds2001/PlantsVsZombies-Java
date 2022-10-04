package bullet;

import java.util.Iterator;

import gameMainPart.GameMain;
import zombies.Zombie;

/* 
 * �ӵ����Ʒ���������
 * ���������ӵ����ÿ��Ʒ���
 */
public class BulletControl {

	//	����ӵ�״̬��������ط����ķ���
	public static void bulletSituationCheck() {
		//	��ȡ������
		Iterator<Bullet> it = GameMain.playPanel.bulletList.iterator();
		
		//	����ö�ٸ��ӵ�ʵ��
		while(it.hasNext()) {
			//	��ȡ�ӵ�ʵ��
			Bullet bullet = it.next();
			
			//	����ӵ��Ƿ�������������������ȥ
			if(bullet.isDead()) {
				bullet.removeBulletImage();
				it.remove();
			}
			//	����ӵ��Ƿ���磺������ʹ֮����
			else if(bullet.isLive() && bullet.isOutOfBound()) {
				bullet.goDead();
			}
			//	����ӵ��Ƿ���н�ʬ
			else if(bullet.isLive()) {
				//	��ȡ��ʬ����
				Zombie zombie = getHitZombies(bullet);
				
				//	�򲻵�������ƶ�
				if(zombie == null) {
					bullet.move();
				}
				//	�ܴ����ȥ��ʬ��Ѫ����ʹ�ӵ�����
				else {
					zombie.loseBlood(bullet.getATK());
					bullet.goDead();
				}
			}
		}
	}
	
	/*
	 * ����ӵ��Ƿ���н�ʬ
	 * �ɴ����򷵻�����˵Ŀɴ��еĽ�ʬ���ã����򷵻�һ����ָ��	
	 */
	private static Zombie getHitZombies(Bullet bullet) {
		Iterator<Zombie> it = GameMain.playPanel.zombieList.iterator();
		Zombie ans = null;
		// ��¼�ɻ��е�����˵Ľ�ʬY����
		int minY = GameMain.WIDTH;
		
		// ��������
		while(it.hasNext()) {
			Zombie zombie = it.next();
			
			// ����ɻ��������¼�Խ�ʬ������
			if(bullet.isHit(zombie) && zombie.getX() < minY) {
				ans = zombie;
			}
		}
		// ���ضԽ�ʬ������
		return ans;
	}
}
