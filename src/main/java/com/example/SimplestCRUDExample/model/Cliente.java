package com.example.SimplestCRUDExample.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nombres;
	@NotNull
	private String primerApellido;
	@NotNull
	private String segundoApellido;
	@NotNull
	private String numeroIdentificacion;
	private String direccion;
	@NotNull
	private String celular;
	@NotNull
	private String password;
	@NotNull
	private String email;
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;

}
