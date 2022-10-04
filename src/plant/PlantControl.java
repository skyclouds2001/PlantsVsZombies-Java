package plant;

import java.util.Iterator;

import gameMainPart.GameMain;

/*
 * 植物控制方法集成类
 * 包含各种植物 共用控制方法
 */
public class PlantControl {
	
//	添加植物的时间间隔（单位：毫秒）
	private static final int PASS_TIME = 1000;
	//	添加植物
	public static void addPlant(int passTime) {
	//	检测是否到达添加的时间点
		if(passTime % PASS_TIME != 0)
			return;
		
	//	使用随机数来随机获取掉落植物和调落X、Y坐标
		int rand = (int)(Math.random() * Plant.PLANT_NUMBER);
		int posY = (int)(Math.random() * (GameMain.HEIGTH - 100) + 50);
		int posX = (int)(Math.random() * (GameMain.WIDTH - 200) + 100);
		
	//	根据随机数获取植物
		Plant plant = getPlant(rand, posX, posY);
		
	//	添加植物和画上植物
		synchronized(GameMain.playPanel.plantList) {
			GameMain.playPanel.plantList.add(plant);
		}
		
		plant.drawPlantImage();
	}
	
//	根据随机值返回植物
	private static Plant getPlant(int rand, int x, int y) {
		Plant i = null;
		
		switch(rand) {
		case 0:
			i = new Peashooter(x, y);
			break;
		case 1:
			i = new Repeater(x, y);
			break;
		default:
			throw new RuntimeException();
		}
		
		return i;
}
	
	public static Plant searchPlant(int x, int y) {
		Plant plant = null;
		
		Iterator<Plant> it = GameMain.playPanel.plantList.iterator();
		
		synchronized(GameMain.playPanel.plantList) {
			while(it.hasNext()) {
				Plant p = it.next();
				if(p.isLife()
						&& Math.abs(p.getX() - x) <= p.width 
						&& Math.abs(p.getY() - y) <= p.height ) {
					plant = p;
					break;
				}
			}
		}
		
		return plant;
	}
	
//	检测植物状态，并确定是否攻击/移除植物/移动选中但未种植的植物
	public static void plantSituationCheck() {
		synchronized(GameMain.playPanel.plantList) {
			Iterator<Plant> it = GameMain.playPanel.plantList.iterator();
		
		//	枚举plantList列表，依次判断植物状态
			while(it.hasNext()) {
			//	获取植物类实例
				Plant plant = it.next();
				
			//	若植物已死：移去植物图片并从列表中移除
				if(plant.isDead()) {
					plant.removePlantImage();
					it.remove();
				}
			//	若植物仍存活：检测生命值，若小于0使之死亡，若大于0检测其是否在攻击状态
				else if(plant.isLife()) {
					if(plant.getLive() <= 0) {
						plant.goDead();
					}
					else if(plant.isAttackPlant() && plant.isAttackable())
						plant.plantAttack();
				}
//			//	若植物在选中移动过程中：移动至最新的坐标:cancel
//				else if(plant.isMove()) {
//					int x = (int) GameMain.playPanel.getMousePosition().getX();
//					int y = (int) GameMain.playPanel.getMousePosition().getY();
//					plant.moveTo(x, y);
//				}
				
				plant.removePlantImage();
				plant.drawPlantImage();
			}
		}
	}
}
