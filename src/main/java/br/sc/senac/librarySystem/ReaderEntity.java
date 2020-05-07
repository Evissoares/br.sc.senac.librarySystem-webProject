package br.sc.senac.librarySystem;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "readerEntity")
public class ReaderEntity implements Serializable {

	private static final long serialVersionUID = -1384495822813041468L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long readerId;
	
	private String entityReaderName;
	
	private Integer entityReaderAge;
		
	public ReaderEntity(String readerName, Integer readerAge) {
		this.entityReaderName = readerName;
		this.entityReaderAge = readerAge;
	}
	
	protected ReaderEntity() {
		
	}

	public String getEntityReaderName() {
		return entityReaderName;
	}

	public void setEntityReaderName(String entityReaderName) {
		if(entityReaderName != null) {
		this.entityReaderName = entityReaderName;
		}
	}

	public Integer getEntityReaderAge() {
		return entityReaderAge;
	}

	public void setEntityReaderAge(Integer entityReaderAge) {
		if(entityReaderAge != null) {
		this.entityReaderAge = entityReaderAge;
		}
	}
	
	public Long getReaderId() {
		return this.readerId;
	}
}