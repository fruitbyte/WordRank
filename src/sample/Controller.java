/*
***************************
    "Work Rank Utility"

    @won_tonsoup 2019
***************************
*/

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{

    @FXML private TextField fileChosenField;
    @FXML private TableView<Word> tableView;
    @FXML private TableColumn<Word, String> wordColumn;
    @FXML private TableColumn<Word, Number> countColumn;

    String filePath;

    //ObservableList that will hold all the words in
    ObservableList<Word> words = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }

    //FileChooser button that allows files pane to open and the user can select a .txt file.
    public void fileChooserBtnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //.txt file format filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".txt files", "*.txt"));

        File selected = fileChooser.showOpenDialog(null);

        if(selected != null) {
            filePath = selected.getAbsolutePath();
            fileChosenField.setText(selected.getName());
        } else {
            fileChosenField.setText("Invalid file.");
        }
    }

    public void startBtnClick(ActionEvent event) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        //While loop that checks if there is something next
        while(scanner.hasNext()){
            String word = scanner.next();
            Word wordObject = new Word(word);
            if(words.contains(wordObject)){
                int count = wordObject.getCountProperty();
                wordObject.setCountProperty(count);
            } else {
                wordObject.setCountProperty(1); //Sets initial count to 1.
                words.add(wordObject); //Adds the word to words arrayList.
            }
        }
        scanner.close(); //Closes the scanner.

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("wordProperty"));     //Word Column
        countColumn.setCellValueFactory(new PropertyValueFactory<>("countProperty"));   //Count Column

        tableView.setItems(getWords());

    }

    public ObservableList<Word> getWords() {
        return words;
    }
}
