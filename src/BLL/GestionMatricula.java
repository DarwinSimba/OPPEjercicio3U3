package BLL;

import BEU.Curso;
import BEU.Estudiante;
import BEU.Matricula;
import SIMBA.BaseBllCRUD;
import SIMBA.BasePersistencia;
import SIMBA.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionMatricula extends BasePersistencia<Matricula>
        implements BaseBllCRUD<Matricula> {

    private Matricula matricula;
    private final String directorio = "Matriculas";

    public GestionMatricula() {
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiante: ").append(matricula.getEstudiante()).append("\n");
        sb.append("Curso: ").append(matricula.getCurso().getTitulo()).append("\n");
        sb.append("Promedio: ");
        sb.append(matricula.getPromedio());
        sb.append(matricula.imprmiDetalle());

        return sb.toString();
    }

    public String Calificar(float valor) {
        String mensaje = "";
        int num = this.matricula.addCalificacion(valor);
        switch (num) {
            case 0:
                mensaje = "Todas las notas estan registradas\n";
                break;
            case 1:
                mensaje = "Calificacion deuan unidad I ingresado correctamente.\n";
                break;
            case 2:
                mensaje = "Calificacion deuan unidad II ingresado correctamente.\n";
                break;
            case 3:
                mensaje = "Calificacion deuan unidad III ingresado correctamente.\n";
                break;
            default:
                mensaje = "Hubo un Error al ingresar la Calificacion\n";

        }
        return mensaje;
    }

    public void promediar() {
        this.matricula.CalcularPromedio();
    }

    public String imprimirDetalle() {
        return matricula.imprmiDetalle();

    }

    public void archivar() throws IOException {
        this.Escribir(directorio, this.matricula.getNumero(), matricula);

    }

    public void configurar(Curso cr, Estudiante est) {
        this.matricula.setCurso(cr);
        this.matricula.setEstudiante(est);
    }

    public List<Matricula> reportar(String titulo) throws IOException {
        List<Matricula> resultado = new ArrayList<>();
        List<String> contenidos = this.leerDirectorio(directorio, titulo);
        for (String texto : contenidos) {
            GsonBuilder gb = new GsonBuilder();
            gb.setPrettyPrinting();
            Gson gson = gb.create();
            try {
                Matricula m = gson.fromJson(texto, Matricula.class);
                resultado.add(m);
            } catch (JsonSyntaxException ex) {
                Util.imprimir("El archivo tiene errores");
                Util.imprimir(ex.toString());
            }
        }
        return resultado;

    }

    @Override
    public void Crear() {
        matricula = new Matricula();
    }

    @Override
    public void Consultar(String id) throws IOException {
        String archivo = id + ".json";
        String contenido = this.Leer(directorio, archivo);
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        matricula = gson.fromJson(contenido, Matricula.class);
    }

    @Override
    public void Actualizar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
