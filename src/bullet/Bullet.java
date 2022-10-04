package bullet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gameMainPart.GameMain;
import zombies.Zombie;

// �ӵ�������
public abstract class Bullet {
	
//	�ӵ�ͼƬ����
	protected int x;
	protected int y;
	
//	�ӵ������еı��
	protected int line;
	
//	���췽��
	public Bullet(int x, int y) {
		this();
		
		this.x = x;
		this.y = y;
	}
	public Bullet() {
		this.label = new JLabel();
	}
	
//	�ӵ�״̬
	protected static final int LIVE = 0;
	protected static final int DEAD = 1;
	protected int state = LIVE;
	
//	�ӵ��ƶ�����true��ʾ����false��ʾ����
//	�����๹�췽����ֵ
	protected boolean position;
	
//	����ӵ��Ƿ��Ǳ�����
//	�����๹�췽����ֵ
	protected boolean isIce;
	
//	�ӵ��ƶ��ٶȣ�ÿ10ms
	protected static final int MOVE_SPEED = 2;
	
//	�ӵ�������
//	�����๹�췽����ֵ
	protected int attackPoint;

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
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public int getATK() {
		return this.attackPoint;
	}
	public int getLine() {
		return this.line;
	}
	public boolean isIce() {
		return this.isIce;
	}
	
//	����������
	public void setState(int state) {
		this.state = state;
	}
	public void setLine(int line) {
		this.line = line;
	}
	
//	�ж�״̬����
	public boolean isLive() {
		return this.state == LIVE;
	}
	public boolean isDead() {
		return this.state == DEAD;
	}
	
//	�޸�״̬����
	public void goLive() {
		this.state = LIVE;
	}
	public void goDead() {
		this.state = DEAD;
	}
	
//	�ƶ��ӵ�����
	public void move() {
		if(this.position) {
			this.y += MOVE_SPEED;
		}
		else {
			this.y -= MOVE_SPEED;
		}
	}
	
//	�ж��ӵ��Ƿ��ѻ���Ŀ��
	public boolean isHit(Zombie z) {
		if(z == null)
			throw new NullPointerException();
		
		// ͨ���ж��ӵ��Ƿ��뽩ʬ��ͬһ�в����ӵ��Ƿ��뽩ʬ�нӴ�
		return (z.getLine() == this.line &&
				this.y <= z.getY() + z.getWidth() &&
				this.y + this.width >= z.getY());
	}
	
//	�ж��ӵ��Ƿ�Խ��
	public boolean isOutOfBound() {
		return (this.y >= GameMain.WIDTH || this.y <= 0);
	}
	
//	ͼƬ��������
	protected int width;
	protected int height;
	protected ImageIcon image;
	protected JLabel label;
	
//	�滭ֲ��ͼƬ����
	public JLabel drawBulletImage() {
		
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
	public void removeBulletImage() {
		if(this.label != null)
			gameMainPart.GameMain.frame.getContentPane().remove(this.label);
		this.label = null;
	}
	
}
