package br.sc.senac.librarysystem;

public class ReaderDTO {

	public static final ReaderDTO NULL_VALUE = new ReaderDTO(Long.valueOf(0), "", 0);
	
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
