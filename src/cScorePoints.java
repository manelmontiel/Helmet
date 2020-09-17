public class cScorePoints implements Comparable<cScorePoints> {     //clase per donar forma al que es guarda al arxiu .json
    private String nombre;
    private int puntuacion;

    public cScorePoints(String nombre, int puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;}

    @Override
    public int compareTo(cScorePoints o) {
        return this.nombre.compareTo(o.nombre);}

    public int getPuntuacion() {return puntuacion;}

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " -- " + puntuacion;
    }
}
