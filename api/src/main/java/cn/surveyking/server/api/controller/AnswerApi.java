package cn.surveyking.server.api.controller;

import cn.surveyking.server.api.domain.dto.AnswerQuery;
import cn.surveyking.server.api.domain.model.Answer;
import cn.surveyking.server.api.service.AnswerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author javahuang
 * @date 2021/8/6
 */
@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerApi {

	private final AnswerService answerService;

	@GetMapping
	public IPage<Answer> listAnswer(AnswerQuery query) {
		return answerService.listAnswer(query);
	}

	@GetMapping("/{id}")
	public Answer getAnswer(@PathVariable String id, AnswerQuery query) {
		query.setId(id);
		return answerService.getAnswer(query);
	}

	@PostMapping
	public void saveAnswer(@RequestBody Answer answer, HttpServletRequest request) {
		answerService.saveAnswer(answer, request);
	}

	@PatchMapping
	public void updateAnswer(@RequestBody Answer answer) {
		answerService.updateAnswer(answer);
	}

	@DeleteMapping
	public void deleteAnswer(String[] ids) {
		answerService.deleteAnswer(ids);
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> download(String shortId) {
		return answerService.download(shortId);
	}

}