package com.documents.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

public class Document {
@Size(min=20,max=20)
@Id
@GeneratedValue
String docID;
String content;

public String getDocID() {
	return docID;
}


public Document() {
	super();
}


public Document(String docID) {
	super();
	this.docID = docID;
}

public Document(String docID, String content) {
	super();
	this.docID = docID;
	this.content = content;
}


public void setDocID(String docID) {
	this.docID = docID;
}


public String getContent() {
	return content;
}


public void setContent(String content) {
	this.content = content;
}

}
