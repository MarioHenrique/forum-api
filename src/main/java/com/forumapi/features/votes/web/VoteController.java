package com.forumapi.features.votes.web;

import com.forumapi.features.votes.model.VoteTO;
import com.forumapi.features.votes.model.VoteUpdateTO;
import com.forumapi.features.votes.model.VoteVO;
import com.forumapi.features.votes.usecase.CreateVoteUseCase;
import com.forumapi.features.votes.usecase.DeleteVoteUserCase;
import com.forumapi.features.votes.usecase.UpdateVoteUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final CreateVoteUseCase createVoteUseCase;
    private final UpdateVoteUseCase updateVoteUseCase;
    private final DeleteVoteUserCase deleteVoteUserCase;

    @Autowired
    public VoteController(CreateVoteUseCase createVoteUseCase,
                          UpdateVoteUseCase updateVoteUseCase,
                          DeleteVoteUserCase deleteVoteUserCase) {
        this.createVoteUseCase = createVoteUseCase;
        this.updateVoteUseCase = updateVoteUseCase;
        this.deleteVoteUserCase = deleteVoteUserCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um voto", authorizations = { @Authorization(value="token") })
    public VoteVO create(@Valid @RequestBody VoteTO voteTO){
        return createVoteUseCase.create(voteTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera um voto", authorizations = { @Authorization(value="token") })
    public VoteVO update(@PathVariable Long id, @Valid @RequestBody VoteUpdateTO voteUpdateTO){
        return updateVoteUseCase.update(id, voteUpdateTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um voto", authorizations = { @Authorization(value="token") })
    public void delete(@PathVariable Long id){
        deleteVoteUserCase.delete(id);
    }

}
