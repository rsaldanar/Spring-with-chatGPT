package com.example.springwithchatgpt.controller;

import com.example.springwithchatgpt.entity.ResponseDTO;
import io.github.flashvayne.chatgpt.dto.image.ImageFormat;
import io.github.flashvayne.chatgpt.dto.image.ImageSize;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @project: Spring-with-chatGPT
 * @author: Rafael Saldana
 * @date: 9/20/2023
 * @email: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final ChatgptService chatgptService;

    @GetMapping("/send")
    public ResponseDTO<String> sendQuestion(@RequestParam String question) {
        log.info("question is: {}", question);
        String responseQuestion = chatgptService.sendMessage(question);
        log.info("responseQuestion is: {}", responseQuestion);
        return ResponseDTO.success(responseQuestion);
    }

    @PostMapping("/imageGenerate")
    public String imageGenerate(@RequestParam String var1 ){
        return chatgptService.imageGenerate(var1);
    }

}
