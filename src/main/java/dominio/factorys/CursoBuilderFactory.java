package dominio.factorys;

import dominio.Repositorios.RepositorioDeDocentes;
import dominio.builders.CursoBuilder;
import dominio.estrategias.asignacionDeAlumnos.PuntajeMayor;
import dominio.estrategias.asignacionDeDocentes.PorMenorDisponibilidad;

public class CursoBuilderFactory {

    private Integer minimoDeAlumnos;
    private Integer maximoDeAlumnos;
    private RepositorioDeDocentes repositorioDocentes;

    public CursoBuilderFactory(RepositorioDeDocentes repoDocentes)
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

    private RepositorioDeDocentes getRepoDocentes()
    {
        return this.repositorioDocentes;
    }

}
