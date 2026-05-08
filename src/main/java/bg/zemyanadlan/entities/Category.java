package bg.zemyanadlan.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "category_scope",
            columnDefinition = "varchar(50)"
    )
    private CategoryScope scope;
}
