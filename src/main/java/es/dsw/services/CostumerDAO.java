package es.dsw.services;

import java.security.SecureRandom;
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
	private boolean isError;

	public CostumerDAO() {

		conn = new MySqlConnection(false);
	}

	public void insertBuyingTicketAndTicketsInBD() {
		
		String codeQR;
		String[] rowSeats = costumer.getButacas().split(";");
		int actualSeat = 0;
		int buyTicketId = -1;
		SecureRandom random = new SecureRandom();

		String sqlForInsert = " INSERT INTO DB_FILMCINEMA.BUYTICKETS_FILM" + "(NAME_BTF, SURNAMES_BTF, EMAIL_BTF,"
				+ " CARDHOLDER_BTF, CARDNUMBER_BTF, MONTHCARD_BTF, YEARCARD_BTF,"
				+ " CCS_CARD_CODE_BTF, TOTALPRICE_BTF, S_ACTIVEROW_BTF," + " S_IDUSER_BTF)" + "VALUES" + "(?, ?, ?,"
				+ "?, ?, ?, ?," + "?, ?, b'1'," + "'1')";
		
		String sqlForInsertTickets = "INSERT INTO DB_FILMCINEMA.TICKET_FILM \r\n"
				+ "(IDSESSION_TKF, DATESESSION_TKF, TIMESESSION_TKF, \r\n"
				+ "SERIALCODE_TKF, YOUNGER_TKF, PRICE_TKF, ROWSEAT_TKF, \r\n" + "IDBUYTICKETS_TKF, S_ACTIVEROW_TKF,\r\n"
				+ " S_IDUSER_TKF) \r\n" + "VALUES \r\n" + "(?, ?, ?, ?, ?, ?, ?, ?, b'1',\r\n"
				+ "'1');";
		
		this.isError = false;

		try {

			conn.open();

			if (conn.getConnection() != null) {
				if (!conn.getConnection().isClosed()) {
					PreparedStatement objStament = conn.getConnection().prepareStatement(sqlForInsert,
							Statement.RETURN_GENERATED_KEYS);
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
					if (rs.next()) {
						buyTicketId = rs.getInt(1);
					}
					
					if (buyTicketId != -1) {

						for (int i = 0; i < costumer.getNumNinios(); i++) {
							
							long rdm = random.nextLong(0, 10_000_000_000_000_000L);
							codeQR = String.format("%016d", rdm);

							objStament = conn.getConnection().prepareStatement(sqlForInsertTickets);
							objStament.setInt(1, costumer.getPeliculaChosen().getSesion());
							objStament.setString(2, costumer.getDateString());
							objStament.setString(3, costumer.getHourString());
							objStament.setString(4, codeQR);
							objStament.setByte(5, (byte) 1);
							objStament.setFloat(6, 3.5f);
							objStament.setString(7, rowSeats[i]);
							objStament.setInt(8, buyTicketId);
							
							actualSeat = i + 1;
							costumer.getCodesQR().add(codeQR);

							objStament.execute();


						}

						for (int i = 0; i < costumer.getNumAdultos(); i++) {
							
							long rdm = random.nextLong(0, 10_000_000_000_000_000L);
							codeQR = String.format("%016d", rdm);

							objStament = conn.getConnection().prepareStatement(sqlForInsertTickets);
							objStament.setInt(1, costumer.getPeliculaChosen().getSesion());
							objStament.setString(2, costumer.getDateString());
							objStament.setString(3, costumer.getHourString());
							objStament.setString(4, codeQR);
							objStament.setByte(5, (byte) 0);
							objStament.setFloat(6, (float) costumer.getPeliculaChosen().getPrecio());
							objStament.setString(7, rowSeats[actualSeat]);
							objStament.setInt(8, buyTicketId);

							actualSeat++;
							costumer.getCodesQR().add(codeQR);

							objStament.execute();

						}
						
					conn.getConnection().commit();
					
					}else {
						this.msgError = "Ha ocurrido el error " + this.msgError + " o no se registró la compra";
						this.isError = true;
						try {
							conn.getConnection().rollback();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
				} else {
					this.msgError = conn.msgError();
					this.isError = true;
				}
				
			} else {
				this.msgError = conn.msgError();
				this.isError = true;
			}
			
		} catch (SQLException ex) {
			this.msgError = "No se ha realizado la operación por un error interno. Intentelo de nuevo";
			this.isError = true;
			try {
				conn.getConnection().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			conn.close();
		}

	}

	

	public String getMsgError() {
		return msgError;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	

}
