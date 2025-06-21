package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.RepositoryInfoDto;
import ch.elbernito.cmis.adapter.dto.RepositoryMetaDto;

import java.util.List;

/**
 * Service interface for repository operations.
 */
public interface RepositoryService {

    /**
     * Gets all repositories.
     * @return list of repositories
     */
    List<RepositoryMetaDto> getAllRepositories();

    /**
     * Gets a repository by ID.
     * @param id repository id
     * @return meta info
     */
    RepositoryMetaDto getRepository(String id);

    /**
     * Gets detailed repository info.
     * @param id repository id
     * @return info dto
     */
    RepositoryInfoDto getRepositoryInfo(String id);
}
