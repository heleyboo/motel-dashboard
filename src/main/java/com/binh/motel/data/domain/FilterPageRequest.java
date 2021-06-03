package com.binh.motel.data.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.binh.motel.dto.Filter;

public class FilterPageRequest extends PageRequest {

	private static final long serialVersionUID = 6748844766251256729L;

	protected FilterPageRequest(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	private static int pageNum;
	private static int pageSize;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static PageRequest of(Filter filter) {
		calculatePageData(filter);
		Sort sort = filter.orders() == null ? Sort.unsorted() : Sort.by(filter.orders());
		return of(pageNum, pageSize, sort);
	}

	@SuppressWarnings("rawtypes")
	public static PageRequest of(Filter filter, Sort sort) {
		calculatePageData(filter);
		return of(pageNum, pageSize, sort);
	}

	private static void calculatePageData(@SuppressWarnings("rawtypes") Filter filter) {

		if (null == filter.getPageNum() || !isInteger(filter.getPageNum())) {
			pageNum = 0;
		} else {
			pageNum = Integer.parseInt(filter.getPageNum()) - 1;
		}
		if (null == filter.getPageSize() || !isInteger(filter.getPageSize())) {
			pageSize = 12;
		} else {
			pageSize = Integer.parseInt(filter.getPageSize());
		}

		if (pageNum <= 0) {
			pageNum = 0;
		}
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
