package com.documents.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DocMetaData {
	@Id
	private Long id;

	String file_Name;
	Integer file_Size;

	public DocMetaData() {
		super();
	}

	public  DocMetaData(String fileName, Integer fileSize) {
		super();
		this.file_Name = fileName;
		this.file_Size = fileSize;
	}

	public String getFileName() {
		return file_Name;
	}

	public void setFileName(String fileName) {
		this.file_Name = fileName;
	}

	public Integer getFileSize() {
		return file_Size;
	}

	public void setFileSize(Integer fileSize) {
		this.file_Size = fileSize;
	}

}
