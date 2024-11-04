package es.dsw.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Costumer;

@Service
public class CostumerDAO {
	
	private MySqlConnection conn;
	private Costumer costumer;
	private String msgError;
	private ResultSet rs;
	
	
	public CostumerDAO() {
		
		conn = new MySqlConnection(false);
	}
	
	public void insertBuyingTicket() {
		
		String sqlForInsert = " INSERT INTO DB_FILMCINEMA.BUYTICKETS_FILM"
				+ "(NAME_BTF, SURNAMES_BTF, EMAIL_BTF,"
				+ " CARDHOLDER_BTF, CARDNUMBER_BTF, MONTHCARD_BTF, YEARCARD_BTF,"
				+ " CCS_CARD_CODE_BTF, TOTALPRICE_BTF, S_ACTIVEROW_BTF,"
				+ " S_IDUSER_BTF)"
				+ "VALUES" 
				+ "(?, ?, ?,"
				+ "?, ?, ?, ?," 
				+ "?, ?, b'1'," 
				+ "'1')";
		
		try {
			
			conn.open();
			
			 if (conn != null) {
				if (!conn.getConnection().isClosed()) {
					PreparedStatement objStament = conn.getConnection().prepareStatement(sqlForInsert, Statement.RETURN_GENERATED_KEYS);	
					objStament.setString(1, costumer.getNombre());
					objStament.setString(2, costumer.getApellido());
					objStament.setString(3, costumer.getEmail());
					objStament.setString(4, costumer.getTitularTarjeta());
					objStament.setString(5, costumer.getNumTarjeta());
					objStament.setString(6, costumer.getMesCaducaTarjeta());
					objStament.setString(7, costumer.getAnioCaducaTarjeta());
					objStament.setString(8, costumer.getCvvTarjeta());
					objStament.setDouble(9, costumer.getPrecioTotalVentaEntradas());
					
					objStament.execute();
					rs = objStament.getGeneratedKeys();
					
					conn.getConnection().commit();
				}
				else {
					this.msgError = conn.msgError();
				}
			 }
			 else {
				 this.msgError = conn.msgError();
			}
		   }
		   catch (SQLException ex) {
			   this.msgError = "No se ha realizado la operación por un error interno. Intentelo de nuevo";
			   try {
				conn.getConnection().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   }finally {
			   conn.close();
		   }
		
	}
	
	public void insertTicketsInBD() {
		
		String sqlForInsertTickets = "INSERT INTO DB_FILMCINEMA.TICKET_FILM \r\n"
				+ "(IDSESSION_TKF, DATESESSION_TKF, TIMESESSION_TKF, \r\n"
				+ "SERIALCODE_TKF, YOUNGER_TKF, PRICE_TKF, ROWSEAT_TKF, \r\n"
				+ "IDBUYTICKETS_TKF, S_ACTIVEROW_TKF,\r\n"
				+ " S_IDUSER_TKF) \r\n"
				+ "VALUES \r\n"
				+ "(NULL, '3', '2022-09-30', '17:30:00', \r\n"
				+ "'1000000012300302', b'0', '6.5', 'F11B4', \r\n"
				+ "'2', b'1', current_timestamp(), current_timestamp(), \r\n"
				+ "'1');";
		
		int buyTicketId;
		
		try {
			if(rs.next()) {
				buyTicketId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String mensajeErrorTransaccion() {
		
		return msgError;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
	
	

}
