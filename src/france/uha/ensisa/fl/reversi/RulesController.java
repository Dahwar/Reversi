package france.uha.ensisa.fl.reversi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 *
 * @author Florent
 */
public class RulesController implements Initializable {
    
    @FXML
    private Text rulesText;
    @FXML
    private Text rules;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rulesText.getStyleClass().add("gameRules");
        this.rules.setText("\nReversi\n\nLe but du jeu est d'obtenir le plus de pion de votre couleur. La couleur noire débute toujours."
                + "\n\nPour retourner les pions de la couleur adverse, il suffit de les encadrer par deux pions de notre couleur (en "
                + "ligne, colonne ou diagonale).\n\nIl arrive que l'une des couleurs ne puisse pas jouer pendant un ou plusieurs "
                + "tours.\n\nLa partie se termine quand aucune des deux couleurs ne peut plus retourner de pions adverses. Le gagnant "
                + "est la couleur dont le plus de pions apparaissent sur le plateau.\n\nEn mode un joueur, c'est-à-dire humain VS "
                + "ordinateur, l'intelligence artificielle joue immédiatemment après vous. Si vous ne pouvez pas jouer, la machine "
                + "rejouera jusqu'à ce que vous puissiez de nouveau jouer.\n\n Play & Enjoy :)\n");
    }
    
    @FXML
    private void returnToGame(ActionEvent event) {

    }
}