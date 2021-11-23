package fr.gamedev.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

  /**
   * @param questionId
   * @param answer
   * @param userId
   * @return String
   */
  @GetMapping("/response")
  public String answer(
      @RequestParam final long questionId,
      @RequestParam final Boolean answer,
      @RequestParam final long userId) {
    String response;

    if (answer == Boolean.TRUE) {
      // Ajouter des points

      response = "Bravo ! vous avez trouvé ! ";
    } else {
      // Ne pas ajouter de points

      response = "Oops ! Ca n'est pas correcte";
    }

    return response;
  }

}
