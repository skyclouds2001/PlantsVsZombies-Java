package zombies;

import java.util.Iterator;

import gameMainPart.GameMain;
import gameMainPart.GamePlay;
import plant.Plant;

public class ZombieControl {

	private static int ZOMBIE_NUMBER = 1;

	public static void ZombieSituationCheck() {
		synchronized(GameMain.playPanel.zombieList) {
			Iterator<Zombie> it = GameMain.playPanel.zombieList.iterator();
			
			while(it.hasNext()) {
				Zombie zombie = it.next();
				
				if(zombie.getState() == Zombie.DEAD) {
					it.remove();
				}
				else if(zombie.isMove()) {
					Plant p = zombie.eatable();
					if(zombie.getBlood() <= 0) {
						zombie.goDead();
					}
					else if(p != null){
						zombie.goEating();
						zombie.setAttack(p);
					}
					
					if(zombie.getX() <= 0) {
						GameMain.playPanel.setState(GamePlay.STATE.OVER);
					}
				}
				else if(zombie.isEat()) {
					if(zombie.getBlood() <= 0) {
						zombie.goDead();
					}
					else if(zombie.getAttack().isDead()){
						zombie.goMove();
					}
					else {
						zombie.getAttack().loseLive();
					}
				}
			}
		}
	}
	
	public static void addZombie(int time) {
		final int START_TIME = 10000;
		final int CHANGE_TIME = 30000;
		
		if(time <= START_TIME)
			return;
		
		int line = (int)(Math.random() * 5);
		Zombie zombie = getZombie((int)(Math.random() * ZOMBIE_NUMBER ), line);
		
		if(time <= CHANGE_TIME)
			GameMain.playPanel.zombieList.add(zombie);
	}
	
	private static Zombie getZombie(int rand, int line) {
		Zombie z = null;
		
		switch(rand) {
		case 0:
			z = new SimpleZombie(line * 100 + 50, GamePlay.WIDTH + 50);
			break;
		default:
			throw new RuntimeException("Unexpected random number.");
		}
		
		return z;
	}
}
