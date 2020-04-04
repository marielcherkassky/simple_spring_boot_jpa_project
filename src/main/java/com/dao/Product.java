package com.dao;

import com.dao.Identities.ProductId;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductId.class) //DerivedIdentities
@Entity
/*The Product class is annotated with @Entity, indicating that it is a JPA entity.
(Because no @Table annotation exists, it is assumed that this entity is mapped to a table named product.)*/
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "product_id_seq")
    /*
    The Product object’s id property is annotated with @Id so that JPA recognizes it as the object’s ID.
    The id property is also annotated with @GeneratedValue to indicate that the ID should be generated automatically.
     */
    private Long pid=-1L; // initiate it for just a random value in order to hanlde hibernate bug

    /* UUID Id :
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")*/

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manufacturer",referencedColumnName = "id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private Manufacturer manufacturer;

    private String name;

    @JsonFormat(pattern = "dd/MM/YYYY")
    private Date register_date;


    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    public enum Rating {
        Amazing,Good_Value_For_Money,Bad
    }


}
