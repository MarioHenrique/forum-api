package com.forumapi.features.flags.web;

import com.forumapi.features.flags.business.FlagService;
import com.forumapi.features.flags.model.FlagFilterTO;
import com.forumapi.features.flags.model.FlagTO;
import com.forumapi.features.flags.model.FlagVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/flags")
public class FlagController {

    private final FlagService flagService;

    @Autowired
    public FlagController(FlagService flagService) {
        this.flagService = flagService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma flag", authorizations = { @Authorization(value="token") })
    public FlagVO createFlag(@Valid @RequestBody FlagTO flagTO){
        return flagService.createFlag(flagTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca uma flag", authorizations = { @Authorization(value="token") })
    public Page<FlagVO> searchFlags(@Valid FlagFilterTO flagFilterTO){
        return flagService.search(flagFilterTO);
    }

}
