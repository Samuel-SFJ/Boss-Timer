package com.example.demo;

import com.example.demo.DataModel.Boss;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.time.LocalTime;

public class DialogController {

    @FXML
    private TextField bossNameEdit;
    @FXML
    private TextField bossPlaceEdit;
    @FXML
    private TextField bossTimerEdit;
    @FXML
    private TextField bossRespawnEdit;


    public void bossEdit(Boss b){

        if (!bossNameEdit.getText().isEmpty() && !b.getName().equalsIgnoreCase(bossNameEdit.getText())){
            b.setName(bossNameEdit.getText());

        }

        if (!bossNameEdit.getText().isEmpty() && !b.getPlace().equalsIgnoreCase(bossPlaceEdit.getText())){
            b.setPlace(bossPlaceEdit.getText());
        }

        if (!bossTimerEdit.getText().isEmpty()) {
            int minutes = Integer.parseInt(bossTimerEdit.getText());
            Duration m = Duration.ofMinutes(minutes);
            b.setTimer(m);
        }

        if (!bossRespawnEdit.getText().isEmpty()) {
            String s = bossRespawnEdit.getText();
            String[] s1 = s.split("H");

            int hour = Integer.parseInt(s1[0]);
            int minute = Integer.parseInt(s1[1]);
            b.markKill(LocalTime.of(hour, minute));
        }
    }
}
