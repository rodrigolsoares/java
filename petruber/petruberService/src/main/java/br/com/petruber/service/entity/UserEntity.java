package br.com.petruber.service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_user")
public class UserEntity  implements Serializable {
	 
	private static final long serialVersionUID = -6065232513477887130L;
	
	public UserEntity(){}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dateUpdate;
	
    @Column(nullable=false, length=100) 
	private String name;
    
    @Column(nullable=false, length=100) 
	private String email;
    
    @Column(length=20) 
	private String phoneNumber1;	
    
    @Column(nullable=false, length=50) 
	private String password;


	public Long getId() {
		return id;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: setId 
	 * 
	 * @param id the id to set
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Sets to the given value to
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	

	/**
	 * @author Diego
	 * 
	 * Method Name: getDateUpdate 
	 * 
	 * @return the dateUpdate 
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Returns the value of the attribute dateUpdate
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public Date getDateUpdate() {
		return dateUpdate;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: setDateUpdate 
	 * 
	 * @param dateUpdate the dateUpdate to set
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Sets to the given value to dateUpdate
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: getName 
	 * 
	 * @return the name 
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Returns the value of the attribute name
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public String getName() {
		return name;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: setName 
	 * 
	 * @param name the name to set
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Sets to the given value to name
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: getEmail 
	 * 
	 * @return the email 
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Returns the value of the attribute email
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: setEmail 
	 * 
	 * @param email the email to set
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Sets to the given value to email
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: getPhoneNumber1 
	 * 
	 * @return the phoneNumber1 
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Returns the value of the attribute phoneNumber1
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: setPhoneNumber1 
	 * 
	 * @param phoneNumber1 the phoneNumber1 to set
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Sets to the given value to phoneNumber1
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: getPassword 
	 * 
	 * @return the password  
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Returns the value of the attribute password
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @author Diego
	 * 
	 * Method Name: setPassword 
	 * 
	 * @param password the password to set
	 * 
	 * Create Date: 3 de dez de 2015
	 * 
	 * Description: Sets to the given value to password
	 * 
	 * Manutetion Date: 
	 * 
	 * Manutetion Description:
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
		
}
