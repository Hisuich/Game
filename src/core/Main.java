package core;

public class Main {

	public static void main(String[] args) {
		Unit first = new Unit("first");
		Unit second = new Unit("second");
		game: while(true) {
			first.turn();
			second.turn();
			Unit.send();
			for (Unit unit : Unit.playersList) {
				if (unit.isDead) {
					System.out.println(unit.name + " is dead");
					break game;
				}
			}
		}
	}

}
