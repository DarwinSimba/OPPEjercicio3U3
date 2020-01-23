
package BEU;

import SIMBA.BaseBllCRUD;
import SIMBA.BasePersistencia;
import SIMBA.Persona;
import SIMBA.Unidad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


public class Matricula {
    private final String numero;
    private Calendar Fecha;
    private Estado estado;
    private Persona estudiante;
    private Curso curso; 
    private final List<Calificacion> calificaciones = new ArrayList<>();
    //Información
    private float promedio;
    

    public Matricula(){
        Fecha = Calendar.getInstance();
        estado = Estado.Registrada;
        UUID numeroAleatoreo = UUID.randomUUID();
        this.numero = numeroAleatoreo.toString();
    }

    public String getNumero() {
        return numero;
    }

    

    public Calendar getFecha() {
        return Fecha;
    }

    public void setFecha(Calendar Fecha) {
        this.Fecha = Fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Persona getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Persona estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public float getPromedio(){
        return promedio;
    }
    
    
    public void CalcularPromedio(){
        if(this.calificaciones.isEmpty()){
            return;
        }
        float suma = 0;
        for(Calificacion c : calificaciones){
            suma += c.valor;//Se puede acceder al atrivuto porque es una clase interna
            
        }
        int divisor = this.calificaciones.size();
        promedio = (float) suma /(float) divisor;
        
        if(divisor == 3 ){
            if(promedio > 14){
                this.estado = Estado.Aprobada;
                
            }
            else{
                this.estado = Estado.Reprobada;
                
            }
        }
    }
    //este metodo regristra una calificacion y rwtorna un numero
    //1 si es la nota de la unidad I
    //2 si es la nota de la unidad II
    //3 si es la nota de la unidad III
    //0 si ya tiene todas las notas
    public int addCalificacion(float v){
        Calificacion cal = new Calificacion();
        int cuentanotas = this.calificaciones.size();
        
        switch(cuentanotas){
            case 0:
                cal.setUnidad(Unidad.I);
                break;
            case 1:
                cal.setUnidad(Unidad.II);
                break;
            case 2:
                cal.setUnidad(Unidad.III);
                break;
                //Caso d etener todas las notas retorna 0
            default:
            return 0;
          
        }
  
        cal.setValor(v);
        cal.setFecha(Calendar.getInstance());
        this.calificaciones.add(cal);  
        this.CalcularPromedio();
        
        return this.calificaciones.size();
    }
    
    public String imprmiDetalle(){
        String str = "\n\t" + this.estudiante;
        for(Calificacion c : this.calificaciones){
            str += "\t\t" + c.getValor();
        }
        str += "\t\t" + this.promedio + "\n";
        return str;
        
    }

    
    
    class Calificacion{ //Clase interna de Matricula
        private Calendar fecha;
        private float valor;
        private Unidad unidad;

        public Calificacion() {
        }

        public Calendar getFecha() {
            return fecha;
        }

        public void setFecha(Calendar fecha) {
            this.fecha = fecha;
        }

        public float getValor() {
            return valor;
        }

        public void setValor(float valor) {
            this.valor = valor;
        }

        public Unidad getUnidad() {
            return unidad;
        }

        public void setUnidad(Unidad unidad) {
            this.unidad = unidad;
        }
        
        
    }
    
    @Override
    public String toString() {
        return estudiante.toString() + "#" + numero;
    }
    
    
    public List<Calificacion> getCalificaciones(){
        return this.calificaciones;
    }
    
    public String toSave(){
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        return gson.toJson(this);

}
}

