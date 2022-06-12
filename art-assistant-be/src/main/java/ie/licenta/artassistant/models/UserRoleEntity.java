package ie.licenta.artassistant.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserRoleEntity")
@Table(name = "USER_ROLES")
public class UserRoleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private int id;


    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleName role;
}




