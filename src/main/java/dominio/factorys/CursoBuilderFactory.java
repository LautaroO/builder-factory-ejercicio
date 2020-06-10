package dominio.factorys;

import dominio.Repositorios.Repository;
import dominio.builders.CursoBuilder;
import dominio.entidades.Docente;
import dominio.estrategias.asignacionDeAlumnos.PuntajeMayor;
import dominio.estrategias.asignacionDeDocentes.PorMenorDisponibilidad;

public class CursoBuilderFactory {

    private Integer minimoDeAlumnos;
    private Integer maximoDeAlumnos;
    private Repository<Docente> repositorioDocentes;

    public CursoBuilderFactory(Repository<Docente> repoDocentes)
    {
        this.minimoDeAlumnos = 2;
        this.maximoDeAlumnos = 10;
        this.repositorioDocentes = repoDocentes;
    }

    public CursoBuilder createBuilder()
    {
        CursoBuilder builder = new CursoBuilder();

        builder.setSelectorDeAlumnos(new PuntajeMayor());
        builder.setCantidadMinimaAlumnos(this.getMinimoDeAlumnos());
        builder.setCapacidadAlumnos(this.getMaximoDeAlumnos());
        builder.setAsignadorDeDocente(new PorMenorDisponibilidad(this.getRepoDocentes()));

        return builder;
    }

    public void setMinimoDeAlumnos(Integer minimo)
    {
        this.minimoDeAlumnos = minimo;
    }

    public void setMaximoDeAlumnos(Integer maximo)
    {
        this.maximoDeAlumnos = maximo;
    }

    private Integer getMinimoDeAlumnos()
    {
        return this.minimoDeAlumnos; // puede salir de un config
    }

    private Integer getMaximoDeAlumnos()
    {
        return this.maximoDeAlumnos; // puede salir de un config
    }

    private Repository<Docente> getRepoDocentes()
    {
        return this.repositorioDocentes;
    }

}
