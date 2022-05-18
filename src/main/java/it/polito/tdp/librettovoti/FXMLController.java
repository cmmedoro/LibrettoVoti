package it.polito.tdp.librettovoti;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

public class FXMLController {
	
	private Libretto model; //va bene che il Controller conosca il modello, non il contrario

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbValutazione; //comboBox contiene una serie di oggetti, generici ---> gestisce una lista di oggetti
    								//quando scelgo un oggetto dalla lista, mi fa vedere il toString(), ma dentro mantiene 
    								//gli oggetti veri; qui popolo la comboBox con Integer (sostituisco al '?').
    @FXML
    private Label txtStatus;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtVoti;
    
    @FXML
    private DatePicker pickData;

    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	// 1. acquisizione e controllo dati
    	String nome = txtNome.getText();
    	Integer punti = cmbValutazione.getValue();
    	LocalDate data = pickData.getValue();
    	//controlli di validità
    	if(nome.equals("")|| punti == null || data == null) {
    		// errore, non posso eseguire l'operazione
    		txtStatus.setText("ERRORE: occorre inserire nome, voto e data");
    		return; //esco dal gestore dell'evento
    	}
    	// 2. esecuzione dell'operazione (chiedo al Model di farla)
    	boolean ok = model.add(new Voto(nome,punti, data));

    	//controller e modello dovrebbero parlarsi usando oggetti
    	// 3. visualizzazione/aggiornamento del risultato
    	if(ok) {
    		//il controller deve prendere i voti dal database ---> faccio un metodo che richiamo all'avvio del programma
    		//non nel metodo initialize() poichè il modello non c'è ancora ---> lo metto nel setModel():
    			/*List<Voto> voti = model.getVoti();
        		txtVoti.clear();
        		txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
        		for(Voto vv : voti) {
        			txtVoti.appendText(vv.toString()+"\n");
        		}*/
        	txtNome.clear();
        	cmbValutazione.setValue(null);
        	pickData.setValue(LocalDate.now());
        	txtStatus.setText("");
    	} else {
    		txtStatus.setText("ERRORE: Esame già presente");
    	}
    }
    //predispongo il Controller per agire sul modello
    public void setModel(Libretto model) {
    	this.model = model;
    	List<Voto> voti = model.getVoti();
    	txtVoti.clear();
    	txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
    	for(Voto vv : voti) {
    		txtVoti.appendText(vv.toString()+"\n");
    	}
    }
    @FXML
    void initialize() { //chiamato dopo che la scena è stata costruita; FXMLLoader inietta i riferimenti nel Controller
    					//e poi chiama initialize() quando ormai tutto è pronto per aggiungere inizializzazioni dell'utente
        assert cmbValutazione != null : "fx:id=\"cmbValutazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";
        //popolo la tendina della ComboBox
        cmbValutazione.getItems().clear(); //Items è una lista
        for(int i = 18; i <= 30; i++) {
        	cmbValutazione.getItems().add(i); //popolo la lista della tendina
        }
        pickData.setValue(LocalDate.now());
    }

}

