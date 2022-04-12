package com.example.demo;

import com.example.demo.DataModel.Boss;
import com.example.demo.DataModel.BossData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class HelloController {

    private List<Boss> bossesList;

    @FXML
    private TextField bName;
    @FXML
    private TextField bTimer;
    @FXML
    private TextField bSpawn;
    @FXML
    private ListView<Boss> listOfBosses;
    @FXML
    private Label spawnTime;
    @FXML
    private Label place;
    @FXML
    private BorderPane mainWindow;

    @FXML
    private TextField bossNameEdit;
    @FXML
    private TextField bossTimerEdit;
    @FXML
    private TextField bossRespawnEdit;
    @FXML
    private TextField bossPlaceEdit;




    public void initialize() {


        listOfBosses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Boss>() {
            @Override
            public void changed(ObservableValue<? extends Boss> observableValue, Boss oldBoss, Boss newBoss) {
                if (newBoss != null) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("H:mm");
                    String spawn = listOfBosses.getSelectionModel().getSelectedItem().getRespawn().format(df);

                    System.out.println(spawn);

                    spawnTime.setText(spawn);
                    place.setText(" " + " at " + newBoss.getPlace());

//                    bossTextArea.setText(spawn);


                }
            }
        });

        listOfBosses.setItems(BossData.getInstance().getBossList());
        listOfBosses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listOfBosses.getSelectionModel().selectFirst();

        
    }

    @FXML
    public void beginTimer() {
        for (Boss boss : listOfBosses.getSelectionModel().getSelectedItems()) {
            boss.markKill();
        }
    }

    @FXML
    public void addNew(){
        long timerValue = Long.parseLong(bTimer.getText());
        Boss boss = new Boss(bName.getText(),Duration.ofMinutes(timerValue),bSpawn.getText());
        BossData.getInstance().addNewBoss(boss);
//        bName.setText("Name");
//        bSpawn.setText("Place");
//        bTimer.setText("Timer");
    }

    @FXML
    public void openEdit(){
        Boss boss = listOfBosses.getSelectionModel().getSelectedItem();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DialogController.fxml"));
            dialog.getDialogPane().setContent(root);
        } catch (IOException e) {
            System.out.println("Couldn't load the file");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            bossEdit(boss);
        } else if (result.isPresent() && result.get() == ButtonType.CANCEL){
            System.out.println("Cancel pressed");
        }
    }

    @FXML
    public void bossEdit(Boss b){
        b.setName(bossNameEdit.getText());//bossNameEdit;
        b.setPlace(bossPlaceEdit.getText());//bossPlaceEdit;

        int minutes = Integer.parseInt(bossTimerEdit.getText());
        Duration m = Duration.ofMinutes(minutes);
        b.setTimer(m);

//        bossTimerEdit;

    }
}