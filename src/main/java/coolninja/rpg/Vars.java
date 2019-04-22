package coolninja.rpg;

import java.net.URI;

import coolninja.rpg.Battle.LoseBattle;
import coolninja.rpg.Cons.Companion;
import coolninja.rpg.Console.Colors;
import coolninja.rpg.Required.Player;

/**
 * Class for storing values used in the engine
 * @version 1.1
 * @since 1.0
 * @author Ben Ballard
 */
public class Vars {
    
    public static Player player;
    
    public static Companion[] comps; 
    
    public static String compColorCode = Colors.GREEN_BACKGROUND;
    
    public static String enemyColorCode = Colors.CYAN_BACKGROUND;
    
    public static String winText = "You Won!";
    
    public static URI defaultBattleSoundLocation;
    
    public static URI winMusic;
    
    public static URI loseMusic;
    
    public static boolean shouldScroll = true;
    
    public static LoseBattle loseBattle;
    
    public static boolean shouldUseLuckForExtraDamage = false;
    
    public static boolean mute = false;
    
    
}
