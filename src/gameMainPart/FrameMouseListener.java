package gameMainPart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

// 窗体鼠标动作监视器
class FrameMouseListener extends MouseAdapter {

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
//		if(GameMain.state.compareTo(GameMain.STATE.PAUSE) == 0)
//			GameMain.state = GameMain.STATE.RUNNING;
	}

//	鼠标移出
	@Override
	public void mouseExited(MouseEvent e) {
//		if(GameMain.state.compareTo(GameMain.STATE.RUNNING) == 0)
//			GameMain.state = GameMain.STATE.PAUSE;
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
}
