package gameMainPart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

// ר����ͼƬ��ǩ�����������
class ImageMouseListener extends MouseAdapter {

//	��ǵ�id���
	private int id;
	
//	���췽��
	public ImageMouseListener(int id) {
		this.id = id;
	}
	
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
	}

//	����Ƴ�
	@Override
	public void mouseExited(MouseEvent e) {
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
	

//	����������
	public int getId() {
		return id;
	}
}
