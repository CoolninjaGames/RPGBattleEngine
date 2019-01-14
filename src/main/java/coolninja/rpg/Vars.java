package coolninja.rpg;

import java.net.URL;

import coolninja.rpg.Cons.Companion;
import coolninja.rpg.Console.Colors;
import coolninja.rpg.Required.Player;

public class Vars {
    
    public static Player player;
    
    public static Companion[] comps; 
    
    public static String compColorCode = Colors.GREEN_BACKGROUND;
    
    public static String enemyColorCode = Colors.RED_BACKGROUND;
    
    public static String winText = "You Won!";
    
    public static String loseText = "You Lost!";
    
    public static URL defaultBattleSoundLocation;
    
    public static URL winMusic;
    
    public static boolean shouldScroll = true;
    
    
}
