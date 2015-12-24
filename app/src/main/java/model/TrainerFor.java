package model;

import java.io.Serializable;

public enum TrainerFor  implements Serializable {
	
	
	BEGINNERS {
		
		 @Override
		    public String toString() {
		      return "BEGINNERS";
		    }
		
		
	}, INTERMEDIATE
	{
		
		 @Override
		    public String toString() {
		      return "INTERMEDIATE";
		    }
		
		
	}, ADVANCED
	{
		
		 @Override
		    public String toString() {
		      return "ADVANCED";
		    }
		
		
	},  HIGH
	{
		
		 @Override
		    public String toString() {
		      return "HIGH";
		    }
		
		
	}

}
