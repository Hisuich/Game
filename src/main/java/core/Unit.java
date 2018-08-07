package core;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Unit {

	public static List<Unit> playersList = new ArrayList<>();
	
	public int tiredness = 100;
	public boolean isReady = false;
	public boolean isDead = false;
	public String name;
	
	public int attack;
	public int defence;
	public int dodge;
	public int recovery;
	
	public Unit(String name) {
		this.name = name;
		playersList.add(this);
	}
	
	public void turn() {
		this.attack = 0;
		this.defence = 0;
		this.dodge = 0;
		this.recovery = 0;
		int tiredness = this.tiredness;
		Scanner sc = new Scanner(System.in);
		while (tiredness != 0) {
			System.out.println(this.name + " have " + tiredness + "%");
			String[] str = sc.nextLine().split(" ");
			if (str.length <= 1)
				System.out.println("Please, input the command");
			try {
				Integer.parseInt(str[1]);
			} catch (NumberFormatException e) {
				System.out.println("Not corrent input");
			}
			int percent = Integer.parseInt(str[1]);
			if (percent > tiredness) {
				percent = tiredness;
			}
			
			switch(str[0]) {
			
			case "attack":
				attack += percent;
				tiredness -= percent;
				break;
			
			case "defence":
				defence += percent;
				tiredness -= percent;
				break;
			
			case "dodge":
				dodge += percent;
				tiredness -= percent;
				break;
			
			case "recovery":
				recovery += percent;
				tiredness -= percent;
				break;
			
			default:
				System.out.println("Not correct command");	
			}
		}
		attack /= Constants.randNumber.getRandomNumber();
		defence /= Constants.randNumber.getRandomNumber();
		dodge /= Constants.randNumber.getRandomNumber();

		System.out.println("You send: ");
		System.out.println("attack - "   + attack   + "%");
		System.out.println("defence - "  + defence  + "%");
		System.out.println("dodge - " 	 + dodge    + "%");
		System.out.println("recovery - " + recovery + "%");
		isReady = true;
	}

	public static void send() {
		for (Unit unit : playersList)
			if (unit.isReady == false)
				return;
		Unit first = playersList.get(0);
		Unit second = playersList.get(1);
		
		if (!second.isDodge()) {
			if (second.defence < first.attack) {
				second.tiredness -= first.attack - second.defence;
				System.out.println("The first attacking " + (first.attack - second.defence));
				}
				else 
					System.out.println("The second defencing");}
		else {
				first.tiredness -= second.attack / 2;
				System.out.println("The second dodging");
		}
		
		if (!first.isDodge()) {
			if (first.defence < second.attack) {
			first.tiredness -= second.attack - first.defence;
			System.out.println("The second attacking " + (second.attack - first.defence));
			}
			else 
				System.out.println("The first defencing");
		}
		else {
			second.tiredness -= first.attack / 2;
			System.out.println("The first dodging");
		}
		if (first.tiredness <= 10) 
			first.isDead = true;

		if (second.tiredness <= 10) 
			second.isDead = true;
		
		first.tiredness += first.recovery * 0.1 + new Random().nextInt(10);
		second.tiredness += second.recovery * 0.1 + new Random().nextInt(10);
	}
	
	public boolean isDodge() {
		int rand = new Random().nextInt(100);
		if (rand <= dodge) 
			return true;
		return false;
	}
}	
