package br.com.devjony.firstprojectrefactoring.service;

import br.com.devjony.firstprojectrefactoring.domain.ExamDomain;
import br.com.devjony.firstprojectrefactoring.repository.ExamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    Logger logger = LoggerFactory.getLogger(ExamService.class);

    @Autowired
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<ExamDomain> findAll() {
        logger.info("Finding all [ExamDomain] on Database");

        List<ExamDomain> allExamDomain = examRepository.findAll();

        logger.info("Returning [ExamDomain] List");
        return allExamDomain;
    }

    public ExamDomain save(ExamDomain examDomain) {
        logger.info("Saving [ExamDomain] on Database");

        ExamDomain examDomainFromDb = examRepository.save(examDomain);

        logger.info("Returning [ExamDomain] saved on Database");
        return examDomainFromDb;
    }
}
