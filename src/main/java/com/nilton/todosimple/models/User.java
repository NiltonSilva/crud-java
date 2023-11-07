package com.nilton.todosimple.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public interface CreateUser { }
    public interface UpdateUser { }

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // diz que é auto incremento
    @Column(name = "id", unique = true) // da o nome pra coluna e diz que cada coluna é unica, não pode repetir nome
    private Long id;

    @Column(name = "usaerName", length = 100, nullable = false, unique = true)
    @NotNull (groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty (groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = CreateUser.class, min = 2, max = 100) 
    private String userName;
    
    @Column(name = "password", length = 60, nullable = false)
    @NotNull (groups = CreateUser.class)
    @NotEmpty (groups = CreateUser.class)
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;

    // private List<Task> tasks = new ArrayList<Task>();

    public User() { }

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof User))
            return false;
        User other = (User) obj;
        if(this.id == null)
            if(other.id != null)
                return false;
            else if(!this.id.equals(other.id)) 
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.userName, other.userName) && Objects.equals(this.password, other.password);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime + result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

}
