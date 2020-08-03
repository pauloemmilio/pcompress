package com.paulo.pcompress.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulo.pcompress.entity.RequestFile;
import com.paulo.pcompress.entity.ResponseFile;
import com.paulo.pcompress.service.CompressService;

@RestController
@RequestMapping("/compress")
public class CompressController {

	@Autowired private CompressService compressService;
	
	@PostMapping
	public ResponseFile compress(@RequestBody @Valid RequestFile requestFile) {
		return compressService.compress(requestFile);
	}
}
