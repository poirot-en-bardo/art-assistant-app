package ie.licenta.artassistant.models;

import ie.licenta.artassistant.constants.Role;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @JoinColumn(name = "ROLE")
    private Role role;
}
