package com.chris.service;

import java.util.List;

import com.chris.entity.Empleado;


public interface EmpleadoService {

	public abstract List<Empleado> listAllEmpleados();

	public abstract Empleado addEmpleado(Empleado empleado);

	public abstract int removeEmpleado(int id);

	public abstract Empleado updateEmpleado(Empleado empleado);

	public abstract Empleado getEmpleado(int id);

}
