package com.forumapi.features.info.web;

import com.forumapi.features.info.business.InfoService;
import com.forumapi.features.info.model.InfoFilter;
import com.forumapi.features.info.model.UserInfoVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/infos")
public class InfoController {

    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Realiza a busca de usuarios e suas informações complementars" , authorizations = { @Authorization(value="token") })
    public List<UserInfoVO> search(InfoFilter infoFilter){
        return infoService.search(infoFilter);
    }


}
