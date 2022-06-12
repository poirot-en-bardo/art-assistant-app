package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RoleEntity")
@Table(name = "ROLES")
public class RoleEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private int id;


    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;
}
