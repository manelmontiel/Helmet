import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class cPuntuacion {      //clase per guardar i mostrar les dades de l'arxiu .json

    //Metode per guardar les dades de la partida en un json
    public static void guardarDades(String nombre, int Puntuacion) throws IOException {
        FileReader llegir = new FileReader("puntuaciones.json");
        JSONParser parser = new JSONParser();
        JSONArray partides = new JSONArray();

        try{
            partides = (JSONArray) parser.parse(llegir);
        }catch(Exception e){}

        JSONObject puntuacionsJson = new JSONObject();

        puntuacionsJson.put("Nom_Jugador", nombre);
        puntuacionsJson.put("Puntuacion", Puntuacion);


        partides.add(puntuacionsJson);

        FileWriter cargarDades = new FileWriter("puntuaciones.json");

        cargarDades.write(partides.toJSONString());

        cargarDades.flush();
        cargarDades.close();

    }

    //Metode que retorna un arrylist amb totes les puntuacions ordenades
    public static ArrayList<cScorePoints> mostrarDades() throws FileNotFoundException {

        FileReader llegir = new FileReader("puntuaciones.json");
        JSONParser parser = new JSONParser();
        JSONArray partides = new JSONArray();

        try{
            partides = (JSONArray) parser.parse(llegir);
        }catch(Exception e){}

        ArrayList<cScorePoints> array_puntuacio_partida = new ArrayList<>();

        for (int i = 0; i < partides.size(); i++){
            JSONObject JSONpartides = (JSONObject) partides.get(i);

            String nom_jugador = JSONpartides.get("Nom_Jugador").toString();
            int puntuacion = Integer.parseInt(JSONpartides.get("Puntuacion").toString());

            array_puntuacio_partida.add(new cScorePoints(nom_jugador, puntuacion));
        }

        Collections.sort(array_puntuacio_partida, new Orden_Puntuacion());

        for(int i = 0; i < array_puntuacio_partida.size(); i++){
            if (i >= 5){
                array_puntuacio_partida.remove(i);
            }
        }
        return array_puntuacio_partida;
    }

    public static class Orden_Puntuacion implements Comparator<cScorePoints> {
        @Override
        public int compare(cScorePoints score1, cScorePoints score2){
            return score2.getPuntuacion() - score1.getPuntuacion();
        }
    }
}
