package izPrvogDela;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static javafx.scene.text.FontWeight.BOLD;
import javafx.stage.Stage;

public class PZ01 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        HFraktalPane hfraktal = new HFraktalPane();

        BorderPane bpane = new BorderPane(hfraktal);

        //polje za unos stepena
        TextField stepen = new TextField();
        stepen.setPrefColumnCount(3);
        stepen.setAlignment(Pos.BASELINE_RIGHT);
        stepen.setText("");
        
        //dugme za izlazak
        Button izadjiDugme = new Button("X");
        izadjiDugme.setTextFill(Color.RED);
        izadjiDugme.setFont(Font.font(STYLESHEET_MODENA, BOLD, 15));
        izadjiDugme.setAlignment(Pos.BASELINE_RIGHT);
        
        izadjiDugme.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        Alert alert = new Alert(AlertType.INFORMATION);
        
        hfraktal.postaviStepen(0);
        hfraktal.crtaj();

        //kada se unese stepen
        stepen.setOnKeyPressed(new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent e) {
                
                if (e.getCode() == KeyCode.ENTER) {
                    try {
                        
                        hfraktal.postaviStepen(Integer.parseInt(stepen.getText()));
                    
                      //ukoliko se ne unese broj exception
                    } catch (NumberFormatException ex) {
                        
                        hfraktal.postaviStepen(1);  
                        hfraktal.crtaj();
                        stepen.setText("");
                    }
                    
                    if (Integer.parseInt(stepen.getText()) >= 1) {
                        
                        hfraktal.crtaj();
                    } else {
                        
                        //alert za unos stepena koji je manji od 1
                        alert.setTitle("GRESKA!");
                        alert.setHeaderText("Zbog unosa pogresnog stepena fraktala,"
                                + " fraktal ce biti vracen na stepen 1.");
                        alert.setContentText("Uneli ste neispravan stepen: " + stepen.getText()
                                + "\nStepen ne moze biti manji od 1!\n"
                                        + "Molimo Vas unesite stepen ponovo!");
                        alert.showAndWait();
                        
                        hfraktal.postaviStepen(1);
                        hfraktal.crtaj();
                        stepen.setText("");
                    }
                }
            }
        });

        //podesavanje izgleda
        Label unosStepena = new Label("Unesite stepen koji zelite da bude prikazan: ");
        HBox hBox = new HBox(10, unosStepena, stepen, izadjiDugme);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        bpane.setBottom(hBox);
        hBox.setPadding(new Insets(15));

        Scene scene = new Scene(bpane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CS103 - PZ01");
        primaryStage.show();
        primaryStage.setResizable(false);
        hfraktal.requestFocus();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
