package com.forumapi.features.answer.web;

import com.forumapi.features.answer.business.AnswerService;
import com.forumapi.features.answer.model.AnswerTO;
import com.forumapi.features.answer.model.AnswerUpdateTO;
import com.forumapi.features.answer.model.AnswerVO;
import com.forumapi.features.answer.usecase.CreateAnswerUseCase;
import com.forumapi.features.answer.usecase.DeleteAnswerUseCase;
import com.forumapi.features.answer.usecase.UpdateAnswerUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final CreateAnswerUseCase createAnswerUseCase;
    private final UpdateAnswerUseCase updateAnswerUseCase;
    private final DeleteAnswerUseCase deleteAnswerUseCase;
    private final AnswerService answerService;

    @Autowired
    public AnswerController(CreateAnswerUseCase createAnswerUseCase,
                            UpdateAnswerUseCase updateAnswerUseCase,
                            DeleteAnswerUseCase deleteAnswerUseCase,
                            AnswerService answerService) {
        this.createAnswerUseCase = createAnswerUseCase;
        this.updateAnswerUseCase = updateAnswerUseCase;
        this.deleteAnswerUseCase = deleteAnswerUseCase;
        this.answerService = answerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma resposta", authorizations = { @Authorization(value="token") })
    public AnswerVO create(@Valid @RequestBody AnswerTO answerTO){
        return createAnswerUseCase.create(answerTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza uma resposta", authorizations = { @Authorization(value="token") })
    public AnswerVO update(@PathVariable Long id, @Valid @RequestBody AnswerUpdateTO answerUpdateTO){
        return updateAnswerUseCase.update(id, answerUpdateTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma resposta", authorizations = { @Authorization(value="token") })
    public void delete(@PathVariable Long id){
        deleteAnswerUseCase.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca uma resposta", authorizations = { @Authorization(value="token") })
    public AnswerVO searchAnswer(@PathVariable Long id){
        return answerService.searchAnswer(id);
    }

}
