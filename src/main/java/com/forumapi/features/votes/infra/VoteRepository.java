package com.forumapi.features.votes.infra;

import com.forumapi.entities.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

}
