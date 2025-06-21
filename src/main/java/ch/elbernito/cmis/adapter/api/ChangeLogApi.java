package ch.elbernito.cmis.adapter.api;

import ch.elbernito.cmis.adapter.dto.ChangeLogDto;
import ch.elbernito.cmis.adapter.service.ChangeLogService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API layer for change log-related CMIS operations.
 * Handles Swagger/OpenAPI documentation, Prometheus/Actuator metrics, and logging.
 */
@Slf4j
@RestController
@RequestMapping("/api/cmis/1.2/changelog")
@AllArgsConstructor
@Tag(name = "ChangeLog", description = "CMIS change log API with metrics and detailed OpenAPI documentation.")
public class ChangeLogApi {

    private final ChangeLogService changeLogService;

    @Timed(value = "cmis.changelog.get.time", description = "Time spent fetching change log")
    @Counted(value = "cmis.changelog.get.count", description = "Count of change log fetches")
    @Operation(summary = "Get change log", description = "Fetches all CMIS change log entries.")
    @GetMapping
    public List<ChangeLogDto> getChangeLog() {
        log.info("API: Get change log");
        return changeLogService.getChangeLog();
    }

    @Timed(value = "cmis.changelog.contentchanges.time", description = "Time spent fetching content changes")
    @Counted(value = "cmis.changelog.contentchanges.count", description = "Count of content change log fetches")
    @Operation(summary = "Get content changes", description = "Fetches all CMIS content change log entries.")
    @GetMapping("/changes")
    public List<ChangeLogDto> getContentChanges() {
        log.info("API: Get content changes");
        return changeLogService.getContentChanges();
    }
}
