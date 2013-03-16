package de.fro_ozen.RoughLike.BaseTypes.Misc;

public class Leveling {
	public int Level, expToNext, expEver, expMoment;
	public Leveling(){
		Level = 1;
		expToNext = 25;
		expEver = 0;
		expMoment = 0;
	}
	private void levelUp(){
		Level++;
	}
	public void addExp(int amount){
		expEver+=amount;
		while(amount>0){
			if((expToNext-expMoment)-amount<=0){
				levelUp();
				System.out.println("Level Up!");
				amount=amount-(expToNext-expMoment);
				expToNext = 2*expToNext;
				expMoment = 0;
			}
			else{
				expMoment+=amount;
				amount=0;
			}
		}
	}
}