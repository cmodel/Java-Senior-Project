/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Corey
 */
public class Map 
{
    //Array that stores the tiles that make up the map
    Tile [][] tileArray;
    
    //Boolean to store whether tiles are selected or not
    private boolean toggled;
    
    public Map (Pane root, int length, int width)
    {
        //Initializes map array
        tileArray = new Tile [length][width];
        
        //Builds a map using random tiles
        for (int i = 0; i < tileArray.length; i++)
        {
            for (int k = 0; k < tileArray[0].length; k++)
            {
                int random = (int)(Math.random()*7);
                switch (random) {
                    case 0:
                        tileArray[i][k] =  new Tile("Bolder 1",root,i*(new Image ("/TileImages/Bolder 1.png").getWidth()),k*(new Image ("/TileImages/Bolder 1.png").getWidth()));
                        break;
                    case 1:
                        tileArray[i][k] =  new Tile("Grass 2",root,i*(new Image ("/TileImages/Grass 2.png").getWidth()),k*(new Image ("/TileImages/Grass 2.png").getWidth()));
                        break;
                    default:
                        tileArray[i][k] =  new Tile("Grass 1",root,i*(new Image ("/TileImages/Grass 1.png").getWidth()),k*(new Image ("/TileImages/Grass 1.png").getWidth()));
                        break;
                }
            }
        }
    }
    
    //Toggles a specific tile based on x,y GRID position
    public void toggleTile(int x, int y)
    {
        tileArray[x][y].toggle();
    }
    
    //Clears selection of any tiles
    public void resetToggleTile()
    {
        for (int i = 0; i < tileArray.length; i++)
        {
            for (int k = 0; k < tileArray[0].length; k++)
            {
                //Resets selection of each tile in the map
                tileArray[i][k].resetToggle();
            }
        }
        toggled = false;
    }
    
    //Moves a player to a tile that is in the move selection box. Returns true is player is moved, otherwise returns false
    public boolean move(Player player, MouseEvent e)
    {
        for (int i = 0; i<tileArray.length; i++)
        {
            for (int k = 0; k<tileArray[0].length; k++)
            {
                //Checks whether a specific tile is both clicked and selected
                if (tileArray[i][k].isClicked(e) && tileArray[i][k].isToggled())
                {
                    //Moves player
                    player.setX(i);
                    player.setY(k);
                    return true;
                }
            }
        }
        return false;
    }
    
    //Toggles tile selection for each tile adjacent to the player
    public void tileToggle(Player player, int range)
    {
        //Toggles tile selections to the right and left of the player
        for (int i = player.getX()-1; i <= player.getX()+1; i++)
        {
            try
            {
                if (tileArray[i][player.getY()].isPassable())
                {
                    System.out.println(i+","+player.getY());
                    tileArray[i][player.getY()].toggle();
                }
            }
            catch(java.lang.ArrayIndexOutOfBoundsException e)
            {
                //Do nothing
            }
        }
        
        //Toggles tile selections at the top and bottom of the player
        for (int k = player.getY()-1; k<= player.getY()+1; k++)
        {
            try
            {
                if (tileArray[player.getX()][k].isPassable())
                {
                    System.out.println(player.getX()+","+k);
                    tileArray[player.getX()][k].toggle();
                }
            }
            catch(java.lang.ArrayIndexOutOfBoundsException e)
            {
                //Do nothing
            }
        }
        
        //Now calls continueToggle to toggle the tiles to the left, right, top, and bottom of the tiles that were toggled on the right and left
        for (int i = player.getX()-1; i <= player.getX()+1; i++)
        {
            try{
                if (tileArray [i][player.getY()].isPassable())
                {
                    continueToggle(i,player.getY(),range);
                }
            }
            catch (java.lang.ArrayIndexOutOfBoundsException e){}
        }
        
        //Calls continueToggle to toggle the tiles to the left, right, top, and bottom of the tiles that were toggled on the top and bottom
        for (int k = player.getY()-1; k<= player.getY()+1; k++)
        {
            try{
                if (tileArray [player.getX()][k].isPassable())
                {
                    continueToggle(player.getX(),k,range);
                }
            }
            catch (java.lang.ArrayIndexOutOfBoundsException e){}
        }
        
        //Sets toggled to true to show that tiles are selected on the map
        toggled = true;
    }
    
    private void continueToggle(int x, int y, int steps)
    {
        System.out.println("Toggle centered at "+x+", "+y);
        
        //Checks if steps are left
        //Otherwise the interior explination is the same as the function above
        if (steps > 0)
        {
            for (int i = x-1; i <= x+1; i++)
            {
                try
                {
                    if (tileArray[i][y].isPassable() && !tileArray[i][y].isToggled())
                    {
                        tileArray[i][y].toggle();
                        System.out.println(i + "," + y);
                    }
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e)
                {
                    //Do nothing
                }
            }
            for (int k = y-1; k<= y+1; k++)
            {
                try
                {
                    if (tileArray[x][k].isPassable() && !tileArray[x][k].isToggled())
                    {
                        System.out.println(x + "," + k);
                        tileArray[x][k].toggle();
                    }
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e)
                {
                    //Do nothing
                }
            }

            for (int i = x-1; i <= x+1; i++)
            {
                try{
                    if (tileArray [i][y].isPassable())
                    {
                        continueToggle(i,y,steps-1);
                    }
                }
                catch (java.lang.ArrayIndexOutOfBoundsException e){}
            }
            for (int k = y-1; k<= y+1; k++)
            {
                try{
                    if (tileArray [x][k].isPassable())
                    {
                        continueToggle(x,k,steps-1);
                    }
                }
                catch (java.lang.ArrayIndexOutOfBoundsException e){}
            }
        }
    }
    
    //Returns whether tiles on the map are selected
    public boolean getToggled ()
    {
        return toggled;
    }
}
