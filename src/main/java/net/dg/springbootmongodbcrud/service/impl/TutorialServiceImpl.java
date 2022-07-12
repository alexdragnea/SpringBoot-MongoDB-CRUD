package net.dg.springbootmongodbcrud.service.impl;

import lombok.AllArgsConstructor;
import net.dg.springbootmongodbcrud.models.Tutorial;
import net.dg.springbootmongodbcrud.repository.TutorialRepository;
import net.dg.springbootmongodbcrud.service.TutorialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;

    @Override
    public Tutorial saveNewTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @Override
    public void deleteTutorialById(String id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public List<Tutorial> findAllTutorials() {
        return tutorialRepository.findAll();
    }

    @Override
    public Optional<Tutorial> findTutorialById(String id) {
        return tutorialRepository.findById(id);
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return tutorialRepository.findByPublished(published);
    }
}
