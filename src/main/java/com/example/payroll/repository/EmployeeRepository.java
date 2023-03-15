package com.example.payroll.repository;

import com.example.payroll.model.Employee;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRepository {

	private final SqlSession sqlSession;

	public EmployeeRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Employee selectEmployeeById(long id) {
		return this.sqlSession.selectOne("selectEmployeeById", id);
	}

	public List<Employee> findAll() {
		return this.sqlSession.selectList("findAll");
	}

	public Employee save(Employee employee){
//		return this.sqlSession.insert("save", employee);
		this.sqlSession.insert("save", employee);
		return employee;
	}

	public Employee update(Employee newEmployee){
		this.sqlSession.update("update", newEmployee);
		return newEmployee;
	}

	public int delete(int id) {
		return this.sqlSession.delete("deleteById", id);
	}

}
