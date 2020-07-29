package com.forumapi.features.questions.web;

import com.forumapi.features.questions.business.QuestionService;
import com.forumapi.features.questions.model.QuestionTO;
import com.forumapi.features.questions.model.QuestionVO;
import com.forumapi.features.questions.usecase.CreateQuestionUseCase;
import com.forumapi.features.questions.usecase.DeleteQuestionUseCase;
import com.forumapi.features.questions.usecase.UpdateQuestionUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;
    private final QuestionService questionService;
    private final UpdateQuestionUseCase updateQuestionUseCase;
    private final DeleteQuestionUseCase questionUseCase;

    @Autowired
    public QuestionController(CreateQuestionUseCase createQuestionUseCase,
                              QuestionService questionService,
                              UpdateQuestionUseCase updateQuestionUseCase,
                              DeleteQuestionUseCase questionUseCase) {
        this.createQuestionUseCase = createQuestionUseCase;
        this.questionService = questionService;
        this.updateQuestionUseCase = updateQuestionUseCase;
        this.questionUseCase = questionUseCase;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca uma questão", authorizations = { @Authorization(value="token") })
    public QuestionVO searchQuestion(@PathVariable Long id){
        return questionService.searchQuestion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma questão", authorizations = { @Authorization(value="token") })
    public QuestionVO createQuestion(@Valid @RequestBody QuestionTO questionTO){
        return this.createQuestionUseCase.create(questionTO);
    }

    @PostMapping("/{id}/resolve")
    @PreAuthorize("hasAuthority('MODERATOR')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Marca uma questão como resolvida",
            notes = "Apenas usuários moderadores podem realizar essa operação",
            authorizations = { @Authorization(value="token") })
    public QuestionVO resolveQuestion(@PathVariable Long id){
        return questionService.resolve(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera uma questão", authorizations = { @Authorization(value="token") })
    public QuestionVO updateQuestion(@Valid @RequestBody QuestionTO questionTO, @PathVariable Long id){
        return updateQuestionUseCase.update(id, questionTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma questão", authorizations = { @Authorization(value="token") })
    public void deleteQuestion(@PathVariable Long id){
        questionUseCase.delete(id);
    }

}
