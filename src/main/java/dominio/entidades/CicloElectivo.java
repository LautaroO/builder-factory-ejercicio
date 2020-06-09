package dominio.entidades;

import java.time.LocalDate;
import java.util.Date;

public class CicloElectivo {
    private Integer año;

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Boolean sosCicloActual()
    {
        return LocalDate.now().getYear() == this.getAño() ;
    }
}
