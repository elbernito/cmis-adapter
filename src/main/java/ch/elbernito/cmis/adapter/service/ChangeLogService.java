package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.ChangeLogDto;

import java.util.List;

/**
 * Service interface for CMIS change log operations.
 */
public interface ChangeLogService {

    /**
     * Gets all change log entries.
     * @return list of change log entries
     */
    List<ChangeLogDto> getChangeLog();

    /**
     * Gets all content change log entries (specialized view).
     * @return list of content change log entries
     */
    List<ChangeLogDto> getContentChanges();
}
