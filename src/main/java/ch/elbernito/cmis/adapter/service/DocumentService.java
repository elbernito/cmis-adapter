package ch.elbernito.cmis.adapter.service;

import ch.elbernito.cmis.adapter.dto.DocumentDto;
import ch.elbernito.cmis.adapter.dto.VersionDto;

import java.util.List;

/**
 * Service interface for document-related CMIS operations.
 * Defines all available document operations for the API layer.
 */
public interface DocumentService {

    /**
     * Creates a new document.
     * @param docDto the document to create
     * @return the created document
     */
    DocumentDto createDocument(DocumentDto docDto);

    /**
     * Returns a document by ID.
     * @param documentId the document ID
     * @return the document
     */
    DocumentDto getDocument(String documentId);

    /**
     * Updates an existing document.
     * @param documentId the ID of the document to update
     * @param docDto the updated document data
     * @return the updated document
     */
    DocumentDto updateDocument(String documentId, DocumentDto docDto);

    /**
     * Deletes a document by ID.
     * @param documentId the ID of the document to delete
     */
    void deleteDocument(String documentId);

    /**
     * Checks in a document.
     * @param documentId the document ID
     * @return the checked-in document
     */
    DocumentDto checkinDocument(String documentId);

    /**
     * Checks out a document.
     * @param documentId the document ID
     * @return the checked-out document
     */
    DocumentDto checkoutDocument(String documentId);

    /**
     * Returns all versions for a document.
     * @param documentId the document ID
     * @return list of versions
     */
    List<VersionDto> getDocumentVersions(String documentId);

    /**
     * Returns the binary content of a document.
     * @param documentId the document ID
     * @return binary content
     */
    byte[] getDocumentContent(String documentId);

    /**
     * Updates the content of a document.
     * @param documentId the document ID
     * @param content the new content
     * @param mimeType the MIME type
     */
    void updateDocumentContent(String documentId, byte[] content, String mimeType);
}
