package com.example.demo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class formularioPersona {

		@NotNull
		@Size(min=2, max=30)
		private String Nombre;

		@NotNull
		@Min(18)
		private Integer Edad;

		public String getNombre() {
			return this.Nombre;
		}

		public void setNombre(String name) {
			this.Nombre = name;
		}

		public Integer getEdad() {
			return Edad;
		}

		public void setEdad(Integer age) {
			this.Edad = age;
		}

		public String toString() {
			return "Person(nombre: " + this.Nombre + ", Age: " + this.Edad + ")";
		}
	}