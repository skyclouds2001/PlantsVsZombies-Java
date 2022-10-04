package bullet;

import java.util.Iterator;

import gameMainPart.GameMain;
import zombies.Zombie;

/* 
 * 子弹控制方法集成类
 * 包含各种子弹共用控制方法
 */
public class BulletControl {

	//	检测子弹状态并调用相关方法的方法
	public static void bulletSituationCheck() {
		//	获取迭代器
		Iterator<Bullet> it = GameMain.playPanel.bulletList.iterator();
		
		//	遍历枚举各子弹实例
		while(it.hasNext()) {
			//	获取子弹实例
			Bullet bullet = it.next();
			
			//	检测子弹是否已死亡：已死亡则移去
			if(bullet.isDead()) {
				bullet.removeBulletImage();
				it.remove();
			}
			//	检测子弹是否出界：出界则使之死亡
			else if(bullet.isLive() && bullet.isOutOfBound()) {
				bullet.goDead();
			}
			//	检测子弹是否打中僵尸
			else if(bullet.isLive()) {
				//	获取僵尸引用
				Zombie zombie = getHitZombies(bullet);
				
				//	打不到则继续移动
				if(zombie == null) {
					bullet.move();
				}
				//	能打到则减去僵尸的血量并使子弹死亡
				else {
					zombie.loseBlood(bullet.getATK());
					bullet.goDead();
				}
			}
		}
	}
	
	/*
	 * 检测子弹是否打中僵尸
	 * 可打中则返回最左端的可打中的僵尸引用，否则返回一个空指针	
	 */
	private static Zombie getHitZombies(Bullet bullet) {
		Iterator<Zombie> it = GameMain.playPanel.zombieList.iterator();
		Zombie ans = null;
		// 记录可击中的最左端的僵尸Y坐标
		int minY = GameMain.WIDTH;
		
		// 遍历查找
		while(it.hasNext()) {
			Zombie zombie = it.next();
			
			// 如果可击中且则记录对僵尸的引用
			if(bullet.isHit(zombie) && zombie.getX() < minY) {
				ans = zombie;
			}
		}
		// 返回对僵尸的引用
		return ans;
	}
}
