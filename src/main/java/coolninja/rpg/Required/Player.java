package coolninja.rpg.Required;

import java.util.ArrayList;

import coolninja.rpg.InputHandler;
import coolninja.rpg.MathFunc;
import coolninja.rpg.Vars;
import coolninja.rpg.Cons.*;
import coolninja.rpg.Console.Colors;
import coolninja.rpg.Console.Console;

/**
 * The required class to a define player
 * @since 1.0
 * @version 1.0
 * @author Ben Ballard
 */
public class Player {
    
    public String name;
    public int level, health, maxHealth, mana, maxMana, attack, defense, luck, mAttack, mDefense, specialAttack, exp, expToNextLevel;
    public ArrayList<Move> moves = new ArrayList<Move>();
    /**
     * 0 = feet
     * 1 = legs
     * 2 = arms
     * 3 = chest
     * 4 = head
     * 5 = weapon
     * 6 = mod
     */
    public Equipment[] equipment = new Equipment[7];
    public ArrayList<Item> inv = new ArrayList<Item>();
    public Weakness currentWeakness;
    
    /**
     * Used for internal handling of level ups
     */
    protected int[] stat;
    protected String[] names = new String[]{"Max Health", "Max Mana", "Attack", "Defense", "Luck", "Magic Attack", "Magic Defense", "Special Attack"};
    protected int skillPoints = 0;
    
    
    /**
     * If name == "You", then "Your turn" will be used on battle
     * @since 1.0
     */
    public Player(String name, int health, int mana, int maxMana, int attack, int defense, 
            int luck, int mAttack, int mDefense, int specialAttack){
        this.name = name;
        this.level = 1;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = maxMana;
        this.attack = attack;
        this.defense = defense;
        this.luck = luck;
        this.mAttack = mAttack;
        this.mDefense = mDefense;
        this.specialAttack = specialAttack;
        this.expToNextLevel = 10;
        this.exp = 0;
    }
    
    /**
     * Increases the exp
     * @since 1.0
     */
    public void increaseEXP(int exp){
        this.exp += exp;
    }
    
    /**
     * Used to level up the player when the required exp is obtained
     * (Can be overwritten)
     * @since 1.0
     */
    public void levelUp(){
        
        if(exp >= expToNextLevel){
            
            exp -= expToNextLevel;
            
            System.out.println("You have leveled up!");
            
            Console.waitFull(2);
            
            Console.clear();
            
            System.out.println("Level " + level);
            
            stat = null;
        
            stat = new int[]{maxHealth, maxMana, attack, defense, luck, mAttack, mDefense, specialAttack};
        
            LevelUpHelper();
            
            levelUp();
            
            heal();
        }
    }
    
    private void LevelUpHelper(){
        level += 1;
        Console.clear();
        
        System.out.println("Level " + level + "!");
        Console.waitHalf(5);
        
        for(int i = 0; i < stat.length; i++){
            String name = names[i];
            int temp = (int) Math.round((((MathFunc.random(0)/2)))+1+(luck/4));
            stat[i] = temp;
            System.out.println(name + " increased by " + temp);
            if(Vars.shouldScroll){
                for(int x = 0; x < 5; x++){
                    System.out.println("\n");
                    Console.waitReal(50);
                }
            }
            Console.waitFull(1);
        }
        
        maxHealth += stat[0];
        maxMana += stat[1];
        attack += stat[2];
        defense += stat[3];
        luck += stat[4];
        mAttack += stat[5];
        mDefense += stat[6];
        specialAttack += stat[7];
        expToNextLevel += MathFunc.random(0)+3*level;
        health = maxHealth;
        mana = maxMana;
        skillPoints = MathFunc.random(3)+2*level;
        
        while(skillPoints != 0){
            pickStat(skillPoints);
        }
        
        //addMove(level);
        
        Console.waitHalf(1);
        
        InputHandler.pressEnter();
        
        Console.clear();
    }
    
    private void pickStat(int skillPoints){
        stat = new int[]{maxHealth, maxMana, attack, defense, luck, mAttack, mDefense, specialAttack};

        for(int i = 0; i < stat.length; i++){
            System.out.println("(" + i + ")" + names[i] + ": " + stat[i]);
            Console.waitReal(300);
        }
        
        System.out.println("You have " + skillPoints + " skill points.\n Which one? (Use number)");
        
        int s;
        
        try{
            s = Integer.parseInt(InputHandler.getInput());
        }catch(Exception e){
            System.out.println("That's Not A Number!");   
            Console.clear();
            return;
        }
        
        usePoint(s);
        
        Console.clear();
        
    }
    
