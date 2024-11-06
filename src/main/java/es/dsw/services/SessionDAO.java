package es.dsw.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Costumer;

@Service
public class SessionDAO {
	
	private Costumer costumer;
	private MySqlConnection connection;
	private List<String> butacasOcuppied;
	
	public SessionDAO() {
		super();
		butacasOcuppied = new ArrayList<>();
		connection = new MySqlConnection();
	}
	
	public List<String> listOfButacasOcuppied(){
		
		String strToSearchButacasOcuppied = "select ROWSEAT_TKF from ticket_film\n"
				+ " inner join session_film on session_film.IDSESSION_SSF = ticket_film.IDSESSION_TKF\n"
				+ " where \n"
				+ " IDSESSION_TKF = " + costumer.getPeliculaChosen().getSesion() + " and DATESESSION_TKF = '" + costumer.getDateString() + "'\n"
				+ " and TIMESESSION_TKF = '" + costumer.getHourString() + "' and IDFILM_SSF = " 
				+ costumer.getPeliculaChosen().getId() + " and S_ACTIVEROW_SSF = 1;";
		
		butacasOcuppied.clear();
		
		
		try {
			
		connection.open();
		
		ResultSet rs = connection.executeSelect(strToSearchButacasOcuppied);
		
			while (rs.next()) {
				
				butacasOcuppied.add(rs.getString(1)); 
				
			}
		} catch (SQLException e) {
			System.out.println(connection.msgError());
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return butacasOcuppied;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
	
	

}
