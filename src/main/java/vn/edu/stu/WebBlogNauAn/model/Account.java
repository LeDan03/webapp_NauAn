package vn.edu.stu.WebBlogNauAn.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String username;

    @Column()
    private String password;

    @Column(unique=true)
    private String email;

    @Column()
    private String avatar_url;

    @Column()
    private Timestamp created_at;

    @Column()
    private Timestamp updated_at;

}
