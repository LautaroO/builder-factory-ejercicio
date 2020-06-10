package principal;

import dominio.Repositorios.Repository;
import dominio.Repositorios.daos.DAOMemo;
import dominio.builders.CursoBuilder;
import dominio.builders.ExcepcionDeCreacionDeCurso;
import dominio.entidades.*;
import dominio.factorys.CursoBuilderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TesterPrincipal {

    private CursoBuilderFactory builderFactory;
    private Repository<Docente> repoDocentes;
    private Materia diseño;
    private Materia algebra;
    private CicloElectivo cicloActual;
    private Alumno lalo;
    private Alumno julio;
    private Docente eze;
    private Docente juan;

    @Before
    public void antesDeTestear()
    {
        /** Materias */

        this.diseño = new Materia();
        diseño.setNombre("Diseño de Sistemas");

        this.algebra = new Materia();
        algebra.setNombre("Algebra");

        /** CiclosElectivos */
        this.cicloActual = new CicloElectivo();
        cicloActual.setAño(LocalDate.now().getYear());

        /** Docentes */
        this.repoDocentes = new Repository<Docente>(new DAOMemo<Docente>());

        this.eze = new Docente();
        eze.setNombre("Ezequiel");
        eze.setApellido("Escobar");
        eze.agregarMaterias(this.diseño);
        this.repoDocentes.insert(this.eze);

        this.juan = new Docente();
        juan.setNombre("Juan");
        juan.setApellido("Perez");
        juan.agregarMaterias(this.algebra);
        this.repoDocentes.insert(this.juan);



        /** Factory */
        this.builderFactory = new CursoBuilderFactory(this.repoDocentes);

        /** Alumnos */

        this.lalo = new Alumno();
        lalo.setNombre("Lautaro");
        lalo.setApellido("Olivera");
        lalo.setLegajo(1637642);
        lalo.setPuntaje(10);

        this.julio = new Alumno();
        julio.setNombre("Julio");
        julio.setApellido("Perez");
        julio.setLegajo(1234567);
        julio.setPuntaje(8);
    }

    /**
     *
     *  Tests!
     *
    */
    @Test
    public void creacionValidaDeUnCurso()
    {
        this.builderFactory.setMinimoDeAlumnos(2); //para fines del test

        CursoBuilder cursoBuilder = this.builderFactory.createBuilder();

        Curso nuevoCurso = new Curso(); //para que no me diga que "puede no estar inicializado"

        try
        {
            nuevoCurso = cursoBuilder.agregarTitulo("Un Curso los miercoles a la noche De Diseño")
                    .agregarDia(Dia.MIERCOLES)
                    .agregarCicloElectivo(this.cicloActual) //ciclo 2020
                    .agregarMateria(this.diseño) //diseño
                    .agregarDocente() //vamos a agregar uno mediante el algoritmo automatico
                    .agregarAlumnos(this.lalo, this.julio)
                    .build()
                    ;
        }
        catch(ExcepcionDeCreacionDeCurso ex)
        {
            Assert.fail(ex.getMessage());
        }

        Assert.assertEquals(nuevoCurso.getTitulo(), "Un Curso los miercoles a la noche De Diseño");
        Assert.assertEquals(nuevoCurso.getDia(), Dia.MIERCOLES);
        Assert.assertEquals(nuevoCurso.getCicloElectivo(), this.cicloActual);
        Assert.assertEquals(nuevoCurso.getMateria(), this.diseño);
        Assert.assertEquals(nuevoCurso.getDocente(), this.eze);
        Assert.assertTrue(nuevoCurso.tenesAlumno(this.lalo)
                    && nuevoCurso.tenesAlumno(this.julio)
        );
    }

    @Test(expected = ExcepcionDeCreacionDeCurso.class)
    public void crearCursoVacio() throws ExcepcionDeCreacionDeCurso {
        CursoBuilder builder = this.builderFactory.createBuilder();

        builder.build();
    }

    @Test(expected = ExcepcionDeCreacionDeCurso.class)
    public void crearCursoSinLlegarAlMinimoDeAlumnos() throws ExcepcionDeCreacionDeCurso
    {
        CursoBuilder builder = this.builderFactory.createBuilder();

        Assert.assertTrue(builder.getCantidadMinimaAlumnos() == 2); //el minimo es dos

        Curso nuevoCurso = builder.agregarTitulo("Un Curso los miercoles a la noche De Diseño")
                .agregarDia(Dia.MIERCOLES)
                .agregarCicloElectivo(this.cicloActual) //ciclo 2020
                .agregarMateria(this.diseño) //diseño
                .agregarDocente() //vamos a agregar uno mediante el algoritmo automatico
                .agregarAlumnos(this.lalo) // el minimo es dos
                .build()
        ;
    }
}
