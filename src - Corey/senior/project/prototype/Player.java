/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Corey
 */
public class Player 
{
    //Arbitrary player health
    int health = 50;
    
    //Player x and y coords RELATIVE TO MAP GRID (not pixles)
    int xCoord;
    int yCoord;
    
    //Max moves a player can take in a turn
    int maxMoves;
    //Moves a player has left
    int moves;
    
    //ImageView used to display player
    ImageView player;
    
    public Player (Pane root, int x, int y)
    {
        //Sets image view
        player = new ImageView(new Image ("/TileImages/Player.png"));
        
        //Sets player x and y coords RELATIVE TO MAP GRID (not pixles)
        xCoord = x;
        yCoord = y;
        
        //Sets actual pixle value of player ImageView
        player.setX(xCoord*50);
        player.setY(yCoord*50);
        
        //Adds player to root
        root.getChildren().add(player);
        
        //Sets maxMoves to an arbitrary value of 2
        maxMoves = 2;
        moves = maxMoves;
    }
    
    //Subtracts one from moves
    public void useMove()
    {
        moves--;
    }
    
    //Resets moves to to the maxMoves
    public void resetMoves()
    {
        moves = maxMoves;
    }
    
    //Returns Moves
    public int getMoves()
    {
        return moves;
    }
    
    //Returns x RELATIVE TO MAP GRID (not pixles)
    public int getX()
    {
        return xCoord;
    }
    
    //Returns y RELATIVE TO MAP GRID (not pixles)
    public int getY()
    {
        return yCoord;
    }
    
    //Sets x relative to map grid AND moves ImageView to correct pixle
    public void setX(int x)
    {
        xCoord = x;
        player.setTranslateX(50*x);
        player.toFront();
    }
    
    //Sets y relative to map grid AND moves ImageView to correct pixle
    public void setY(int y)
    {
        yCoord = y;
        player.setTranslateY(50*y);
        player.toFront();
    }
}
