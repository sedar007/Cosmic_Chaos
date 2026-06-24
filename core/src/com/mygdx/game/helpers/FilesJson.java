package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Arrays;
import java.util.HashMap;

public class FilesJson {
    Preferences prefs;

    public FilesJson(){
        prefs = Gdx.app.getPreferences("CosmicChaosScores");
    }

    public void initJson(){
        prefs.putString("1", "0.0");
        prefs.putString("2", "0.0");
        prefs.putString("3", "0.0");
        prefs.putString("4", "0.0");
        prefs.putString("5", "0.0");

        prefs.flush();
    }

    public HashMap<Integer, Double> readJson(){
        HashMap<Integer, Double> jsonData = new HashMap<>();

        for(int i = 1; i <= 5; i++) {
            String scoreStr = prefs.getString(String.valueOf(i), "0.0");
            jsonData.put(i, Double.parseDouble(scoreStr));
        }

        return jsonData;
    }

    public void writeJson(Double lastScore){
        HashMap<Integer, Double> currentScores = readJson();

        Double[] tableau = new Double[6];
        for(int i = 0 ; i < 5 ; i++){
            tableau[i] = currentScores.get(i + 1);
        }
        tableau[5] = lastScore;

        Arrays.sort(tableau);

        int position = 1;
        for(int i = 5 ; i > 0 ; i--){
            prefs.putString(String.valueOf(position), String.valueOf(tableau[i]));
            position++;
        }

        prefs.flush();
    }
}