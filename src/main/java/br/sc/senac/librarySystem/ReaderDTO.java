package br.sc.senac.librarySystem;

public class ReaderDTO {

	private Long readerId;
	private String readerName;
	private Integer readerAge;
	
	ReaderDTO(Long readerId, String readerName, Integer readerAge) {
		this.readerId = readerId;
		this.readerName = readerName;
		this.readerAge = readerAge;
	}

	public Long getReaderId() {
		return readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public Integer getReaderAge() {
		return readerAge;
	}
}
