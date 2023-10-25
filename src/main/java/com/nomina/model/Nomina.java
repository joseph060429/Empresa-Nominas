package com.nomina.model;

import com.nomina.model.*;

public class Nomina {
	/**
	 * Contiene los sueldos base para cada categoría de empleado. Los sueldos están
	 * ordenados por categoría, donde la categoría 1 es el primer elemento del
	 * array.
	 */

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 * Calcula el sueldo de un empleado en función de su categoría y empleado en
	 * funcion de su categoria y sus años.
	 *
	 * 
	 * @param El objeto e calculará el sueldo.
	 * @return El sueldo calculado para el empleado.
	 */

	public static int sueldo(Empleado e) {

		return SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.anyos;

	}
}