    private void usePoint(int index){
        System.out.println("How Much?");
        
        int s;
        try{
            s = Integer.parseInt(InputHandler.getInput());
        }catch(Exception e){
            Console.printError("That's Not A Number!", 500);
            return;
        }
   
        if(s > skillPoints){
           Console.printError("That's Too Much!", 500);
            return;
        }
        
        stat[index] += s;
        skillPoints -= s;
        
        maxHealth = stat[0];
        maxMana = stat[1];
        attack = stat[2];
        defense = stat[3];
        luck = stat[4];
        mAttack = stat[5];
        mDefense = stat[6];
        specialAttack = stat[7];
        
    }
    
    /**
     * Sets the players stats using the given equipment
     * (Can be overwritten)
     * @since 1.0
     * @param equipment
     */
    public void setStats(Equipment equipment){
        
    }
    
    /**
     * Adds a move to the player
     * @since 1.0
     */
    public Player addMove(Move move){
        moves.add(move);
        return this;
    }
    
    /**
     * Prints the players moves
     * @since 1.0
     */
    public void printMoves(){
        for(int i = 0; i < moves.size(); i++){
            Move m = moves.get(i);
            if(m.manaCost != 0){
                System.out.print(Colors.BLACK);
                System.out.println("  -" + Colors.WHITE_BACKGROUND + m.name + " | Mana: " + m.manaCost + Colors.reset());
            }else{
                System.out.print(Colors.BLACK);
                System.out.println("  -" + Colors.WHITE_BACKGROUND + m.name + Colors.reset());
            }
        }
    }
    
    /**
     * Returns the player's moves
     * @since 1.0
     */
    public ArrayList<Move> getMoves(){
        return this.moves;
    }
    
    /**
     * Prints the players inventory
     * (Can be overwritten)
     * @since 1.0
     */
    public void printInv(){
        for(int i = 0; i < inv.size(); i++){
            System.out.println(inv.get(i).name);
        }
    }
    
    /**
     * Returns the player's inventory
     * @since 1.0
     */
    public ArrayList<Item> getInv(){
        return this.inv;
    }
    
    /**
     * Heals the player to full health and mana
     * @since 1.0
     */
    public void heal(){
        this.health = maxHealth;
        this.mana = maxMana;
    }
    
    /**
     * Prints the players stats
     * @since 1.0
     */
    public void printStats(){
        stat = new int[]{attack, defense, luck, mAttack, mDefense, specialAttack};
        names = new String[]{"Attack", "Defense", "Luck", "Magic Attack", "Magic Defense", "Special Attack"};
        for(int i = 0; i < stat.length; i++){
            Console.waitReal(300);
            System.out.println(names[i] + ": " + stat[i]);
        }
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Exp: " + exp + "/" + expToNextLevel);
        Console.waitFull(2);
    }
    
    /**
     * Sets the player's current weakness
     * @since 1.0
     */
    public Player setWeakness(Weakness weakness){
        this.currentWeakness = weakness;
        return this;
    }
    
    /**
     * Deals given damage to player/compainion
     * @since 1.0
     * @param damage
     */
    public void dealDamage(int damage){
        this.health -= damage;
    }
    
    /**
     * Adds/Removes Armor
     * @since 1.0
     * @param removing
     * @param armor
     */
    public Player addArmor(boolean removing, Equipment armor){
        if(!removing){
            this.maxHealth += armor.maxHealth;
            this.maxMana += armor.maxMana;
            this.attack += armor.attack;
            this.defense += armor.defense;
            this.luck += armor.luck;
            this.mAttack += armor.mAttack;
            this.mDefense += armor.mDefense;
            if(armor.weakness != null)
                this.currentWeakness = armor.weakness;
            equipment[armor.slot.index] = armor;
        }else{
            this.maxHealth -= armor.maxHealth;
            this.maxMana -= armor.maxMana;
            this.attack -= armor.attack;
            this.defense -= armor.defense;
            this.luck -= armor.luck;
            this.mAttack -= armor.mAttack;
            this.mDefense -= armor.mDefense;
            this.currentWeakness = null;
            equipment[armor.slot.index] = null;
        }
        return this;
    }
    
    public void addItemToInv(Item item){
        inv.add(item);     
    }
}
