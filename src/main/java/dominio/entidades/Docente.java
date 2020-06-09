package dominio.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Docente {

    private String nombre;
    private String apellido;
    private List<Materia> materias;
    private List<Curso> cursos;

    public Docente()
    {
        this.materias = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Curso> getCursos() {
        return this.cursos.stream().filter(curso -> curso.estasActivo())
                .collect(Collectors.toList());
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Materia> getMaterias() {
        return this.materias;
    }

    public void agregarMaterias(Materia ... materias) {
        this.materias.addAll(Arrays.asList(materias));
    }

    public void agregarCursos(Curso ... curso)
    {
        this.cursos.addAll(Arrays.asList(curso));
    }

    /**
     *  Functions! :D
     */
    public Boolean estasDisponible(Dia unDia)
    {
        return this.getCursos().stream().allMatch(curso -> curso.getDia() != unDia);
    }

    public Boolean dictasMateria(Materia materia)
    {
        return this.materias.contains(materia);
    }

    public Integer cantidadCursos() {
        return this.getCursos().size();
    }
}
