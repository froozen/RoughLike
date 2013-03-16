package de.fro_ozen.RoughLike.BaseTypes.Misc;

import de.fro_ozen.RoughLike.BaseTypes.Simple.VariablePair;

public class BaseStats {
	public VariablePair hp;
	public VariablePair mp;
	public VariablePair level;
	
	public double hpregen, mpregen;
	
	
	public BaseStats(){
		hp = new VariablePair();
		mp = new VariablePair();
	}
}