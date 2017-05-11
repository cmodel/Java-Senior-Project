/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype.abilities;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import senior.project.prototype.Holder;
import senior.project.prototype.Player;
import senior.project.prototype.Map;


/**
 *
 * @author Corey
 */
public class Fireball 
{
    int maxCoolDown;
    int coolDown;
    int manaCost;
    int range;
    ImageView fireball;
    AnimationTimer move;
    
    public Fireball ()
    {
        fireball = new ImageView (new Image("/AbilityImages/Fireball.png"));
        
        maxCoolDown = 3;
        manaCost = 50;
        range = 4;
    }
    
    public void target(Player attacker)
    {
        EventHandler mouseMoveClick = new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                System.out.println(e.getSceneX()+"\n"+e.getSceneY());

                //Checks if a toggled tile has been clicked. See comments on Map.move() for more details on that function
                if(Holder.getMap().target(e)!= null)
                {
                    int xCheck = Holder.getMap().target(e).getXCoord();
                    int yCheck = Holder.getMap().target(e).getYCoord();
                    
                    List <Player> players = Holder.getPlayers();
                    
                    for (Player player : players)
                    {
                        if (player.getX() == xCheck && player.getY() == yCheck)
                        {
                            cast (attacker, player);
                            Holder.getPane().setOnMouseClicked(null);
                        }
                    }
                }
            }
        };
        //Checks if toggle is active on the map
        if (Holder.getMap().getToggled())
        {
            //Clears toggle
            Holder.getMap().resetToggleTile();
        }
        else
        {
            //Creates grid of tiles where the player can move to
            Holder.getMap().tileToggle(attacker,range,"target");

            //Listens for a click on the map
            Holder.getPane().setOnMouseClicked(mouseMoveClick);
        }
    }
    
    private void cast(Player attacker, Player defender)
    {
        //I'm not positive how we're calculating damage abilities so this'll just be a place holder
        int damage = 12;
        
        fireball.setX(attacker.getX()*Holder.getScaler());
        fireball.setY(attacker.getY()*Holder.getScaler());
        
        defender.removeHealth(damage);
        move (defender.getX(),defender.getY());
        
    }
    
    private void move (int x, int y)
    {
        move = new AnimationTimer(){
            
            int steps = 0;
            double xMoveTotal = x*Holder.getScaler();
            double yMoveTotal = y*Holder.getScaler();
        
            double xMoveStep = xMoveTotal/60;
            double yMoveStep = yMoveTotal/60;
            
            public void handle(long now)
            {
                if (steps == 60)
                {
                    explode();
                    
                }
                fireball.setTranslateX(fireball.getTranslateX()+xMoveStep);
                fireball.setTranslateY(fireball.getTranslateY()+yMoveStep);
                steps++;
            }
        };
        
    }
    
    private void explode ()
    {
        move = new AnimationTimer(){
            int steps = 0;
            public void handle(long now)
            {
                if (steps < 12)
                {
                    fireball.setImage(new Image("/AbilityImages/Fireball/Fireball Blowup 0.png"));
                }
                else if (steps < 24)
                {
                    fireball.setImage(new Image("/AbilityImages/Fireball/Fireball Blowup 1.png"));
                }
                else if (steps < 36)
                {
                    fireball.setImage(new Image("/AbilityImages/Fireball/Fireball Blowup 3.png"));
                }
                else if (steps < 48)
                {
                    fireball.setImage(new Image("/AbilityImages/Fireball/Fireball Blowup 4.png"));
                }
                else if (steps < 60)
                {
                    fireball.setImage(new Image("/AbilityImages/Fireball/Fireball Blowup 5.png"));
                }
                else
                {
                    move.stop();
                    Holder.getPane().getChildren().remove(fireball);
                }
            }
        };
    }
}
