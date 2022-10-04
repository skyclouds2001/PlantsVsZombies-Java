package plant;

import bullet.Bullet;

// 射击功能接口
interface Shoot {
	// 具体执行攻击方法
	void plantAttack();
	// 检测是否可攻击
	boolean isAttackable();
	// 返回子弹方法
	Bullet [] getBullet(int x, int y);
}
