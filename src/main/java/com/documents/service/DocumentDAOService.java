package com.documents.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import com.documents.model.Document;

@Component
public class DocumentDAOService {
	private static List<Document> docs = new ArrayList();

	/*
	 * This static list is used for the storage
	 */

	static {
		docs.add(new Document("A000010000001AAAAAA", "hello word"));
		docs.add(new Document("A000010000001AAAAAB", "Hi World"));
		docs.add(new Document("A000010000001AAAAAC", "Java 8"));
		docs.add(new Document("A000010000001AAAAAD", "React"));
		docs.add(new Document("A000010000001AAAAAE", "Spring boot"));
		docs.add(new Document("A000010000001AAAAAK", "Angular"));
	}

	public List<Document> findAll() {
		return docs;
	}

	public String save(String content) {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder docId = new StringBuilder(20);
		/*
		 * Generate Random Generated docID with Length=20
		 */
		for (int i = 0; i < 20; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());

			docId.append(AlphaNumericString.charAt(index));
		}
		/*
		 * 
		 * Check the Random Generated ID with id of documents in the storage. If this ID
		 * is not found then insert this document into storage, otherwise replace the
		 * content.
		 * 
		 * ID - Genarated value, content - Given content
		 * 
		 */

		Boolean fDoc = false;

		for (Document dc : docs) {
			if (dc.getDocID().equals(docId.toString())) {
				fDoc = true;
				// if docId found in storage replace the content
				dc.setContent(content);
			}
		}

		if (!fDoc) {
			Document doc = new Document(docId.toString().toUpperCase(), content);
			docs.add(doc);
		}

		return docId.toString();
	}

	public Document findOne(String docId) {

		for (Document doc : docs) {
			if (doc.getDocID().equals(docId)) {
				return doc;
			}

		}
		return null;
	}

	public Document deleteDocument(String docId) {
		Iterator<Document> iterator = docs.iterator();

		while (iterator.hasNext()) {
			Document doc = iterator.next();
			if (doc.getDocID().equals(docId)) {
				iterator.remove();
				return doc;
			}

		}
		return null;
	}

}
