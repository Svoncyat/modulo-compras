package com.compras;

import com.compras.controller.ModuloConfiguracion;
import com.compras.controller.ModuloMenu;

public class Main {
    public static void main(String[] args) {
        ModuloMenu menu = new ModuloMenu();
        //menu.ejecutar();

        ModuloConfiguracion configuracion = new ModuloConfiguracion();
        configuracion.ejecutar();
    }
}