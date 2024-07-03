/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Arco;
import it.polito.tdp.itunes.model.Genre;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="btnMassimo"
    private Button btnMassimo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCanzone"
    private ComboBox<Track> cmbCanzone; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGenere"
    private ComboBox<Genre> cmbGenere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void btnCreaLista(ActionEvent event) {
    	Track t = cmbCanzone.getValue();
    	String input = txtMemoria.getText();
    	if (t.equals(null) || input.compareTo("")==0) {
    		txtResult.setText("Scegli una canzone e inserisci una memoria");
    		return;
    		
    	}
    	int memoria = 0;
    	try {
    		memoria =Integer.parseInt(input);
    	}catch(NumberFormatException e ) {
    		txtResult.setText("La memoria deve essere un numero intero");
    		return;
    	}
    	txtResult.appendText("La lista delle canzoni è: "+"\n");
    	List<Track> lista = model.trovaLista(t,memoria);
    	for (Track traccia: lista) {
    		txtResult.appendText(traccia+"\n");
    	}

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	Genre g = cmbGenere.getValue();
    	if (g.equals(null)) {
    		txtResult.setText("Seleziona un genere");
    		return;
    	}
    	model.creaGrafo(g);
    	txtResult.appendText("Vertici: "+ model.getV()+"\n");
    	txtResult.appendText("Archi: "+ model.getA()+"\n");
    	cmbCanzone.getItems().addAll(model.getVertici());
    }

    @FXML
    void doDeltaMassimo(ActionEvent event) {
    	List<Arco> archi = model.getMaxDelta();
    	for (int i =0; i<archi.size();i++) {
    		txtResult.appendText(archi.get(i)+"\n");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMassimo != null : "fx:id=\"btnMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCanzone != null : "fx:id=\"cmbCanzone\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbGenere.getItems().addAll(model.getGeneri());
    }

}
