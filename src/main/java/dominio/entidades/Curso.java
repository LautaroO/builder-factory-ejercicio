package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String titulo;
    private Materia materia;
    private CicloElectivo cicloElectivo;
    private Docente docente;
    private List<Alumno> alumnos;
    private Dia dia;

    public Curso()
    {
        this.alumnos = new ArrayList<>();
    }

    /**
     *  Getters and setters
     *
     */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public CicloElectivo getCicloElectivo() {
        return cicloElectivo;
    }

    public void setCicloElectivo(CicloElectivo cicloElectivo) {
        this.cicloElectivo = cicloElectivo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public void agregarAlumnos(List<Alumno> alumnosSeleccionados) {
        alumnosSeleccionados.forEach(alumno -> this.agregarAlumno(alumno));
    }

    private void agregarAlumno(Alumno alumno) {
        this.getAlumnos().add(alumno);
    }

    /**
     *  Functions! :D
     */

    public Boolean estasActivo()
    {
        return this.getCicloElectivo().sosCicloActual();
    }
    public Integer cantidadAlumnos() {return this.getAlumnos().size();}
    public Boolean tenesAlumno(Alumno alumno)
    {
        return this.getAlumnos().contains(alumno);
    }
}
