/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author Corey
 */
public class Tile 
{
    //The ImageView used to display the tile
    public ImageView tile = new ImageView();
    
    //Images that store both the tile's toggled and untoggled textures respectively
    public Image tileToggled;
    public Image tileReg;
    
    //Stores the name of the tile for furture use
    String type;
    
    //Stores whether the tile can be traversed
    private boolean isPassable;
    
    //Stores whether the tile is currently "selected" or not
    private boolean toggled = false;
    
    
    public Tile(String type, Pane root, double x, double y)
    {
        //Intializes tile based on respective type
        switch (type)
        {
            case "Grass 1": tileReg = new Image("/TileImages/Grass 1.png");
            tile.setImage(tileReg);
            tileToggled = new Image("/TileImages/Grass 1 Selected.png");
            isPassable = true;
            this.type = type;
            break;
            
            case "Grass 2": tileReg = new Image("/TileImages/Grass 2.png");
            tile.setImage(tileReg);
            tileToggled = new Image("/TileImages/Grass 2 Selected.png");
            isPassable = true;
            this.type = type;
            break;
            
            case "Bolder 1": tileReg = new Image("/TileImages/Bolder 1.png");
            tile.setImage(tileReg);
            tileToggled = new Image("/TileImages/Bolder 1 Selected.png");
            isPassable = false;
            this.type = type;
            break;
            
            default: System.out.println("Your Switch in the initialization of tile screwed up");
            break;
        }
        
        //Places test coords in the bottom of the tile, mostly for bug fixing
        Text coords = new Text((int)(x/50)+", "+((int)(y/50)));
        coords.setX(x);
        coords.setY(y);
        root.getChildren().add(coords);
        
        //playces tile at x and y coords given
        tile.setX(x);
        tile.setY(y);
        
        //adds tile to pane
        root.getChildren().add(tile);
        
    }
    
    //Checks if a mouse event is within the x, y bounds of the tile
    //This is calculated by seeing if 0 < tile coord - event coord > 50 (the tile size)
    public boolean isClicked(MouseEvent e)
    {
        return e.getSceneX()-tile.getX()>0 && e.getSceneX()-tile.getX()<50 && e.getSceneY()-tile.getY()>0 && e.getSceneY()-tile.getY()<50 ;
    }
    
    //Returns if tile is traversable
    public boolean isPassable()
    {
        return isPassable;
    }
    
    //Clears selection box around tile
    public void resetToggle()
    {
        tile.setImage(tileReg);
        toggled = false;
    }
    
    //Checks whether the tile is toggled
    public boolean isToggled()
    {
        return toggled;
    }
    
    //Returns tile width
    public double getWidth()
    {
        return tile.getFitWidth();
    }
    
    //Toggles tile selection
    public void toggle()
    {
        if (toggled == false)
        {
            tile.setImage(tileToggled);
            toggled = true;
        }
        else
        {
            tile.setImage(tileReg);
            toggled = false;
        }
    }
    
    //Returns tile's x coord
    public double getX()
    {
        return tile.getX()+tile.getTranslateX();
    }
    
    //Returns tile's y coord
    public double getY()
    {
        return tile.getY()+tile.getTranslateY();
    }
}
