package net.dg.springbootmongodbcrud.service;

import net.dg.springbootmongodbcrud.models.Tutorial;

import java.util.List;
import java.util.Optional;

public interface TutorialService {

    Tutorial saveNewTutorial(Tutorial tutorial);
    void deleteTutorialById(String id);
    List<Tutorial> findAllTutorials();
    Optional<Tutorial> findTutorialById(String id);
    List<Tutorial> findByTitleContaining(String title);
    List<Tutorial> findByPublished(boolean published);
}
