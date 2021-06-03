package com.binh.motel.dto.response;

import java.util.TreeMap;

import org.springframework.data.domain.Page;

import com.binh.motel.dto.Filter;
import com.binh.motel.util.CustomStringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageResponse<T> {
	private Page<T> pageData;
	private Filter<T> filter;

	public TreeMap<Integer, String> getPages() {
		TreeMap<Integer, String> pages = new TreeMap<Integer, String>();
		int totalPages = pageData.getTotalPages();
		int currentPage = CustomStringUtils.parseIntegerOrZero(filter.getPageNum()) + 1;
		
		
		if (totalPages <= 5) {
			for (int i = 1; i <= totalPages; i++) {
				pages.put(i, filter.toString());
			}
		} else {
			if (currentPage <= 3) {
				for (int i = 1; i <= 5; i++) {
					pages.put(i, filter.toString());
				}
				pages.put(totalPages, filter.toString());
			} else {
				if (!pages.containsKey(1)) {					
					pages.put(1, filter.toString());
				}
				for (int i = 1; i <= 5; i++) {
					if (i + currentPage - 3 <= totalPages) {						
						pages.put(i + currentPage - 3, filter.toString());
					}
				}
				if (!pages.containsKey(totalPages)) {					
					pages.put(totalPages, filter.toString());
				}
			}
		}
		
		return pages;
	}

}
