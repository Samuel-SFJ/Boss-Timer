package com.example.demo;

import com.example.demo.DataModel.Boss;
import com.example.demo.DataModel.BossData;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.util.List;

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



    public void initialize() {
//
//        Boss boss1 = new Boss("Maya Purple", Duration.ofMinutes(120),"Ant Hell");
//        Boss boss2 = new Boss("Orc Hero", Duration.ofMinutes(70),"Geffen Field 3");
//        Boss boss3 = new Boss("Atroce", Duration.ofMinutes(190),"Rachel Field 3");
//
//        bossesList = new ArrayList<Boss>();
//        bossesList.add(boss1);
//        bossesList.add(boss2);
//        bossesList.add(boss3);
//
//        BossData.getInstance().setBossList(bossesList);

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
    }
}