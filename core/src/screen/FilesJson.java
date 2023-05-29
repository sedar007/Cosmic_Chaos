package screen;

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

        file = Gdx.files.local("highScore.json");

    }


    public void initJson(){
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


    public HashMap readJson(){
        jsonData = new HashMap<>();
        json = new Json();
        jsonData = json.fromJson(HashMap.class, file);
        return jsonData;
    }

    public void writeJson(Double lastScore){
        HashMap jsonData2 = new HashMap<>();
        jsonData2 = new HashMap<>();
        json = new Json();

        this.jsonData = readJson();
        //jsonData = new HashMap<>();

       // System.out.println(jsonData);
        Double[] tableau = new Double[6];
        for( int i = 0 ; i < 5 ; i++){
            tableau[i] = (Double) jsonData.get(String.format("%d",i+1));
        }
        tableau[5] = lastScore;

        Arrays.sort(tableau);

        int j = 1;
        for( int i = 5 ; i > 0 ; i--){
            jsonData2.put(j++,tableau[i]);

        }


        jsonString = json.toJson(jsonData2);
        file.writeString(jsonString, false);

    }

    public void changeScore(Double lastScore){
/*
        file = Gdx.files.internal("fichier.json");
        json = new Json();
        HashMap<String, Object> jsonData = json.fromJson(HashMap.class, file);
        String nom = (String) jsonData.get("nom");
        int age = (int) jsonData.get("age");
        String ville = (String) jsonData.get("ville");




        jsonData = new HashMap<>();
        json = new Json();
        file = Gdx.files.local("highScore.json");
        Double[] tableau = new Double[6];

        for( int i = 0 ; i < 5 ; i++){
            tableau[i] = jsonData.get(i + 1);
            System.out.println(tableau[i]);

        }
        tableau[5] = lastScore;

        Arrays.sort(tableau);
        int j = 1;
        for( int i = 4 ; i >= 0 ; i--){
            jsonData.put( j++ ,tableau[i]);
        }
        jsonString = json.toJson(jsonData);
        file.writeString(jsonString, false);


 */
        /*String tmp =" ";
        if(jsonData.get(1) < lastScore){//le meilleur
            backup.put(1,lastScore);
            for(Integer key : jsonData.keySet()){
                if( key != 5 )
                    backup.put(key + 1 ,jsonData.get(key));
            }
            jsonString = json.toJson(backup);
            file.writeString(jsonString, false);

            for (Integer cle : backup.keySet()) {
                tmp = tmp + cle + " -> " + backup.get(cle) + "\n";
            }
            return tmp;
        }
        else{
            for (Integer cle : jsonData.keySet()) {
                tmp = tmp + cle + " -> " + jsonData.get(cle) + "\n";
            }
            return tmp;
        }*/
    }
}
