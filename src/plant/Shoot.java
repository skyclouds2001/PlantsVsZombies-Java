package plant;

import bullet.Bullet;

// ������ܽӿ�
interface Shoot {
	// ����ִ�й�������
	void plantAttack();
	// ����Ƿ�ɹ���
	boolean isAttackable();
	// �����ӵ�����
	Bullet [] getBullet(int x, int y);
}
