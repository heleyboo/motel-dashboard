package com.binh.motel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "province")
public class Province {
	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;
	
	@Column(name = "slug")
	private String slug;

	@Column(name = "type")
	private String type;
	
	@Column(name = "name_with_type")
	private String nameWithType;

	@JsonIgnore
	@OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
	private List<District> districts;

}
