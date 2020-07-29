package com.forumapi.features.flags.infra;

import com.forumapi.entities.Flag;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagRepository extends CrudRepository<Flag, Long>, JpaSpecificationExecutor<Flag> {

}
