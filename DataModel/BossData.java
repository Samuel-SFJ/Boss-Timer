package com.example.demo.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class BossData {

    private static BossData instance = new BossData();
    private String filename = "ListOfBosses.txt";

    private ObservableList<Boss> bossList;
    private DateTimeFormatter dtf;

    private BossData() {
        dtf = DateTimeFormatter.ofPattern("HH mm");
    }

    public static BossData getInstance() {
        return instance;
    }

    public ObservableList<Boss> getBossList() {
        return bossList;
    }

//    public void setBossList(List<Boss> bossList) {
//        this.bossList = bossList;
//    }

    public void saveList() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            Iterator<Boss> iter = bossList.iterator();
            while (iter.hasNext()) {
                Boss boss = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        boss.getName(), boss.getTimer().toMinutes(),
                        boss.getPlace()));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void loadList() throws IOException {

        bossList = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String name = itemPieces[0];
                String place = itemPieces[2];
                String respawn = itemPieces[1];
//                String timer = itemPieces[3];

//                LocalTime temporizer = LocalTime.parse(respawn,dtf);
                Boss boss = new Boss(name, Duration.ofMinutes(Integer.parseInt(respawn)),place);

                bossList.add(boss);
            }
        } finally {
            if (br==null){
                br.close();
            }
        }
    }

    public void addNewBoss(Boss b){
        bossList.add(b);
    }


}
