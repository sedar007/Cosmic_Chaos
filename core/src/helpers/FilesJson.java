package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.Arrays;
import java.util.HashMap;

public class FilesJson {
    HashMap jsonData;
    Json json;
    FileHandle file;
    String jsonString;

    public FilesJson(){
        //Sert à prendre le fichier
        file = Gdx.files.local("highScore.json");
    }


    public void initJson(){//initialisation des meilleurs scores
        jsonData = new HashMap<>();
        json = new Json();
        jsonData.put(1, 0.0);
        jsonData.put(2, 0.0);
        jsonData.put(3, 0.0);
        jsonData.put(4, 0.0);
        jsonData.put(5, 0.0);

        jsonString = json.toJson(jsonData);
        file.writeString(jsonString, false);
    }


    public HashMap readJson(){//Permettant de lire le fichier Json et aussi
        jsonData = new HashMap<>();
        json = new Json();
        jsonData = json.fromJson(HashMap.class, file);
        return jsonData;
    }

    public void writeJson(Double lastScore){//sert a modifier le fichier json
        HashMap jsonData2 = new HashMap<>();
        jsonData2 = new HashMap<>();
        json = new Json();

        this.jsonData = readJson();

        //stockage de tout le contenu du fichier json dans un tableau intermediaire
        //Raison: pour pouvoir classer les scores de maniere decroissante
        Double[] tableau = new Double[6];
        for( int i = 0 ; i < 5 ; i++){
            tableau[i] = (Double) jsonData.get(String.format("%d",i+1));
        }
        tableau[5] = lastScore;//Permettant de comparer le lastscore avec les 5 deja présents dans le fichier

        Arrays.sort(tableau);

        int j = 1;
        for( int i = 5 ; i > 0 ; i--){//Pour le mettre à decroissant
            jsonData2.put(j++,tableau[i]);

        }

        //Remplacement du contenu avec celui de jsonData2
        jsonString = json.toJson(jsonData2);
        file.writeString(jsonString, false);

    }

}
