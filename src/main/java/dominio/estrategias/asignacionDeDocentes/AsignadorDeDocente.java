package dominio.estrategias.asignacionDeDocentes;

import dominio.Repositorios.RepositorioDeDocentes;
import dominio.entidades.Curso;
import dominio.entidades.Docente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AsignadorDeDocente {
    protected RepositorioDeDocentes repoDocentes;

    public AsignadorDeDocente(RepositorioDeDocentes repo)
    {
        this.repoDocentes = repo;
    }

    public abstract void asignarDocente(Curso curso) throws ExcepcionDeAsignadorDeDocente;

    public void asignarDocente(Curso curso, Docente unDocente) throws ExcepcionDeAsignadorDeDocente
    {
        if(!this.puedeDarCurso(curso,unDocente))
        {
            throw new ExcepcionDeAsignadorDeDocente("El docente no puede dar este curso.");
        }
        curso.setDocente(unDocente);
        unDocente.agregarCursos(curso);
    }

    /**
     *  Protected functions
     *
     */
    protected Boolean puedeDarCurso(Curso unCurso, Docente unDocente)
    {
        return unDocente.estasDisponible(unCurso.getDia()) &&
                unDocente.dictasMateria(unCurso.getMateria());
    };
    protected List<Docente> docentesDisponibles(Curso unCurso) throws ExcepcionDeAsignadorDeDocente
    {
        List<Docente> docentes = new ArrayList<>();
        docentes.addAll(this.repoDocentes.getDocentes());

        if(docentes.size() <= 0)
        {
            throw new ExcepcionDeAsignadorDeDocente("No hay docentes disponibles");
        }
        return docentes;
    }

}