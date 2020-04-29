package br.sc.senac.librarySystem;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity(name = "History")
public class BorrowHistoryEntity extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 6518750173406685691L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long BorrowHistoryId;
	
	private Long readerEntityId;
	
	private String readerEntityName;
	
	private Long codeBookEntity;
	
	private String titleBookEntity;
	
	@CreatedDate@Column(columnDefinition = "TIMESTAMP", nullable = false)
	private Date dateAndTimeOfBorrow;
	
	protected BorrowHistoryEntity() {
		
	}
	
	public BorrowHistoryEntity(Long readerId, String readerName, Long codeBook, String titleBook, Date value) {
		this.readerEntityId = readerId;
		this.readerEntityName = readerName;
		this.codeBookEntity = codeBook;
		this.titleBookEntity = titleBook;
		this.dateAndTimeOfBorrow = value;
	}

	public Long getBorrowHistoryId() {
		return BorrowHistoryId;
	}

	public Long getReaderEntityId() {
		return readerEntityId;
	}

	public String getReaderEntityName() {
		return readerEntityName;
	}

	public Long getCodeBookEntity() {
		return codeBookEntity;
	}

	public String getTitleBookEntity() {
		return titleBookEntity;
	}

	public Date getDateAndTimeOfBorrow() {
		return dateAndTimeOfBorrow;
	}
}
