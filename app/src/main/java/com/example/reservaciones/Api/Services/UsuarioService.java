package com.example.reservaciones.Api.Services;

import com.example.reservaciones.Model.Usuario;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {

    @GET("/API/Proyecto_reservaciones/usuarios.php")
    Call<List<Usuario>> getUsuarios();

    @GET("/API/Proyecto_reservaciones/login.php")
    Call<Usuario> getUsuario(@Query("usuario") String usuario);

}
