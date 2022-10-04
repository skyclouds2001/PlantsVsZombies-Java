package bullet;

import javax.swing.ImageIcon;

class BulletImageIcon extends ImageIcon {
	
	private static final long serialVersionUID = 8275293246674910698L;
	
	private String source;		// 图片源位置（相对位置）
	private int id;				// 图片类对象编号
	
//	构造方法
	public BulletImageIcon(String source, int id) {
		super(new String(source));
		this.source = new String(source);
		this.id = id;
	}

//	访问器方法
	public int getId() {
		return this.id;
	}
	public String getSource() {
		return new String(this.source);
	}
}
