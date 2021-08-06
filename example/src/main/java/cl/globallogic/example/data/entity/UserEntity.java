package cl.globallogic.example.data.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "user")
public class UserEntity {
	
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID id; 
	
	private String name;
	
	@Column(unique = true, nullable = false)
	@Email(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$",  message = "El correo tiene un formato incorrecto")
	private String email;
	
	//TODO: La expresion regular le falta.
	@Pattern(regexp = "^((?:[A-Z]{1})(?:[a-z]*\\d){2}[a-z]*$)", message = "La password tiene un formato incorrecto")
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private List<PhoneEntity> phones;
	
	private LocalDateTime created;
	private LocalDateTime modified;
	
	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	@Column(length = 500)
	private String token; 
	private boolean isactive;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<PhoneEntity> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEntity [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phones=");
		builder.append(phones);
		builder.append(", created=");
		builder.append(created);
		builder.append(", modified=");
		builder.append(modified);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append(", token=");
		builder.append(token);
		builder.append(", isactive=");
		builder.append(isactive);
		builder.append("]");
		return builder.toString();
	}
	 

}
