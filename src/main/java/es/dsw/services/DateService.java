package es.dsw.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DateService {
	
	private Date hDate;
	private SimpleDateFormat formatoES = new SimpleDateFormat("EEEE', día' dd' de ' MMMM");
	private SimpleDateFormat formatoHora = new SimpleDateFormat("HH'h' mm'm'");
	private String fechaHoy;
	private String hora;
	private int diaHoy;
	private String mensajeBienvenidaString;
	
	public DateService() {}
	
	public void startAndRefresh() {
		
		hDate = new Date(); 
		fechaHoy = formatoES.format(hDate);
		diaHoy = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		hora = formatoHora.format(hDate);
		
		mensajeBienvenidaString = switch (diaHoy) {
		case 1 -> "Vente y carga las pilas.";
		case 2 -> "Comienza la semana a lo grande.";
		case 3 -> "Hoy doble de palomitas.";
		case 4 -> "Día del espectador.";
		case 5 -> "La noche de las aventuras.";
		case 6 -> "No te quedes en tu casa.";
		case 7 -> "¿Ya has hecho planes para esta noche?";
		default -> "Argumento desconocido";
		};
	}
	
	public String getFechaHoy() {
		return fechaHoy;
	}

	public String getMensajeBienvenidaString() {
		return mensajeBienvenidaString;
	}
	
	public String getHora() {
		return hora;
	}
	
	public int getDiaHoy() {
		return diaHoy;
	}

}
