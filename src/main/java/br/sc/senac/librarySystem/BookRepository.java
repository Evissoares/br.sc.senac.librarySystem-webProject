package br.sc.senac.librarySystem;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
	
}
