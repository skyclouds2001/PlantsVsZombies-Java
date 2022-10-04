package bullet;

import javax.swing.ImageIcon;

class BulletImageIcon extends ImageIcon {
	
	private static final long serialVersionUID = 8275293246674910698L;
	
	private String source;		// ͼƬԴλ�ã����λ�ã�
	private int id;				// ͼƬ�������
	
//	���췽��
	public BulletImageIcon(String source, int id) {
		super(new String(source));
		this.source = new String(source);
		this.id = id;
	}

//	����������
	public int getId() {
		return this.id;
	}
	public String getSource() {
		return new String(this.source);
	}
}
