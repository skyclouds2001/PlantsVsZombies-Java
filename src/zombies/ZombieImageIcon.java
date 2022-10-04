package zombies;

import javax.swing.ImageIcon;

public class ZombieImageIcon extends ImageIcon {

	private static final long serialVersionUID = -3321695832324540341L;
	private String source;		// 图片源位置（相对位置）
	private int id;				// 图片类对象编号
	
//	构造方法
	public ZombieImageIcon(String source, int id) {
		super(new String(source));
		this.source = source;
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
