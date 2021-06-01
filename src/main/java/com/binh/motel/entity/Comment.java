package com.binh.motel.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "commented_by", nullable = false)
	private String commentedBy;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "rating", nullable = true)
	private int raring;
	
	@Column(name = "likes", nullable = true)
	private int likes;
	
	@Column(name = "approved", columnDefinition = "boolean default false")
	private boolean approved;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
	private MotelRoom room;
}
