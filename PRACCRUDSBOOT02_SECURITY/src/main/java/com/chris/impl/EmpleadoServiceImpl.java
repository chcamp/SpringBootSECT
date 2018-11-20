package com.chris.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chris.entity.Empleado;
import com.chris.repository.EmpleadoJpaRepository;
import com.chris.service.EmpleadoService;

@Service("empleadoServiceImpl")
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	@Qualifier("empleadoJpaRepository")
	private EmpleadoJpaRepository empleadoJpaRepository;

	@Override
	public List<Empleado> listAllEmpleados() {

		System.out.println("Aca metodo de listAllEmpleados()");

		return empleadoJpaRepository.findAll();
	}

	@Override
	public Empleado addEmpleado(Empleado empleado) {

		System.out.println("Entro metodo addEmpleado()");

		return empleadoJpaRepository.save(empleado);
	}

	@Override
	public int removeEmpleado(int id) {

		System.out.println("Metodo removeEmpleado");
		empleadoJpaRepository.delete(id);
		
		return 0;
	}

	@Override
	public Empleado updateEmpleado(Empleado empleado) {

		return empleadoJpaRepository.save(empleado);
	}

	@Override
	public Empleado getEmpleado(int id) {

		return empleadoJpaRepository.findById(id);
	}

}
