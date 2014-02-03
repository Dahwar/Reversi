package france.uha.ensisa.fl.reversi;

import france.uha.ensisa.fl.reversi.ReversiModel.State;
import java.awt.Point;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Florent
 */
public class SampleController implements Initializable {
    
    ReversiModel model = new ReversiModel();
    
    Parent rules;
    
    @FXML
    private GridPane gameGrid;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label isPlayable;
    @FXML
    private Label win;
    @FXML
    private Label copyright;
    @FXML
    private Text gameName;
    @FXML
    private CheckBox computerPlay;
    @FXML
    private AnchorPane root;
    
    private LinearGradient white = new LinearGradient(0.5,1.0,0.5,0.0,true,CycleMethod.NO_CYCLE,
                                        new Stop(0.9f,Color.web("#e5e5e5")),
                                        new Stop(0.1f,Color.web("#b0b0b0")));
    private LinearGradient black = new LinearGradient(0.5,1.0,0.5,0.0,true,CycleMethod.NO_CYCLE,
                                        new Stop(0.9f,Color.web("#0c0c0c")),
                                        new Stop(0.1f,Color.web("#3f3f3f")));
    private LinearGradient green = new LinearGradient(0.5,1.0,0.5,0.0,true,CycleMethod.NO_CYCLE,
                                        new Stop(0.9f,Color.web("#3d663d")),
                                        new Stop(0.1f,Color.web("#123e12")));
    private LinearGradient whiteIn = new LinearGradient(0.5,1.0,0.5,0.0,true,CycleMethod.NO_CYCLE,
                                        new Stop(0.1f,Color.web("#e5e5e5")),
                                        new Stop(0.9f,Color.web("#b0b0b0")));
    private LinearGradient blackIn = new LinearGradient(0.5,1.0,0.5,0.0,true,CycleMethod.NO_CYCLE,
                                        new Stop(0.1f,Color.web("#0c0c0c")),
                                        new Stop(0.9f,Color.web("#3f3f3f")));
    private LinearGradient greenIn = new LinearGradient(0.5,1.0,0.5,0.0,true,CycleMethod.NO_CYCLE,
                                        new Stop(0.1f,Color.web("#3d663d")),
                                        new Stop(0.9f,Color.web("#123e12")));
    private DropShadow dropShadow = new DropShadow(8.0, 3.0, 3.0, Color.web("#0c0c0c"));
    
    private Circle[][] circles1;
    private Circle[][] circles2;
    private StackPane[][] stackPanes;
    
    private double radiusCircle = 30.0;
    private double radiusCircleIn = 23.0;
    
    private boolean computerIsPlaying = false;
    private boolean gameOver = false;
    
    private RulesController rc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Rules.fxml"));
            this.rc = fxmlLoader.getController();
            this.rules = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.circles1 = new Circle[this.model.getX()][this.model.getY()];
        this.circles2 = new Circle[this.model.getX()][this.model.getY()];
        this.stackPanes = new StackPane[this.model.getX()][this.model.getY()];
        this.copyright.getStyleClass().add("copyright");
        this.dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        this.currentPlayer.setText("La couleur noire joue !");
        this.gameName.getStyleClass().clear();
        this.gameName.getStyleClass().add("gameNameWhite");
        
