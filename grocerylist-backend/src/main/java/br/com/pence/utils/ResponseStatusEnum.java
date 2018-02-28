package br.com.pence.utils;

public enum ResponseStatusEnum {

	CREATED_201(201, "Created"),
	BAD_REQUEST_400(400, "Bad Request");
	
	private Integer code;
	private String description;

	private ResponseStatusEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
