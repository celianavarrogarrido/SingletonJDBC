package com.mycompany.singletonjdbc;

/** 
 * @author Celia Navarro Garrido
 */
public class SingletonJDBC {

    public static void main(String[] args) {
        // Primer acceso a UsuarioManager, se cargan los datos
        UsuarioManager manager1 = UsuarioManager.getInstance();
        System.out.println("Usuarios cargados desde la base de datos:");
        for (Usuario usuario : manager1.getUsuarios()) {
            System.out.println(usuario);
        } 

        // Segundo acceso a UsuarioManager, no se recargan los datos
        UsuarioManager manager2 = UsuarioManager.getInstance();
        System.out.println("Usuarios nuevamente cargados (de la memoria, no desde la base de datos):");
        for (Usuario usuario : manager2.getUsuarios()) {
            System.out.println(usuario);
        }
    }
}
