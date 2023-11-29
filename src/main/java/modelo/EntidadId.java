package modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntidadId implements Serializable{//representa la clave primaria

    @Id//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK autonumerica
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}