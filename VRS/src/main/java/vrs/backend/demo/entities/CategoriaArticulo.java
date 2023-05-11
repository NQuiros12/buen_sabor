package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="CategoriaArticulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaArticulo extends Base {
    @Column(name="denominacion")
    private String denominacion;

    //Recursivo
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoriaArticulo parent;

//    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.MERGE)
//    private List<CategoriaArticulo> children = new ArrayList<CategoriaArticulo>();

//    public void addChild(CategoriaArticulo child) {
////        children.add(child);
//        child.setParent(this);
//    }
//
//    public void removeChild(CategoriaArticulo child) {
////        children.remove(child);
//        child.setParent(null);
//    }
}