        for(int i=0;i<this.model.getX(); i++) {
            for(int j=0;j<this.model.getY(); j++) {
                this.circles1[i][j] = new Circle(radiusCircle, this.green);
                this.circles1[i][j].setOpacity(0.0);
                this.circles1[i][j].setEffect(dropShadow);
                this.circles2[i][j] = new Circle(radiusCircleIn, this.greenIn);
                this.circles2[i][j].setOpacity(0.0);
                this.stackPanes[i][j] = new StackPane();
                this.stackPanes[i][j].getChildren().add(this.circles1[i][j]);
                this.stackPanes[i][j].getChildren().add(this.circles2[i][j]);
                this.stackPanes[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        handleBoxAction(me);
                    }
                });
                this.stackPanes[i][j].setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        handleBoxActionOpacityIncrease(me);
                    }
                });
                this.stackPanes[i][j].setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        handleBoxActionOpacityDecrease(me);
                    }
                });
                this.gameGrid.add(this.stackPanes[i][j], i, j);
            }
        } 
        
        for(int i=0;i<this.model.getX(); i++) {
            for(int j=0;j<this.model.getY(); j++) {
                if(model.getState(i, j)==State.WHITE) {
                    this.circles1[i][j].setFill(this.white);
                    this.circles1[i][j].setOpacity(1.0);
                    this.circles2[i][j].setFill(this.whiteIn);
                    this.circles2[i][j].setOpacity(1.0);
                }
                if(model.getState(i, j)==State.BLACK) {
                    this.circles1[i][j].setFill(this.black);
                    this.circles1[i][j].setOpacity(1.0);
                    this.circles2[i][j].setFill(this.blackIn);
                    this.circles2[i][j].setOpacity(1.0);
                }
            }
        }
    }    

    @FXML
    private void handleBoxActionOpacityIncrease(MouseEvent event) {
        int x= (int) ((((StackPane) event.getSource()).getLayoutX())/73.0);
        int y= (int) ((((StackPane) event.getSource()).getLayoutY())/73.0);
        if(model.isPlayable(x, y)) {
            this.circles1[x][y].setOpacity(0.3);
            this.circles2[x][y].setOpacity(0.3);
        }
    }
    
    @FXML
    private void handleBoxActionOpacityDecrease(MouseEvent event) {
        int x= (int) ((((StackPane) event.getSource()).getLayoutX())/73.0);
        int y= (int) ((((StackPane) event.getSource()).getLayoutY())/73.0);
        if(model.isPlayable(x, y)) {
            this.circles1[x][y].setOpacity(0.0);
            this.circles2[x][y].setOpacity(0.0);
        }
    }
    
    @FXML
    private void handleBoxAction(MouseEvent event) {
        this.isPlayable.setText(" ");
        int x= (int) ((((StackPane) event.getSource()).getLayoutX())/73.0);
        int y= (int) ((((StackPane) event.getSource()).getLayoutY())/73.0);
        if(this.model.play(x, y)) {
            this.model.changeCurrentPlayer();
            if(this.computerIsPlaying && this.model.getCurrentPlayer()==State.WHITE) {
                if(this.model.canPlay()) {
                    int choice = (int) (random()*this.model.listOfPlayable().size());
                    int xC = (int) ((Point) this.model.listOfPlayable().get(choice)).getX();
                    int yC = (int) ((Point) this.model.listOfPlayable().get(choice)).getY();
                    this.model.play(xC, yC);
                    this.model.changeCurrentPlayer();
                }
                else {
                    this.isPlayable.setText("L'ordinateur (couleur blanche) ne peut pas jouer ce tour-ci !");
                    this.model.changeCurrentPlayer();
                    if(!this.model.canPlay()) {
                        this.isPlayable.setText("La partie est terminée.");
                        this.currentPlayer.setText(" ");
                        this.gameOver = true;
                    }
                }
            }
            
            if(!this.model.canPlay()) {
                if(this.computerIsPlaying) {
                    this.isPlayable.setText("Vous (couleur noire) n'avez pas pu jouer pendant 1 tour !");
                    this.model.changeCurrentPlayer();
                    boolean whitePlay = true;
                    int i=1;
                    while(whitePlay) {
                        if(this.model.canPlay()) {
                            int choice = (int) (random()*this.model.listOfPlayable().size());
                            int xC = (int) ((Point) this.model.listOfPlayable().get(choice)).getX();
                            int yC = (int) ((Point) this.model.listOfPlayable().get(choice)).getY();
                            this.model.play(xC, yC);
                            this.model.changeCurrentPlayer();
                            if(this.model.canPlay()) {
                                whitePlay = false;
                            }
                            else {
                                this.model.changeCurrentPlayer();
                            }
                            i++;
                            this.isPlayable.setText("Vous (couleur noire) n'avez pas pu jouer pendant " + i +" tours !");
                        }
                        else {
                            whitePlay = false;
                        }
                    }
                }
                else {
                    String player1 = (this.model.getCurrentPlayer()==State.WHITE)?" blanche ":" noire ";
                    this.isPlayable.setText("La couleur" + player1 + "ne peut pas jouer ce tour-ci !");
                    this.model.changeCurrentPlayer();
                }
                
                if(!this.model.canPlay()) {
                    this.isPlayable.setText("La partie est terminée.");
                    this.currentPlayer.setText(" ");
                    this.gameOver = true;
                }
            }
            
            if(this.gameOver) {
                int numberWhite=0;
                int numberBlack=0;
                for(int i=0;i<this.model.getX(); i++) {
                    for(int j=0;j<this.model.getY(); j++) {
                        if(model.getState(i, j)==State.WHITE) {
                            numberWhite++;
                        }
                        if(model.getState(i, j)==State.BLACK) {
                            numberBlack++;
                        }
                    }
                }
                if(numberBlack>numberWhite) {
                    if(this.computerIsPlaying) {
                        this.win.setText("Vous (couleur noire) gagnez la partie "+numberBlack+" à "+numberWhite+" !");
                    }
                    else {
                        this.win.setText("La couleur noire gagne la partie "+numberBlack+" à "+numberWhite+" !");
                    }
                    this.gameName.getStyleClass().clear();
                    this.gameName.getStyleClass().add("gameNameBlack");
                }
                else if(numberBlack<numberWhite) {
                    if(this.computerIsPlaying) {
                        this.win.setText("L'ordinateur (couleur blanche) gagne la partie "+numberWhite+" à "+numberBlack+" !");
                    }
                    else {
                        this.win.setText("La couleur blanche gagne la partie "+numberWhite+" à "+numberBlack+" !");
                    }
                    
                }
                else if(numberBlack==numberWhite) {
                    this.win.setText("Match nul "+numberBlack+" à "+numberWhite+" !");
                }
                else {
                    this.win.setText(" ");
                }
            }
            else {
                String currentPlayerColor = (this.model.getCurrentPlayer()==State.WHITE)?" blanche ":" noire ";
                this.currentPlayer.setText("La couleur"+ currentPlayerColor +"joue !");
            }
        }
        this.refreshView();
    }
    
    private void refreshView() {
        for(int i=0;i<this.model.getX(); i++) {
            for(int j=0;j<this.model.getY(); j++) {
                if(model.getState(i, j)==State.WHITE) {
                    this.circles1[i][j].setFill(this.white);
                    this.circles1[i][j].setOpacity(1.0);
                    this.circles2[i][j].setFill(this.whiteIn);
                    this.circles2[i][j].setOpacity(1.0);
                }
                if(model.getState(i, j)==State.BLACK) {
                    this.circles1[i][j].setFill(this.black);
                    this.circles1[i][j].setOpacity(1.0);
                    this.circles2[i][j].setFill(this.blackIn);
                    this.circles2[i][j].setOpacity(1.0);
                }
            }
        }
    }
    
    @FXML
    private void handleButtonOnAction(ActionEvent event) {
        this.model.init();
        this.gameOver=false;
        this.computerIsPlaying=this.computerPlay.isSelected();
        this.currentPlayer.setText("La couleur noire joue !");
        this.gameName.getStyleClass().clear();
        this.gameName.getStyleClass().add("gameNameWhite");
        this.isPlayable.setText(" ");
        this.win.setText(" ");
        for(int i=0;i<this.model.getX(); i++) {
            for(int j=0;j<this.model.getY(); j++) {
                if(model.getState(i, j)==State.EMPTY) {
                    this.circles1[i][j].setFill(this.green);
                    this.circles1[i][j].setOpacity(0.0);
                    this.circles2[i][j].setFill(this.greenIn);
                    this.circles2[i][j].setOpacity(0.0);
                }
                else if(model.getState(i, j)==State.WHITE) {
                    this.circles1[i][j].setFill(this.white);
                    this.circles1[i][j].setOpacity(1.0);
                    this.circles2[i][j].setFill(this.whiteIn);
                    this.circles2[i][j].setOpacity(1.0);
                }
                else if(model.getState(i, j)==State.BLACK) {
                    this.circles1[i][j].setFill(this.black);
                    this.circles1[i][j].setOpacity(1.0);
                    this.circles2[i][j].setFill(this.blackIn);
                    this.circles2[i][j].setOpacity(1.0);
                }
                else {
                    this.circles1[i][j].setFill(this.green);
                    this.circles1[i][j].setOpacity(0.0);
                    this.circles2[i][j].setFill(this.greenIn);
                    this.circles2[i][j].setOpacity(0.0);
                }
            }
        }
    }
    
    @FXML
    private void displayRules(ActionEvent event) {
        this.root.getChildren().add(this.rules);
    }
}
