package de.fro_ozen.RoughLike.BaseTypes.Misc;

import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public class Leveling {
	public int Level, expEver;
	public VariablePair exp;
	public Leveling(){
		Level = 1;
		exp = new VariablePair();
		exp.max = 25;
		expEver = 0;
		exp.real = 0;
	}
	private void levelUp(){
		Level++;
	}
	public void addExp(int amount){
		expEver+=amount;
		while(amount>0){
			if((exp.max-exp.real)-amount<=0){
				levelUp();
				System.out.println("Level Up!");
				amount=amount-(exp.max-exp.real);
				exp.max = 2*exp.max;
				exp.real = 0;
			}
			else{
				exp.real+=amount;
				amount=0;
			}
		}
	}
}