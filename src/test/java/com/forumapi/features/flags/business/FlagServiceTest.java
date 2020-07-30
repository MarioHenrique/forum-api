package com.forumapi.features.flags.business;

import com.forumapi.entities.Flag;
import com.forumapi.features.flags.infra.FlagRepository;
import com.forumapi.features.flags.model.FlagFilterTO;
import com.forumapi.features.flags.model.FlagTO;
import com.forumapi.features.flags.model.FlagVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlagServiceTest {

    @InjectMocks
    private FlagService flagService;

    @Mock
    private FlagRepository flagRepository;

    @Captor
    private ArgumentCaptor<Flag> flagCreated;

    @Captor
    private ArgumentCaptor<Specification<Flag>> spec;

    @Captor
    private ArgumentCaptor<Pageable> filterPagination;

    @Captor
    private ArgumentCaptor<String> queryFilter;

    @Spy
    private Root<Flag> rootSpy;

    @Spy
    private CriteriaBuilder criteriaBuilder;

    @Spy
    private CriteriaQuery<Flag> criteriaQuery;

    @Before
    public void setUp(){

    }

    @Test
    public void createFlag(){
        String flagDescrition = "Java 10";
        FlagTO flag = givingAFlagWithDescription(flagDescrition);
        whenSave(flag);
        thenCreateFlagWithDescription(flagDescrition);
    }

    private void thenCreateFlagWithDescription(String description){
        verify(flagRepository).save(flagCreated.capture());
        Flag flag = flagCreated.getValue();
        assertNotNull(flag);
        assertEquals(description, flag.getDescription());
    }

    private void whenSave(FlagTO flag){
        flagService.createFlag(flag);
    }

    private FlagTO givingAFlagWithDescription(String flagDescrition){
        FlagTO flag = new FlagTO();
        flag.setDescription(flagDescrition);
        return flag;
    }

    @Test
    public void returnEmptyWhenSearchFlags(){
        String description = "Java 10";
        int page = 1;
        int pageSize = 10;

        FlagFilterTO flagFilterTO = givingAFlagFilter(description, page, pageSize);
        givingNoFlagsForFilter();
        Page<FlagVO> flags = whenSearch(flagFilterTO);
        thenSearchWithPagination(description, page, pageSize);
        thenReturnEmpty(flags);
    }

    private void thenSearchWithPagination(String description, int page, int pageSize) {
        verify(flagRepository).findAll(spec.capture(), filterPagination.capture());

        Specification<Flag> specification = spec.getValue();
        specification.toPredicate(rootSpy, criteriaQuery, criteriaBuilder);
        verify(rootSpy).get(queryFilter.capture());
        verify(criteriaBuilder).like(any(), queryFilter.capture());

        List<String> specParams = queryFilter.getAllValues();

        assertEquals("description", specParams.get(0));
        assertEquals("%"+description+"%", specParams.get(1));

        Pageable pageable = filterPagination.getValue();
        assertEquals(page - 1, pageable.getPageNumber());
        assertEquals(pageSize, pageable.getPageSize());
    }

    @Test
    public void returnFlagsWhenSearched(){
        String description = "Java 10";
        int page = 1;
        int pageSize = 10;

        FlagFilterTO flagFilterTO = givingAFlagFilter(description, page, pageSize);
        givingFlagsForFilter();
        Page<FlagVO> flags = whenSearch(flagFilterTO);
        thenSearchWithPagination(description, page, pageSize);
        thenReturnFlag(flags, description);
    }

    private void thenReturnFlag(Page<FlagVO> flags, String description) {
        List<FlagVO> flagsVO = flags.getContent();
        FlagVO flagVO = flagsVO.get(0);

        assertEquals(1, flagsVO.size());
        assertEquals(description, flagVO.getDescription());
    }

    private void thenReturnEmpty(Page<FlagVO> flags) {
        assertTrue(flags.getContent().isEmpty());
    }

    private void givingFlagsForFilter(){
        givingFlags(createFlagList());
    }

    private void givingNoFlagsForFilter(){
        givingFlags(Collections.emptyList());
    }

    private void givingFlags(List<Flag> flags){
        when(flagRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl(flags));
    }

    private List<Flag> createFlagList(){
        Flag flag = new Flag("Java 10");
        return Arrays.asList(flag);
    }

    private Page<FlagVO> whenSearch(FlagFilterTO flagFilterTO) {
        return flagService.search(flagFilterTO);
    }

    public FlagFilterTO givingAFlagFilter(String description, Integer page, Integer pageSize){
        FlagFilterTO flagFilterTO = new FlagFilterTO();
        flagFilterTO.setDescription(description);
        flagFilterTO.setPage(page);
        flagFilterTO.setSize(pageSize);
        return flagFilterTO;
    }


}