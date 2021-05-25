package com.binh.motel.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	@NotBlank(message = "Please enter a code")
	private String code;
	@NotBlank(message = "Please enter name")
	private String name;
	private String slug;
	private int position;
	private String parent;
	private Boolean isVisible;
	private Boolean isEnable;
	private String description;
	@NotBlank(message = "Meta tag title must not be empty")
	private String metaTagTitle;
	private String metaTagDescription;
	private String metaTagKeywords;
}
