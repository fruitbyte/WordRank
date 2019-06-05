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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
        while(scanner.hasNext()) {
            String word = scanner.next();
            System.out.println(word);
            System.out.println(words.size());
            Word wordObject = new Word(word);
            if(words.size() > 0) {
                ArrayList<String> tempPosArray = new ArrayList<String>();
                for(int j = 0; j < words.size(); j++) {
                    tempPosArray.add(j, words.get(j).getWordProperty());
                    System.out.println(tempPosArray);
                }
                if(tempPosArray.contains(wordObject.getWordProperty())){
                    int index = tempPosArray.indexOf(wordObject.getWordProperty());
                    int count = words.get(index).getCountProperty() + 1;
                    words.get(index).setCountProperty(count);
                    System.out.println(wordObject.getWord());
                } else {
                    wordObject.setCountProperty(1); //Sets initial count to 1.
                        words.add(wordObject); //Adds the word to words arrayList.
                        System.out.println(wordObject.getWord());
                }
                tempPosArray.clear();
            } else {
                wordObject.setCountProperty(1); //Sets initial count to 1.
                words.add(wordObject); //Adds the word to words arrayList.
                System.out.println("Added: " + wordObject.getWord());
            }
        }

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("wordProperty"));     //Word Column
        countColumn.setCellValueFactory(new PropertyValueFactory<>("countProperty"));   //Count Column

        tableView.setItems(getWords());
        }
//        scanner.close(); //Closes the scanner.

    public ObservableList<Word> getWords() {
        return words;
    }
}
