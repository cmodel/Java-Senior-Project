/*

 */
package grid;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Grid extends Application {
    
    private final int width = 1000;
    private final int height = 800;
    private Pane root;
    private Node character;
    
    public void start(Stage stage) {
        root = new Pane();
        root.setPrefSize(width, height);
        
        Image characterImage = new Image(getClass().getResourceAsStream("/images/character.jpg"), 50, 70, false, false);
        character = new ImageView (characterImage);
        character.setTranslateY(height/2-35);
        character.setTranslateX(width/2-25);
        root.getChildren().add(character);
        
        Scene game = new Scene (root);
        stage.setTitle("BattleQuest");
        stage.setScene(game);
        game.setOnKeyPressed (new EventHandler<KeyEvent> () {
            public void handle(KeyEvent event){
                switch(event.getCode()){
                    case UP:
                        if (character.getTranslateY()>=70){
                            character.setTranslateY(character.getTranslateY()-70);
                        }
                        break;
                    case DOWN:
                        if (character.getTranslateY()<= height-140){
                            character.setTranslateY(character.getTranslateY()+70);
                        }
                        break;
                    case LEFT:
                        if (character.getTranslateX()>= 20){
                            character.setTranslateX(character.getTranslateX()-70);
                        }
                        break;
                    case RIGHT:
                        if (character.getTranslateX() <= width-70){
                            character.setTranslateX(character.getTranslateX()+70);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
