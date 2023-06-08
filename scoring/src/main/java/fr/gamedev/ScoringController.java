package fr.gamedev;

import fr.gamedev.data.ScoringRule;
import fr.gamedev.data.UserAnswer;
import fr.gamedev.dto.PendingUserAnswerDTO;
import fr.gamedev.dto.ScoringRuleDto;
import fr.gamedev.dto.UserAnswerDTO;
import fr.gamedev.service.ScoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Swan
 *
 */
@RestController

public class ScoringController {

    /**
     * . scoringService
     */
    private final ScoringService scoringService;

    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping("/user-answer")
    public ResponseEntity submitResponse(@RequestBody UserAnswerDTO userAnswerDTO) {
        UserAnswer userAnswer = scoringService.submit_response(userAnswerDTO);
        return ResponseEntity.accepted().body(userAnswer);
    }

    @PostMapping("/scoring-rule")
    public ResponseEntity upsertScoringRule(@RequestBody ScoringRuleDto scoringRuleDto) {
        ScoringRule scoringRule = scoringService.upsetScoringRule(scoringRuleDto);
        return ResponseEntity.accepted().body(scoringRule);
    }

    @PostMapping("/pending-user-answer")
    public ResponseEntity pendingUserAnwser(@RequestBody PendingUserAnswerDTO pendingUserAnswerDTO) {
        return ResponseEntity.ok()
                .body(scoringService.pendingUserAnswer(pendingUserAnswerDTO));
    }

    @GetMapping("/pending-user-answer")
    public ResponseEntity hasPendingUserAnswer(@RequestParam final long userId) {
        return scoringService.hasPendingUserAnswer(userId)
                .map(aLong -> ResponseEntity.ok().body(aLong))
                .orElse(ResponseEntity.noContent().build());
    }

}
