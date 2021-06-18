package com.binh.motel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message_lines")
public class MessageLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "content", nullable = false)
	private String content;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "message_id", nullable = false)
	private Message message;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "last_modified_date")
	private Date lastModifiedDate;

}
