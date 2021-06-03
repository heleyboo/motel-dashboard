package com.binh.motel.dto;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Filter<T> {
	protected String pageNum;
	protected String pageSize;
	protected String search;
	public abstract Specification<T> buildSpec();
	public List<Order> orders() {
		return null;
	}
}
