package com.paulo.pcompress.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestFile {

	@NotEmpty(message = "Base64 file is required")
	private String base64File;
	@NotNull(message = "Compress factor is required")
	@Min(value = 0, message = "Compress factor must be greater than 0")
	@Max(value = 1, message = "Compress factor must be lower than 1")
	private Double compressFactor;
	
	public RequestFile() {}
	
	public RequestFile(String base64File, Double compressFactor) {
		this.base64File = base64File;
		this.compressFactor = compressFactor;
	}

	public String getBase64File() {
		return base64File;
	}

	public void setBase64File(String base64File) {
		this.base64File = base64File;
	}

	public Double getCompressFactor() {
		return compressFactor;
	}

	public void setCompressFactor(Double compressFactor) {
		this.compressFactor = compressFactor;
	}
}
