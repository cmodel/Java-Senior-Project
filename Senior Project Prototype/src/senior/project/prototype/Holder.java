/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 *
 * @author Corey
 */
public class Holder 
{
    //initializes pane
    static Pane root = new Pane();
        
    //Generates Scene
    static Scene scene = new Scene(root, 500, 500);
    
    //Initializes map
    static Map testMap = new Map(Holder.getPane(),10,10);
    
    static int scaler = 50;     
    
    static List<Player> players = new ArrayList<>();
    
    public static List getPlayers()
    {
        return players;
    }
    
    public static void addPlayer(Player player)
    {
        players.add(player);
    }
    
    public static Map getMap()
    {
        return testMap;
    }
    
    public static Pane getPane()
    {
        return root;
    }
    
    public static Scene getScene()
    {
        return scene;
    }
    
    public static int getScaler ()
    {
        return scaler;
    }
}
