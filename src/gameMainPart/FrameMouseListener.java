package gameMainPart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

// ������궯��������
class FrameMouseListener extends MouseAdapter {

//	�����
	@Override
	public void mouseClicked(MouseEvent e) {
	}

//	��갴��
	@Override
	public void mousePressed(MouseEvent e) {
	}

//	����ɿ�
	@Override
	public void mouseReleased(MouseEvent e) {
	}

//	������
	@Override
	public void mouseEntered(MouseEvent e) {
//		if(GameMain.state.compareTo(GameMain.STATE.PAUSE) == 0)
//			GameMain.state = GameMain.STATE.RUNNING;
	}

//	����Ƴ�
	@Override
	public void mouseExited(MouseEvent e) {
//		if(GameMain.state.compareTo(GameMain.STATE.RUNNING) == 0)
//			GameMain.state = GameMain.STATE.PAUSE;
	}

//	����϶�
	@Override
	public void mouseDragged(MouseEvent e) {
	}

//	����ƶ�
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
//	����������
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
}
