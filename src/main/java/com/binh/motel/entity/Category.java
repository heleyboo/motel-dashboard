package com.binh.motel.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
@Table(name = "category")
public class Category {
	@NotEmpty
	@NotBlank
	@Id
	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@NotEmpty
	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@Column(name = "slug")
	private String slug;

	@JsonIgnore
	@Column(name = "position")
	private int position;

	@JsonIgnore
	@Column(name = "level")
	private int level;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "code", nullable = true)
    private Category parent;

	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Category> children;

	@JsonIgnore
	@Column(name = "is_visible", insertable = false)
	private Boolean isVisible;

	@JsonIgnore
	@Column(name = "is_enable", insertable = false)
	private Boolean isEnable;

	@JsonIgnore
	@Column(name = "description")
	private String description;
	
	@JsonIgnore
	@Column(name = "meta_tag_title")
	private String metaTagTitle;
	
	@JsonIgnore
	@Column(name = "meta_tag_description")
	private String metaTagDescription;
	
	@JsonIgnore
	@Column(name = "meta_tag_keyword")
	private String metaTagKeywords;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<MotelRoom> rooms;
	
}