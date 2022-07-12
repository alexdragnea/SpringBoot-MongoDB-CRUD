package net.dg.springbootmongodbcrud.controller;

import lombok.AllArgsConstructor;
import net.dg.springbootmongodbcrud.models.Tutorial;
import net.dg.springbootmongodbcrud.repository.TutorialRepository;
import net.dg.springbootmongodbcrud.service.TutorialService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TutorialController {
    private static final Logger logger = LogManager.getLogger(TutorialController.class);

    private TutorialService tutorialService;

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        logger.info("Inside createTutorial method of TutorialController.");

        try {
            Tutorial tutorialToBeSaved = tutorialService.saveNewTutorial(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
            return new ResponseEntity<>(tutorialToBeSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        logger.info("Inside getAllTutorials method of TutorialController.");

        return new ResponseEntity<>(tutorialService.findAllTutorials(), HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id) {
        logger.info("Inside getTutorialById of TutorialController");
        Optional<Tutorial> tutorial = tutorialService.findTutorialById(id);
        if (tutorial.isPresent()) {
            return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        logger.info("Inside findByPublished method of TutorialController");

        try {
            List<Tutorial> tutorials = tutorialService.findByPublished(true);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial){
        logger.info("Inside updateTutorial method of TutorialController");

        Optional<Tutorial> tutorialToBeUpdated = tutorialService.findTutorialById(id);
        if (tutorialToBeUpdated.isPresent()) {
            Tutorial tutorialUpdated = tutorialToBeUpdated.get();
            tutorialUpdated.setTitle(tutorial.getTitle());
            tutorialUpdated.setDescription(tutorial.getDescription());
            tutorialUpdated.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialService.saveNewTutorial(tutorialUpdated), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id){
        logger.info("Inside deleteTutorial method of TutorialController");

        try {
            tutorialService.deleteTutorialById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


