/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senior.project.prototype;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Corey
 */
public class SeniorProjectPrototype extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        //initializes pane
        Pane root = new Pane();
        
        //Initializes map
        Map testMap = new Map(root,10,10);
        
        //Initializes Player
        Player playerTest = new Player(root,0,0);
        
        //Generates Scene
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Button to activate move toggle
        Button move = new Button();
        move.setTranslateX(0);
        move.setTranslateY(0);
        move.setText("Move");
        root.getChildren().add(move);
        
        //Listener for move toggle button
        //Will probably be moved to a "buttons" class whenever we get that far
        move.setOnMouseClicked(new EventHandler<MouseEvent>(){
            //A dummy boolean that I used to play around with closing the mouse click listener when the move button is not pressed
            //May be abolished at some point
            boolean moving = true;
            public void handle(MouseEvent e)
            {
                //Checks if toggle is active on the map
                if (testMap.getToggled())
                {
                    //Clears toggle
                    testMap.resetToggleTile();
                    //Resets move counter on player
                    playerTest.resetMoves();
                    moving = false;
                }
                else
                {
                    moving = true;
                    
                    //Creates grid of tiles where the player can move to
                    testMap.tileToggle(playerTest,playerTest.getMoves()-1);
                    
                    //Listens for a click on the map
                    scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
                        public void handle(MouseEvent e)
                        {
                            System.out.println(e.getSceneX()+"\n"+e.getSceneY());
                            
                            //Checks if a toggled tile has been clicked. See comments on Map.move() for more details on that function
                            if(moving = true && testMap.move(playerTest, e))
                            {
                                //After moving clears tiles and resets player moves
                                testMap.resetToggleTile();
                                playerTest.resetMoves();
                            }
                        }
                    });
                    moving = false;
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
