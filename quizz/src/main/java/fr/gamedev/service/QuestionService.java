package fr.gamedev.service;

import fr.gamedev.repository.QuestionRepository;
import fr.gamedev.repository.TagRepository;

public class QuestionService {

    /**
     * . questionRepository
     */
    private final QuestionRepository questionRepository;

    /**
     * . tagRepository
     */
    private final TagRepository tagRepository;


    public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
    }
}
