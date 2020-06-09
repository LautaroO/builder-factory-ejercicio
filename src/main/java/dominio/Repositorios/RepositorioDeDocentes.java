package dominio.Repositorios;

import dominio.entidades.Docente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioDeDocentes {

    private List<Docente> docentes;

    public RepositorioDeDocentes()
    {
        this.docentes = new ArrayList<>();
    }

    public void agregarDocentes(Docente... docentes){
        this.docentes.addAll(Arrays.asList(docentes));
    }

    public List<Docente> getDocentes()
    {
        return this.docentes;
    }

}
