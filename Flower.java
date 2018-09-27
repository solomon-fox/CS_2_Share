
//this class/object represents MY interpretation/understanding
//	of a Flower and its important behaviors/attributes
public class Flower
{
	// instance variable
	double height = 0, // how high the flower currently is
			growthAmnt = 0; // how much it grows per day
	String color = "Red";

	public Flower()	//default constructor - same name as the class
	{
		//random growth rate in the range [0.5, 5.5]
		growthAmnt = Math.random()*5+0.5;
		System.out.println("Created a new "+color+" flower.");
	}
	
	public Flower(String c)	//expects a color
	{
		color = c;
		growthAmnt = Math.random()*5+0.5;
		System.out.println("Created a new "+color+" flower.");
	}
	
	public Flower(double g)	//expects a growth amount
	{
		growthAmnt = g;
		System.out.println("Created a new "+color+" flower.");
	}
}
