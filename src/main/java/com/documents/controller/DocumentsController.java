package com.documents.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 

import com.documents.model.Document;
import com.documents.service.DocumentDAOService;

@RestController
public class DocumentsController {
	@Autowired
	private DocumentDAOService service;

	/*
	 * GET REQUEST
	 * 
	 */
	@GetMapping(path = "/storage/documents")
	public List<Document> retrieveAllDocuments() {
		return service.findAll();
	}

	/*
	 * 	  GET QUERY	 
	 */
	@GetMapping(path = "/storage/documents/{docId}")
	public String retrieveDocument(@PathVariable String docId)

	{
		Document doc = service.findOne(docId);
		if (service.findOne(docId) == null) {
			throw new DocNotFoundException("DocId" + docId);

		}
		String txtMsg = "Content length: " + doc.getContent().length() + System.lineSeparator() + doc.getContent();
		return txtMsg;
	}

	/*
	 * POST REQUEST
	 */
	@PostMapping("/storage/documents")
	public ResponseEntity<Object> createDocuments(@Valid @RequestBody String content)

	{
		String savedDoc = service.save(content);

		String msgTxt = "Content-Type: text/plain; charset=us-ascii Content-Length: " + savedDoc.length()
				+ System.lineSeparator() + savedDoc.toUpperCase();
		return new ResponseEntity<>(msgTxt, HttpStatus.CREATED);
	}

	/*
	 * PUT REQUEST
	 * 
	 */
	@PutMapping("/storage/documents/{docId}")
	public ResponseEntity<Object> replaceEmployee(@RequestBody String newContent, @PathVariable String docId) {

		Document doc = service.findOne(docId);

		if (doc == null) {
			throw new DocNotFoundException("DocId-" + docId);
		}
		doc.setContent(newContent);
		// service.save(doc);
		String msgTxt = "Content-Type: text/plain; charset=us-ascii Content-Length: " + newContent.length()
				+ System.lineSeparator() + docId.toUpperCase();

		return new ResponseEntity<>(msgTxt, HttpStatus.NO_CONTENT);
	}

	/*
	 * DELETE REQUEST
	 * 
	 */
	@DeleteMapping("/storage/documents/{docId}")
	public ResponseEntity<Object> deleteDocument(@PathVariable String docId) {
		Document doc = service.deleteDocument(docId);
		if (doc == null) {
			throw new DocNotFoundException("DocId-" + docId);
		}

		return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
	}

}
