package com.example.reservaciones.Api.Services;

import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.Model.Rol;
import com.example.reservaciones.Model.SincronizacionModel;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.Serializer.ReservacionSerializer;
import com.example.reservaciones.Serializer.RolSerializer;
import com.example.reservaciones.Serializer.UsuarioSerializer;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {

    @GET("/webservices/trunk/Proyecto_reservaciones/usuarios.php")
    Call<List<Usuario>> getUsuariosList();

    @GET("/webservices/trunk/Proyecto_reservaciones/login.php")
    Call<Usuario> getUsuarios(@Query("usuario") String usuario);

    @GET("/webservices/trunk/Proyecto_reservaciones/get_all_usuario.php")
    Call<UsuarioSerializer> getUsuario();
    @GET("/webservices/trunk/Proyecto_reservaciones/get_all_usuario.php")
    Call<UsuarioSerializer> obtenerUsuario(@Query("usuario") String usuario);

    @GET("/webservices/trunk/Proyecto_reservaciones/get_all_usuario.php")
    Call<UsuarioSerializer> getUsuarioD (@Query("usuario") String usuario);

    @GET("/webservices/trunk/Proyecto_reservaciones/get_all_rol.php")
    Call<RolSerializer> getRol();

    @GET("/webservices/trunk/Proyecto_reservaciones/get_all_reservacion.php")
    Call<ReservacionSerializer> getReservacion();

    @POST("/webservices/trunk/Proyecto_reservaciones/insertar_usuario.php")
    @FormUrlEncoded
    Call<SincronizacionModel> setUsuario(@Field("json") String json);

    @POST("/webservices/trunk/Proyecto_reservaciones/insertar_reservaciones.php")
    @FormUrlEncoded
    Call<SincronizacionModel> SetReservaciones(@Field("json") String json);

    @POST("/webservices/trunk/Proyecto_reservaciones/insertar_detalle.php")
    @FormUrlEncoded
    Call<SincronizacionModel> setDetalle(@Field("json") String json);

}
