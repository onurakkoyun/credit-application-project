package com.definex.finalcase.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@NotBlank(message = "Kimlik numarası boş olamaz")
	@Size(min = 11, max = 11, message = "Kimlik numarası 11 haneli olmalıdır")
	@Column(name = "identity_number",  unique = true)
	private String identityNumber;
	
	@NotNull
	@NotBlank(message = "Ad boş olamaz")
	@Size(min = 2, message = "Ad en az 2 haneli olmalıdır")
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull
	@NotBlank(message = "Soyad boş olamaz")
	@Size(min = 2, message = "Ad en az 2 haneli olmalıdır")
	@Column(name = "last_name")
	private String lastName;
	
	@PositiveOrZero(message = "Gelir negatif bir değer olamaz!")
	@Column(name = "monthly_income")
	private int monthlyIncome;
	
	@NotNull
	@NotBlank(message = "Telefon numarası boş olamaz")
	@Pattern(regexp ="[0-9\\s]{12}", message = "Telefon numarası uygun formatda değil, doğru format : 90555xxxxxxx")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "birthday")
    private LocalDate birthday;
	
	@PositiveOrZero(message = "Teminat negatif bir değer olamaz!")
	@Column(name = "assurance")
	private int assurance;
	
	@Column(name = "credit_score")
	private int creditScore;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Credit> credits;

}
