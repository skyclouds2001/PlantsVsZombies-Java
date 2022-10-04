package plant;

import java.util.Iterator;

import gameMainPart.GameMain;

/*
 * ֲ����Ʒ���������
 * ��������ֲ�� ���ÿ��Ʒ���
 */
public class PlantControl {
	
//	���ֲ���ʱ��������λ�����룩
	private static final int PASS_TIME = 1000;
	//	���ֲ��
	public static void addPlant(int passTime) {
	//	����Ƿ񵽴���ӵ�ʱ���
		if(passTime % PASS_TIME != 0)
			return;
		
	//	ʹ��������������ȡ����ֲ��͵���X��Y����
		int rand = (int)(Math.random() * Plant.PLANT_NUMBER);
		int posY = (int)(Math.random() * (GameMain.HEIGTH - 100) + 50);
		int posX = (int)(Math.random() * (GameMain.WIDTH - 200) + 100);
		
	//	�����������ȡֲ��
		Plant plant = getPlant(rand, posX, posY);
		
	//	���ֲ��ͻ���ֲ��
		synchronized(GameMain.playPanel.plantList) {
			GameMain.playPanel.plantList.add(plant);
		}
		
		plant.drawPlantImage();
	}
	
//	�������ֵ����ֲ��
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
	
//	���ֲ��״̬����ȷ���Ƿ񹥻�/�Ƴ�ֲ��/�ƶ�ѡ�е�δ��ֲ��ֲ��
	public static void plantSituationCheck() {
		synchronized(GameMain.playPanel.plantList) {
			Iterator<Plant> it = GameMain.playPanel.plantList.iterator();
		
		//	ö��plantList�б������ж�ֲ��״̬
			while(it.hasNext()) {
			//	��ȡֲ����ʵ��
				Plant plant = it.next();
				
			//	��ֲ����������ȥֲ��ͼƬ�����б����Ƴ�
				if(plant.isDead()) {
					plant.removePlantImage();
					it.remove();
				}
			//	��ֲ���Դ��������ֵ����С��0ʹ֮������������0������Ƿ��ڹ���״̬
				else if(plant.isLife()) {
					if(plant.getLive() <= 0) {
						plant.goDead();
					}
					else if(plant.isAttackPlant() && plant.isAttackable())
						plant.plantAttack();
				}
//			//	��ֲ����ѡ���ƶ������У��ƶ������µ�����:cancel
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
