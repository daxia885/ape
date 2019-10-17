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
import com.blue.ape.entity.Collect;
import com.blue.ape.entity.Process;
import com.blue.ape.entity.Word;
import com.blue.ape.service.CollectService;
import com.blue.ape.service.ProcessService;
import com.blue.ape.service.WordService;

@Controller
public class WordController {
	
	@Autowired
	private WordService wordService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private ProcessService processService;
	
	@RequestMapping("index")
	public String index(Model model) {
//		Object wordList = wordService.pageWordList(1, 20);
		Object wordList = wordService.pageWordListFromProcess(1, 20, 1);
		model.addAttribute("memberId", 1);
		model.addAttribute("wordList", wordList);
		return "index";
	}
	
	@RequestMapping("process")
	public String process(Model model) {
		return "process";
	}
	/**
	 * 获取单词列表分页
	 * @param param
	 * @return
	 */
	@PostMapping("pageWordList")
	@ResponseBody
	public Object pageWordList(@RequestBody RequestParam param) {
		Object wordList = wordService.pageWordList(param.getPageNumber(), param.getPageSize());
		return wordList;
	}
	
	/**
	 * 获取指定单词的发音（调用百度接口）
	 * @param word
	 * @return
	 */
	@PostMapping("getEnglishVoice")
	@ResponseBody
	public String getEnglishVoice(@RequestBody Word word) {
		if (word.getId() == null || StringUtils.isEmpty(word.getEnglish())) {
			return null;
		}
		byte[] voiceBytes = wordService.saveEnglishVoice(word.getId(), word.getEnglish());
		String base64Voice = Base64.getEncoder().encodeToString(voiceBytes);
		return base64Voice;
	}
	
	/**
	 * 标记单词已学习
	 * @param process
	 * @return
	 */
	@PostMapping("setWordStudied")
	@ResponseBody
	public String setWordStudied(@RequestBody Process process) {
		if (null == process.getMember() || null == process.getWord()) {
			return "error";
		}
		processService.addProcess(process);
		return "success";
	}
	
	/**
	 * 收藏与取消收藏
	 * @param collect
	 * @return
	 */
	@PostMapping("collectWord")
	@ResponseBody
	public String collectWord(@RequestBody Collect collect) {
		if (null == collect.getMember() || null == collect.getWord()) {
			return "error";
		}
		if (collect.isCollect()) {
			collectService.addCollect(collect);
		} else {
			collectService.deleteCollect(collect);
		}
		return "success";
	}
	
}
