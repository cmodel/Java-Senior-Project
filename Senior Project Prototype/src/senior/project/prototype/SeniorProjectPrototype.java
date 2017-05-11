/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import senior.project.prototype.abilities.Fireball;

/**
 *
 * @author Corey
 */
public class SeniorProjectPrototype extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        //initializes pane
        //Pane root = new Pane();
        
        
        
        //Initializes Player
        Player playerTest = new Player(Holder.getPane(),0,0);
        Player targetTest = new Player(Holder.getPane(),5,5);
        
        Holder.addPlayer(playerTest);
        Holder.addPlayer(targetTest);
        
        //Generates Scene
        primaryStage.setScene(Holder.getScene());
        primaryStage.show();
        
        //Button to activate move toggle
        Button moveButton = new Button();
        moveButton.setTranslateX(0);
        moveButton.setTranslateY(0);
        moveButton.setText("Move");
        Holder.getPane().getChildren().add(moveButton);
        
        Button fireButton = new Button();
        fireButton.setTranslateX(30);
        fireButton.setTranslateY(0);
        fireButton.setText("Firebal");
        Holder.getPane().getChildren().add(fireButton);
        
        Fireball testBall = new Fireball();

        fireButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                testBall.target(playerTest);
            }
        });
        
        
        //Event handler
        EventHandler mouseMoveClick = new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent e)
            {
                System.out.println(e.getSceneX()+"\n"+e.getSceneY());

                //Checks if a toggled tile has been clicked. See comments on Map.move() for more details on that function
                if(Holder.getMap().move(playerTest, e))
                {
                    //After moving clears tiles and resets player moves
                    Holder.getMap().resetToggleTile();
                    playerTest.resetMoves();
                    
                    Holder.getPane().setOnMouseClicked(null);
                }
            }
        };
        
        //Listener for move toggle button
        //Will probably be moved to a "buttons" class whenever we get that far
        moveButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e)
            {
                //Checks if toggle is active on the map
                if (Holder.getMap().getToggled())
                {
                    //Clears toggle
                    Holder.getMap().resetToggleTile();
                    //Resets move counter on player
                    playerTest.resetMoves(); 
                }
                else
                {
                    //Creates grid of tiles where the player can move to
                    Holder.getMap().tileToggle(playerTest,playerTest.getMoves()-1,"select");
                    
                    //Listens for a click on the map
                    Holder.getPane().setOnMouseClicked(mouseMoveClick);
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
