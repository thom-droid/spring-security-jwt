package org.test.jwttest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String memberName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role = new ArrayList<>();

}
