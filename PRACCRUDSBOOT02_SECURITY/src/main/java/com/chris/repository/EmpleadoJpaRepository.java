package com.chris.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chris.entity.Empleado;

@Repository("empleadoJpaRepository")
public interface EmpleadoJpaRepository extends JpaRepository<Empleado, Serializable>{
	
	public abstract Empleado findById(int id);
	
	
}
