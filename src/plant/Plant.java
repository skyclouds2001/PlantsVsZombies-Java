package plant;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bullet.Bullet;
import gameMainPart.GameMain;

// ֲ���ุ��
public abstract class Plant {
	
//	�ܵ�ֲ������:�༴ֲ����������
	public static final int PLANT_NUMBER = 2;
	
//	ͼ������x��y
	protected int x;
	protected int y;
	
//	�������꣨1~5,1~9��Ĭ�ϣ�0,0�� ��|��
//	��ֲ�����º���Ч
	protected int row;
	protected int column;
	
//	���๹�췽��
	public Plant(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.label = new JLabel();
	}
	
//	ֲ����ͼƬ��������
	protected ImageIcon image;
	protected int width;
	protected int height;
	protected JLabel label;
	
//	�滭ֲ��ͼƬ����
	public JLabel drawPlantImage() {
		
		this.label = new JLabel();
		
	//	���ñ�ǩ����ͼƬ����ǩλ�ô�С
		this.label.setIcon(image);
		this.label.setBounds(this.x, this.y,
				width, height);
		
	//	���ñ�ǩ
		GameMain.frame.getContentPane().add(this.label);

		return this.label;
	}
	
//	�Ƴ�ֲ��ͼƬ����
	public void removePlantImage() {
		if(this.label != null)
			gameMainPart.GameMain.frame.getContentPane().remove(label);
		this.label = null;
	}
	
//	����ֲ��״̬
	protected static final int FLOW = 0;		// �������
	protected static final int MOVE = 1; 		// ���������ƶ�
	protected static final int LIFE = 2;		// ��ֲ������
	protected static final int DEAD = 3;		// ��ֲ������
//	������ֲ��״̬
	protected int state = FLOW;
	
//	ֲ��ʵ��Ѫ���������๹�췽����ֵ
	protected int blood;
//	ֲ��Ѫ���������๹�췽����ֵ
	protected int attackingTime;
	
//	����������
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getState() {
		return this.state;
	}
	public int getLive() {
		return this.blood;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public boolean isAttackPlant() {
		return this.isAttackPlant;
	}
	
//	����������
	public void setxy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
// �ж�ֲ���״̬
	public boolean isFlow() {
		return this.state == FLOW;
	}
	public boolean isMove() {
		return this.state == MOVE;
	}
	public boolean isLife() {
		return this.state == LIFE;
	}
	public boolean isDead() {
		return this.state == DEAD;
	}
	
//	�ı�ֲ��״̬
	public void goFlow() {
		this.state = FLOW;
	}
	public void goMove() {
		this.state = MOVE;
	}
	public void goLife() {
		this.state = LIFE;
	}
	public void goDead() {
		this.state = DEAD;
	}
	
//	��ʬÿ10���빥��ֲ�ֲ���½���Ѫ��
	protected static final int ZOMBIE_HIT_BLOOD_POINTS = 1;
//	ֲ�ﱻ������Ѫ����
	public void loseLive() {
		this.blood -= ZOMBIE_HIT_BLOOD_POINTS;
	}
	
//	ֲ�ﱻѡ�к���ƶ�������x,yΪ��������
	public void moveTo(int x, int y) {
		this.x = x + this.height / 2;
		this.y = y + this.width / 2;
	}
	
//	��ǵ�ǰֲ���Ƿ��ǹ���ֲ��
//	�����๹�췽����ֵ	
	protected boolean isAttackPlant;
//	���ֲ�ﵱǰ�Ƿ�Ӧ������
	public boolean isAttackable() {
		
		// ����Ƿ��ִ�й���
		boolean flag = false;
		// ��¼ֲ�ﵱǰ����
		int row = this.row;
		
		synchronized(GameMain.playPanel.zombieList) {
			// �ж����޽�ʬ��ֲ��ͬ�У�����ɽ��й���
			for(zombies.Zombie z: GameMain.playPanel.zombieList) {
				if(z.getLine() == row) {
					flag = true;
					break;
				}
			}
		}
		
		return flag;
	}
	
//	��ȡ�ӵ�������������ʵ��
	protected abstract Bullet [] getBullet(int x, int y);
	
//	ֲ�﹥������
	public void plantAttack() {
		int startx = this.x;
		int starty = this.y;
		
		for(Bullet bullet: this.getBullet(startx, starty)) {
			bullet.drawBulletImage();
			GameMain.playPanel.bulletList.add(bullet); 
		}
	}
}
