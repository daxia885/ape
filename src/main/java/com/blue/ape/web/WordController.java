package com.blue.ape.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blue.ape.config.RequestParam;
import com.blue.ape.service.WordService;

@RestController
public class WordController {
	
	@Autowired
	private WordService wordService;
	
	@PostMapping("pageWordList")
	public Object pageWordList(@RequestBody RequestParam param) {
		return wordService.pageWordList(param.getPageNumber(), param.getPageSize());
	}

}
