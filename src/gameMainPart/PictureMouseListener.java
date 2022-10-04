package gameMainPart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

// 专用于图片标签类的鼠标监视器
class ImageMouseListener extends MouseAdapter {

//	标记的id编号
	private int id;
	
//	构造方法
	public ImageMouseListener(int id) {
		this.id = id;
	}
	
//	鼠标点击
	@Override
	public void mouseClicked(MouseEvent e) {
	}

//	鼠标按下
	@Override
	public void mousePressed(MouseEvent e) {
	}

//	鼠标松开
	@Override
	public void mouseReleased(MouseEvent e) {
	}

//	鼠标进入
	@Override
	public void mouseEntered(MouseEvent e) {
	}

//	鼠标移出
	@Override
	public void mouseExited(MouseEvent e) {
	}

//	鼠标拖动
	@Override
	public void mouseDragged(MouseEvent e) {
	}

//	鼠标移动
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
//	滚动鼠标滚轮
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	

//	访问器方法
	public int getId() {
		return id;
	}
}
