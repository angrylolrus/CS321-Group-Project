package CS321.Project.Code;

public class Player{
	public Inventory inventory;
	public GameMenu gameMenu;
	
	public double health;
	public double sleep;
	
	public Player(GameMenu g) {
		gameMenu = g;
	}
}