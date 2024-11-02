package es.dsw.services;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Costumer;

public class CostumerDAO {
	
	private MySqlConnection conn;
	private Costumer costumer;
	
	
	public CostumerDAO(Costumer costumer) {
		
		this.costumer = costumer;
		conn = new MySqlConnection(false);
	}
	
	public void insertBuyingTicket() {
		
		String sqlForInsert = " INSERT INTO DB_FILMCINEMA.BUYTICKETS_FILM"
				+ "(IDBUYTICKETS_BTF, NAME_BTF, SURNAMES_BTF, EMAIL_BTF,"
				+ " CARDHOLDER_BTF, CARDNUMBER_BTF, MONTHCARD_BTF, YEARCARD_BTF,"
				+ " CCS_CARD_CODE_BTF, TOTALPRICE_BTF, S_ACTIVEROW_BTF, S_INSERTDATE_BTF,"
				+ " S_UPDATEDATE_BTF, S_IDUSER_BTF)"
				+ "VALUES"
				+ "(NULL," + "'" + costumer.getNombre()  +"','" + costumer.getApellido() + "','" + costumer.getEmail() + "',"
				+ "'" + costumer.getTitularTarjeta() + "','" + costumer.getNumTarjeta() + "','" + costumer.getMesCaducaTarjeta() + "','" + costumer.getAnioCaducaTarjeta() + "',"
				+ "'344', '13', b'1', NULL, \r\n"
				+ "NULL, '1')";
	}

}
