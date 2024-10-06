package com.compras.model;

public class Comprador {

        private String id;
        private String nombre;
    
        public Comprador(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }
    
        public String getId() { 
            return id; 
        }
        public String getNombre() { 
            return nombre;
        }
    
}
