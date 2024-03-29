package com.hd.blog.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hd.blog.common.util.AuthProvider;
import com.hd.blog.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Size(min = 5, max = 30)
    @Column(length = 30, unique = true, nullable = false)
    private String email;

    @Size(min = 1, max = 30)
    @Column(name = "user_name", length = 30, nullable = false)
    private String userName;

    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = true)
    private String password;

    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})

    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Post> posts;

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date")
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(String email, String password, String userName){
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals( Object obj ) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
