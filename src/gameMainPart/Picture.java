package gameMainPart;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// ͼƬ������
class Picture {
	
//	������ͨͼƬ����
	public static JLabel drawPicture(int id) {
		if(id < 0 || id >= IMAGE_NUMBER)
			throw new IndexOutOfBoundsException();
		
	//	������ǩ����
		JLabel label = new JLabel();
		
	//	���ñ�ǩ����ͼƬ����ǩλ�ô�С
		label.setIcon(image[id]);
		label.setBounds(image[id].getX(), image[id].getY(),
				image[id].getIconWidth(), image[id].getIconHeight());
		
	//	���ñ�ǩ
		GameMain.frame.getContentPane().add(label);
		
		return label;
	}
	
//	�滭����ͼƬ����
	public static JLabel drawBackgroundPicture() {
	//	�����̶��ߴ��СͼƬ�����
		ImageIcon background = null;
		int id;
		
		if(GamePlay.state.compareTo(GamePlay.STATE.START) == 0) {
			// ���ر���ͼƬ  
            background = new ImageIcon(image[0].getSource());
            id = 0;
		}
		else if(GamePlay.state.compareTo(GamePlay.STATE.RUNNING) == 0) {
			// ���ر���ͼƬ  
            background = new ImageIcon(image[1].getSource());
            id = 1;
		}
		else
			return null;
		  
        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
        JLabel label = new JLabel(background);
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
        label.setBounds(image[id].getX(), image[id].getY(), 
        		image[id].getIconWidth(), image[id].getIconHeight());
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel) GameMain.frame.getContentPane();  
        imagePanel.setOpaque(false);
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        GameMain.frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        
        return label;
	}
	
//	�洢Ԥ����ͼƬ
	private static GameImageIcon [] image = null;
//	�洢�Ѽ���ͼƬ����
	private static final int IMAGE_NUMBER = 16;
//	Ԥ����ͼƬ����
	static {
		image = new GameImageIcon[IMAGE_NUMBER];
		
		// 0:��Ϸ��ʼ���汳��
		image[0] = new GameImageIcon("img/gameMenu.jpg", 0, 0, 0);
		// 1:��Ϸ���н��汳��
		image[1] = new GameImageIcon("img/gameRunning.jpg", 1, -50, 0);
		// 2:��Ϸʧ������
		image[2] = new GameImageIcon("img/gameOver.jpg", 2, 0, 0);
		// 3:ð��ģʽĹ����
		image[3] = new GameImageIcon("img/main/SelectorScreen_Adventure_button.png", 3, 578, 88);
		// 4:�˳���Ϸ����
		image[4] = new GameImageIcon("img/main/SelectorScreen_Quit1.png", 4, 915, 495);
		// 5:ѡ����Ϸ����
		image[5] = new GameImageIcon("img/main/SelectorScreen_Options1.png", 5, 750, 480);
		// 6:������Ϸ����
		image[6] = new GameImageIcon("img/main/SelectorScreen_Help1.png", 6, 835, 515);
		// 7|8|9|10|11:��ݻ�
		image[7] = new GameImageIcon("img/main/GrassCutter.png", 7, 120, 80);
		image[8] = new GameImageIcon("img/main/GrassCutter.png", 8, 120, 180);
		image[9] = new GameImageIcon("img/main/GrassCutter.png", 9, 120, 280);
		image[10] = new GameImageIcon("img/main/GrassCutter.png", 10, 115, 380);
		image[11] = new GameImageIcon("img/main/GrassCutter.png", 11, 110, 480);
		// 12:ͼ��
		image[12] = new GameImageIcon("img/main/SelectorScreen_Almanac.png", 12, 500, 420);
		// 13:�̵공Կ��
		image[13] = new GameImageIcon("img/main/SelectorScreen_Store.png", 13, 580, 470);
		// 14:������԰
		image[14] = new GameImageIcon("img/main/SelectorScreen_ZenGarden.png", 14, 310, 410);
		// 15:��ֲ��
		image[15] = new GameImageIcon("img/main/PlantChooseArea.png", 15, 250, 0);
	}
}

//	�洢����ͼƬ��:�̳�ImageIcon��
class GameImageIcon extends ImageIcon {
	private static final long serialVersionUID = 1476360722059981605L;
	
//	�洢��ͼƬ���ͼƬ��Ϣ�������
	private ImageInfo imageInfo;
	
//	���췽��
	public GameImageIcon(String source, int id, int x, int y) {
		super(new String(source));
		this.imageInfo = new ImageInfo(source, id, x, y);
	}

//	����������
	public int getX() {
		return this.imageInfo.x;
	}
	public int getY() {
		return this.imageInfo.y;
	}
	public int getId() {
		return this.imageInfo.id;
	}
	public String getSource() {
		return new String(this.imageInfo.source);
	}
	
	// ͼƬ��Ϣ��
	class ImageInfo {
		private String source;		// ͼƬԴλ�ã����λ�ã�
		private int id;				// ͼƬ�������
		private int x;				// ͼƬ����λ��x
		private int y;				// ͼƬ����λ��y
		
	//	ͼƬ��Ϣ�๹�췽��
		public ImageInfo(String src, int id, int x, int y) {
			this.source = new String(src);
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
}
