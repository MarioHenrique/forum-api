package com.forumapi.features.flags.business;

import com.forumapi.entities.Flag;
import com.forumapi.features.flags.infra.FlagRepository;
import com.forumapi.features.flags.model.FlagFilterTO;
import com.forumapi.features.flags.model.FlagTO;
import com.forumapi.features.flags.model.FlagVO;
import com.forumapi.persistence.SpecificationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class FlagService {

    private final FlagRepository flagRepository;

    @Autowired
    public FlagService(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    @Transactional
    public FlagVO createFlag(FlagTO flagTO){
        Flag flag = new Flag(flagTO.getDescription());
        flagRepository.save(flag);
        return new FlagVO(flag);
    }

    public Page<FlagVO> search(FlagFilterTO flagFilterTO){
        SpecificationQuery<Flag> flagSpec = new SpecificationQuery<>(flagFilterTO);
        Page<Flag> page = flagRepository.findAll(where(flagSpec.like("description", flagFilterTO.getDescription())), flagSpec.getPagination());
        return page.map(FlagVO::new);
    }

}
