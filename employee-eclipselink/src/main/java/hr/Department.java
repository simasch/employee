package hr;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Employee> employees = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
}
