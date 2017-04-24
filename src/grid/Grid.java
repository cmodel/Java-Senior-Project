/*

 */
package grid;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
        
        Image grass = new Image(getClass().getResourceAsStream("/images/grass.png"), 50, 70, false, false);
        
        Image tallGrass = new Image(getClass().getResourceAsStream("/images/tallgrass.png"), 50, 70, false, false);
        
        Image path = new Image(getClass().getResourceAsStream("/images/path.png"), 50, 70, false, false);
        
        /*
        Rectangle2D viewport1 = new Rectangle2D(275, 225 , 450 , 350);
        int width = 10;
        int length = 5;
        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){
                ImageView tileset = new ImageView(path);
                tileset.setViewport(viewport1);

                GridPane.setConstraints(tileset,x,y);
                root.getChildren().add(tileset);
            }
        }
        */
        
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
