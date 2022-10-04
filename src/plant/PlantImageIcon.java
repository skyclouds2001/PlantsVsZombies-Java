package plant;

import javax.swing.ImageIcon;

class PlantImageIcon extends ImageIcon {
	
	private static final long serialVersionUID = -3321695832324540341L;
	private String source;		// ͼƬԴλ�ã����λ�ã�
	private int id;				// ͼƬ�������
	
//	���췽��
	public PlantImageIcon(String source, int id) {
		super(new String(source));
		this.source = source;
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
