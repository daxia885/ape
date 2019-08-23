package com.blue.ape.web;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.blue.ape.config.RequestParam;
import com.blue.ape.entity.Word;
import com.blue.ape.service.WordService;

@Controller
public class WordController {
	
	@Autowired
	private WordService wordService;
	
	@RequestMapping("index")
	public String index(Model model) {
		Object wordList = wordService.pageWordList(1, 20);
		model.addAttribute("wordList", wordList);
		return "index";
	}
	
	@PostMapping("pageWordList")
	@ResponseBody
	public Object pageWordList(@RequestBody RequestParam param) {
		Object wordList = wordService.pageWordList(param.getPageNumber(), param.getPageSize());
		return wordList;
	}

	@PostMapping("getEnglishVoice")
	@ResponseBody
	public String getEnglishVoice(@RequestBody Word word) {
		if (word.getId() == null || StringUtils.isEmpty(word.getEnglish())) {
			return null;
		}
		byte[] voiceBytes = wordService.saveEnglishVoice(word.getId(), word.getEnglish());
		String base64Voice = Base64.getEncoder().encodeToString(voiceBytes);
		System.out.println(base64Voice);
		return base64Voice;
	}
}
