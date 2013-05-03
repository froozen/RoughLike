package de.fro_ozen.RoughLike.GameMechanics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


import de.fro_ozen.RoughLike.BaseTypes.Entitys.BaseEntity;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.BattleEntity;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.CharacterEntity;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.DropItem;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Enemy;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.Player;
import de.fro_ozen.RoughLike.BaseTypes.Entitys.ProjectileEntity;
import de.fro_ozen.RoughLike.GameMechanics.Menus.ItemShortCutBar;

public class GameLoop {
	Random ran = new Random();
	private boolean freeze;
	public static boolean end;
	public static Player player;
	public HUD hud;
	public MenuHandler menhan;
	private long thisTime, lastTime;
	public static ArrayList<BaseEntity> newEntitys;
	public ArrayList<BaseEntity> EntityContainer ;
	public void initialize(){
		player = new Player(300, 300);
		menhan = new MenuHandler(player);
		hud = new HUD(player);
		thisTime = System.currentTimeMillis();
		lastTime = System.currentTimeMillis();
		EntityContainer = new ArrayList<BaseEntity>();
		EntityContainer.add(player);
		newEntitys = new ArrayList<BaseEntity>();
	}
	private void checkPickups(){
		for(BaseEntity e:EntityContainer){
			if(e instanceof DropItem){
				if(e.isColiding(player)){
					e.remove = true;
					DropItem di=(DropItem) e;
					player.pickUp(di.item);
				}
			}
		}
	}
	private void checkNewEntitys(){
		if(newEntitys.size()>0){
			EntityContainer.addAll(newEntitys);
			newEntitys = new ArrayList<BaseEntity>();
		}
	}
	private void checkAttacks(){
		for(BaseEntity e1:EntityContainer){
			if(e1 instanceof ProjectileEntity){
				ProjectileEntity eb = (ProjectileEntity)e1;
				for(BaseEntity e2:EntityContainer){
					if(e2 != eb.source){
						if(e2 instanceof BattleEntity){
							if(eb.isColiding(e2)){
								BattleEntity ec = (BattleEntity)e2;
								ec.inflictDamage(eb.deliverDamage(), eb.direction);
								eb.remove = true;
							}

						}
					}
				}
			}
		}

		for(BaseEntity e1:EntityContainer){
			if(e1 instanceof BattleEntity){
				BattleEntity eb = (BattleEntity)e1;
				for(BaseEntity e2:EntityContainer){
					if(e1!=e2){
						if(e2 instanceof BattleEntity){
							if(eb.attacking){
								if(eb.isHitting(e2)){
									BattleEntity ec = (BattleEntity)e2;
									ec.inflictDamage(eb.computeDamage(), eb.atkDirection);
								}
//								eb.attacking = false;
							}
						}
					}
				}
			}
		}
		
		for(BaseEntity e1:EntityContainer){
			if(e1 instanceof BattleEntity){
				BattleEntity eb = (BattleEntity)e1;
				if(eb.attacking)eb.attacking = false;
			}
		}
	}
	private void removeEntitys(){
		ArrayList<BaseEntity>ids = new ArrayList<BaseEntity>();
		for(BaseEntity e:EntityContainer){
			if(e instanceof BattleEntity){
				BattleEntity be = (BattleEntity)e;
				if(be.stats.hp.real<=0){
					be.kill();
					if(be.dropItem != null){
						DropItem x = new DropItem(be.feetBox.x, be.feetBox.y, be.dropItem);
						ids.add(x);
					}
				}
			}
		}
		EntityContainer.addAll(ids);
		ids = new ArrayList<BaseEntity>();
		for(BaseEntity e:EntityContainer){
			if(e.remove)ids.add(e);
		}
		for(BaseEntity e:ids){
			EntityContainer.remove(e);
		}

		int normalEnemyCount = 4;
		int enemyCount = 0;
		for(BaseEntity e:EntityContainer){
			if(e instanceof Enemy)enemyCount++;
		}
		if(enemyCount < normalEnemyCount){
			for(int i = 0; i<(normalEnemyCount - enemyCount); i++){
				Enemy en = new Enemy(ran.nextInt(800),(ran.nextInt(600)), player);
				if(!en.isColiding(player))EntityContainer.add(en);
			}
		}
	}
	private void resortEntitys(){
		Collections.sort(EntityContainer, new Comparator<BaseEntity>() {
			public int compare(BaseEntity o1, BaseEntity o2) {
				return Double.compare(o1.feetBox.y, o2.feetBox.y);
			}
		});
	}
	private void testColission(){
		for(BaseEntity e1:EntityContainer){
			if(e1 instanceof CharacterEntity){
				for(BaseEntity e2:EntityContainer){
					if(e1!=e2){
						if(e2 instanceof CharacterEntity){
							CharacterEntity ec = (CharacterEntity)e1;
							if(ec.isFeetColiding(e2))e1.stepBack();
						}

					}
				}
			}

		}
	}
	public void computeFrame(){
		thisTime = System.currentTimeMillis();
		player.timeSinceLastFrame = thisTime - lastTime;
		player.timeSinceLastFrame /= 1000;
		lastTime = thisTime;
		checkNewEntitys();
		if(player.dead){
			menhan.gameOver = true;
		}
		freeze = menhan.checkMenus();
		if(!freeze){
			for(BaseEntity e:EntityContainer){
				e.compute();
			}
			checkPickups();
			removeEntitys();
			resortEntitys();
			testColission();
			checkAttacks();
		}
		if(menhan.gameOver && MouseInput.leftClicked)end = true;
		ItemShortCutBar.checkKeys();
		KeyInput.resetReleasedKeys();
		MouseInput.resetClicks();
	}
	
	public static Player getPlayer(){
		return player;
	}
}