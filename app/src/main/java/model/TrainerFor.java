package model;

public enum TrainerFor {
	
	
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
