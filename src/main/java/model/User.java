package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullname;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Issue> assignedIssues = new ArrayList<>();

    private int projectSize;

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	public String getFullname() {
		// TODO Auto-generated method stub
		return fullname;
	}

	public void setFullname(String  fullname) {
		// TODO Auto-generated method stub
		this.fullname = fullname;
	}
}
