package com.binh.motel.data.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.binh.motel.dto.Filter;

public class FilterPageRequest extends PageRequest {

	private static final long serialVersionUID = 6748844766251256729L;

	protected FilterPageRequest(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	@SuppressWarnings("rawtypes")
	public static PageRequest of(Filter filter) {
		int pageNum;
		int pageSize;
		if (null == filter.getPageNum() || !isInteger(filter.getPageNum())) {
			pageNum = 0;
		} else {
			pageNum = Integer.parseInt(filter.getPageNum()) - 1;
		}
		if (null == filter.getPageSize() || !isInteger(filter.getPageSize())) {
			pageSize = 10;
		} else {
			pageSize = Integer.parseInt(filter.getPageSize());
		}
		
		if (pageNum <= 0) {
			pageNum = 0;
		}

		return of(pageNum, pageSize, Sort.unsorted());
	}

	public static boolean isInteger(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
