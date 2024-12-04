import java.util.Scanner;
interface Fighter {
    void attack();
    void defend();
}
interface Damageable {
    void takeDamage(int damage);
}
interface Healable {
    void heal(int amount);
}
class Player implements Fighter, Damageable, Healable {
    private String name;
    private int health;
    private int attackPower;
    
    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.attackPower = 25;
    }
    @Override
    public void attack() {
        System.out.println(name + " attacks with power " + attackPower);
    }
    @Override
    public void defend() {
        System.out.println(name + " defends against the attack.");
    }
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health: " + health);
    }
    @Override
    public void heal(int amount) {
        this.health += amount;
        System.out.println(name + " heals for " + amount + " health. Health: " + health);
    }
    public int getHealth() {
        return health;
    }
    public String getName() {
        return name;
    }
}
class Zombie implements Fighter, Damageable {
    private int health;
    private int attackPower;
    
    public Zombie() {
        this.health = 50;
        this.attackPower = 15;
    }
    @Override
    public void attack() {
        System.out.println("Zombie attacks with power " + attackPower);
    }
    @Override
    public void defend() {
        System.out.println("Zombie defends weakly.");
    }
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("Zombie takes " + damage + " damage. Health: " + health);
    }
    public int getHealth() {
        return health;
    }
    public int getAttackPower() {
        return attackPower; 
    }
}
class Dragon implements Fighter, Damageable {
    private int health;
    private int attackPower;
    
    public Dragon() {
        this.health = 150;
        this.attackPower = 40;
    }
    @Override
    public void attack() {
        System.out.println("Dragon attacks with fire breath! Power: " + attackPower);
    }
    @Override
    public void defend() {
        System.out.println("Dragon defends with its scales.");
    }
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("Dragon takes " + damage + " damage. Health: " + health);
    }
    public int getHealth() {
        return health;
    }
    public int getAttackPower() {
        return attackPower;
    }
}
public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        Zombie zombie = new Zombie();
        Dragon dragon = new Dragon();

        while (player.getHealth() > 0) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Attack Zombie");
            System.out.println("2. Attack Dragon");
            System.out.println("3. Heal");
            System.out.println("4. Quit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    player.attack();
                    zombie.takeDamage(player.getHealth() / 2); 
                    if (zombie.getHealth() <= 0) {
                        System.out.println("Zombie has been defeated!");
                    } else {
                        zombie.attack();
                        player.takeDamage(zombie.getAttackPower()); 
                    }
                    break;
                case 2:
                    player.attack();
                    dragon.takeDamage(player.getHealth() / 2);
                    if (dragon.getHealth() <= 0) {
                        System.out.println("Dragon has been defeated!");
                    } else {
                        dragon.attack();
                        player.takeDamage(dragon.getAttackPower());
                    }
                    break;
                case 3:
                    player.heal(20);
                    break;
                case 4:
                    System.out.println("You chose to quit. Game over.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
            if (player.getHealth() <= 0) {
                System.out.println("Game over! " + player.getName() + " has died.");
                break;
            }
        }
    }
}