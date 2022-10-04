package gameMainPart;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 图片管理类
class Picture {
	
//	绘制普通图片方法
	public static JLabel drawPicture(int id) {
		if(id < 0 || id >= IMAGE_NUMBER)
			throw new IndexOutOfBoundsException();
		
	//	声明标签对象
		JLabel label = new JLabel();
		
	//	设置标签背景图片及标签位置大小
		label.setIcon(image[id]);
		label.setBounds(image[id].getX(), image[id].getY(),
				image[id].getIconWidth(), image[id].getIconHeight());
		
	//	放置标签
		GameMain.frame.getContentPane().add(label);
		
		return label;
	}
	
//	绘画背景图片方法
	public static JLabel drawBackgroundPicture() {
	//	声明固定尺寸的小图片类对象
		ImageIcon background = null;
		int id;
		
		if(GamePlay.state.compareTo(GamePlay.STATE.START) == 0) {
			// 加载背景图片  
            background = new ImageIcon(image[0].getSource());
            id = 0;
		}
		else if(GamePlay.state.compareTo(GamePlay.STATE.RUNNING) == 0) {
			// 加载背景图片  
            background = new ImageIcon(image[1].getSource());
            id = 1;
		}
		else
			return null;
		  
        // 把背景图片显示在一个标签里面  
        JLabel label = new JLabel(background);
        // 把标签的大小位置设置为图片刚好填充整个面板  
        label.setBounds(image[id].getX(), image[id].getY(), 
        		image[id].getIconWidth(), image[id].getIconHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel) GameMain.frame.getContentPane();  
        imagePanel.setOpaque(false);
        // 把背景图片添加到分层窗格的最底层作为背景  
        GameMain.frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        
        return label;
	}
	
//	存储预加载图片
	private static GameImageIcon [] image = null;
//	存储已加载图片数量
	private static final int IMAGE_NUMBER = 16;
//	预加载图片语句块
	static {
		image = new GameImageIcon[IMAGE_NUMBER];
		
		// 0:游戏开始界面背景
		image[0] = new GameImageIcon("img/gameMenu.jpg", 0, 0, 0);
		// 1:游戏进行界面背景
		image[1] = new GameImageIcon("img/gameRunning.jpg", 1, -50, 0);
		// 2:游戏失败文字
		image[2] = new GameImageIcon("img/gameOver.jpg", 2, 0, 0);
		// 3:冒险模式墓碑条
		image[3] = new GameImageIcon("img/main/SelectorScreen_Adventure_button.png", 3, 578, 88);
		// 4:退出游戏文字
		image[4] = new GameImageIcon("img/main/SelectorScreen_Quit1.png", 4, 915, 495);
		// 5:选项游戏文字
		image[5] = new GameImageIcon("img/main/SelectorScreen_Options1.png", 5, 750, 480);
		// 6:帮助游戏文字
		image[6] = new GameImageIcon("img/main/SelectorScreen_Help1.png", 6, 835, 515);
		// 7|8|9|10|11:割草机
		image[7] = new GameImageIcon("img/main/GrassCutter.png", 7, 120, 80);
		image[8] = new GameImageIcon("img/main/GrassCutter.png", 8, 120, 180);
		image[9] = new GameImageIcon("img/main/GrassCutter.png", 9, 120, 280);
		image[10] = new GameImageIcon("img/main/GrassCutter.png", 10, 115, 380);
		image[11] = new GameImageIcon("img/main/GrassCutter.png", 11, 110, 480);
		// 12:图鉴
		image[12] = new GameImageIcon("img/main/SelectorScreen_Almanac.png", 12, 500, 420);
		// 13:商店车钥匙
		image[13] = new GameImageIcon("img/main/SelectorScreen_Store.png", 13, 580, 470);
		// 14:禅境花园
		image[14] = new GameImageIcon("img/main/SelectorScreen_ZenGarden.png", 14, 310, 410);
		// 15:种植区
		image[15] = new GameImageIcon("img/main/PlantChooseArea.png", 15, 250, 0);
	}
}

//	存储加载图片类:继承ImageIcon类
class GameImageIcon extends ImageIcon {
	private static final long serialVersionUID = 1476360722059981605L;
	
//	存储该图片类的图片信息类的引用
	private ImageInfo imageInfo;
	
//	构造方法
	public GameImageIcon(String source, int id, int x, int y) {
		super(new String(source));
		this.imageInfo = new ImageInfo(source, id, x, y);
	}

//	访问器方法
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
	
	// 图片信息类
	class ImageInfo {
		private String source;		// 图片源位置（相对位置）
		private int id;				// 图片类对象编号
		private int x;				// 图片放置位置x
		private int y;				// 图片放置位置y
		
	//	图片信息类构造方法
		public ImageInfo(String src, int id, int x, int y) {
			this.source = new String(src);
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
}
