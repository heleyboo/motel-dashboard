package com.binh.motel.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "uuid", unique = true)
	private String uuid;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "host_user")
	private User host;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_user")
	private User client;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
	private MotelRoom motelRoom;
	
	@JsonIgnore
	@OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MessageLine> messageLines;
}
