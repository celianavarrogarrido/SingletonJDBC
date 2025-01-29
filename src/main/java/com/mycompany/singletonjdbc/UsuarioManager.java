/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
 * @author Celia Navarro Garrido
 */

package com.mycompany.singletonjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//creación de la clase de UsuarioManager en el Main 
public class UsuarioManager { 
    private static UsuarioManager instance;
    private List<Usuario> usuarios;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/usuarios"; //incluimos aquí el nombre de nuestra base de datos
    private static final String JDBC_USER = "root";  // Usuario 'root'
    private static final String JDBC_PASSWORD = "";  // Sin contraseña (vacío)

    // Constructor privado para evitar la creación de más instancias
    private UsuarioManager() {
        this.usuarios = new ArrayList<>();
    }

    // Método para obtener la instancia única de UsuarioManager (Singleton)
    public static synchronized UsuarioManager getInstance() {
        if (instance == null) {
            instance = new UsuarioManager();
            instance.cargarUsuariosDesdeBaseDeDatos();
        }
        return instance;
    }

    // Método para cargar los usuarios desde la base de datos
    private void cargarUsuariosDesdeBaseDeDatos() {
        System.out.println("Cargando usuarios desde la base de datos...");
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT * FROM usuarios";
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    Usuario usuario = new Usuario(id, nombre, email);
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la lista de usuarios cargados
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
