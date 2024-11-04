package es.dsw.services;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Pelicula;

@Service
public class ServicePeliculaDAO {

	private String[] arrImage;
	private List<Pelicula> listaPeliculas;
	private List<String> listImages;
	private DateService serviceDate;
	MySqlConnection connection;

	public ServicePeliculaDAO(DateService serviceDate) {
		super();
		this.serviceDate = serviceDate;
		connection = new MySqlConnection(false);

	}

	public void startService() {

		loadFilesImages();
		generateListOfPeliculas();
	}

	private void loadFilesImages() {

		Resource recursoImg = new ClassPathResource("static/img/films/billboard");
		File archivosDeImagen;
		try {
			archivosDeImagen = recursoImg.getFile();
			arrImage = archivosDeImagen.list();
			listImages = new ArrayList<String>(Arrays.asList(arrImage));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void generateListOfPeliculas() {

		listaPeliculas = new ArrayList<>();
		double precio = serviceDate.getDiaHoy() == 4 ? 3.5 : 6;

		try {

			connection.open();
			ResultSet rs = connection.executeSelect(
					"SELECT NUMBERROOM_RCF AS 'SALA', TITLE_RF AS 'TITULO', IDFILM_RF AS 'ID', IDSESSION_SSF AS 'SESION' FROM SESSION_FILM\n"
							+ "INNER JOIN ROOMCINEMA_FILM ON SESSION_FILM.IDROOMCINEMA_SSF = ROOMCINEMA_FILM.IDROOMCINEMA_RCF\n"
							+ "INNER JOIN REPOSITORY_FILM ON SESSION_FILM.IDFILM_SSF = REPOSITORY_FILM.IDFILM_RF\n"
							+ "WHERE S_ACTIVEROW_SSF = 1 AND\n"
							+ "IDROOMCINEMA_RCF = IDROOMCINEMA_SSF AND\n"
							+ "S_ACTIVEROW_RCF = 1\n"
							+ "ORDER BY SESSION_FILM.IDROOMCINEMA_SSF ASC");
			
			while (rs.next()) {

				Pelicula pelicula = new Pelicula();
				pelicula.setId(rs.getInt("ID")); 
				pelicula.setSala(rs.getInt("SALA"));
				pelicula.setTitulo(rs.getString("TITULO"));
				pelicula.setSesion(rs.getInt("SESION"));
				int indexImage = listImages.indexOf("film" + pelicula.getId() + ".jpg");
				pelicula.setNombreImagenString(listImages.get(indexImage));
				pelicula.setPrecio(precio);
				listaPeliculas.add(pelicula);

			}

		} catch (Exception e) {

			System.out.println(connection.msgError());

		} finally {
			connection.close();
		}
	}

	public List<Pelicula> getListaPeliculas() {
		return listaPeliculas;
	}

}
