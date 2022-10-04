package zombies;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gameMainPart.GameMain;
import plant.Plant;

public abstract class Zombie {
	
	public static final int ZOMBIE_NUMBER = 0;
	
//	ͼ������x��y
	protected int x;
	protected int y;
	
	public Zombie(int x, int y) {
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
	public JLabel drawZombieImage() {
		
		this.label = new JLabel();
		
	//	���ñ�ǩ����ͼƬ����ǩλ�ô�С
		this.label.setIcon(image);
		this.label.setBounds(this.x, this.y,
				width, height);
		
	//	���ñ�ǩ
		GameMain.frame.getContentPane().add(this.label);

		return this.label;
	}
	
//	������
	protected int line;
	
//	����������
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getLine() {
		return this.line;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public int getState() {
		return this.state;
	}
	public int getBlood() {
		return this.blood;
	}
	
	//	����������
	public void setState(int state) {
		this.state = state;
	}
	public void setLine(int line) {
		this.line = line;
	}
	
	protected static final int MOVE = 0;
	protected static final int EAT = 1;
	protected static final int DEAD = 2;
	private int state = MOVE;
	
	public boolean isMove() {
		return this.state == MOVE;
	}
	public boolean isDead() {
		return this.state == DEAD;
	}
	public boolean isEat() {
		return this.state == EAT;
	}
	
	public void goMove() {
		this.state = MOVE;
	}
	public void goDead() {
		this.state = DEAD;
	}
	public void goEating() {
		this.state = EAT;
	}
	
//	ֲ��ʵ��Ѫ���������๹�췽����ֵ
	protected int blood;
//	ֲ��Ѫ���������๹�췽����ֵ
	protected int ATTACKING_SPEED;
	
	public void loseBlood(int bloodPoint) {
		this.blood -= bloodPoint;
	}
	
	private static final int ATTACK_SPEED = 10;
	
	public void move() {
		this.x -= ATTACK_SPEED;
	}
	
	private Plant attack;
	public Plant eatable() {
		Plant p = null;
		
		return p;
	}
	public Plant getAttack() {
		return this.attack;
	}
	public void setAttack(Plant attack) {
		this.attack = attack;
	}
}
