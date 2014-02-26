package de.codecentric.psd.worblehat.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repo for Book
 * 
 * @author mahmut.can
 * 
 */
@Repository
@Transactional
public class BookRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Constructor for test.
	 * 
	 * @param em
	 */
	public BookRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * 
	 */
	public BookRepository() {
		// empty
	}

	/**
	 * Select a book by ISBN.
	 * 
	 * @param isbn
	 *            a valid ISBN_10 of a book entity
	 * @return null if no book found
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByISBN(String isbn) {
		Query query = em.createQuery("from Book where isbn = ?");
		query = query.setParameter(1, isbn);
		return query.getResultList();
	}

	public Book findBookByUserAndISBN(String email, String isbn) {
		Query query = em.createQuery("from Book where isbn =? and email= ?")
				.setParameter(1, isbn).setParameter(2, email);
		return (Book) query.getSingleResult();
	}

	/**
	 * Persist a book entity to DB.
	 * 
	 * @param book
	 *            the book to persist
	 * @throws IsbnAlreadyUsedException
	 */
	public void store(Book book) throws IsbnAlreadyUsedException {
		List<Book> books = findBooksByISBN(book.getIsbn());
		for (Book book2 : books) {
			if (!book2.equals(book)) {
				if (!areSameBook(book, book2))
					throw new IsbnAlreadyUsedException();
			}
		}
		em.persist(book);
	}

	public boolean areSameBook(Book book1, Book book2) {
		if (book1.getTitle().equals(book2.getTitle())
				&& book1.getAuthor().equals(book2.getAuthor())
				&& book1.getIsbn().equals(book2.getIsbn())
				&& book1.getYear() == book2.getYear()
				&& book1.getEdition().equals(book2.getEdition())
				&& descriptionIsEqualOrEmptyAndNull(book1.getDescription(),
						book2.getDescription())) {
			return true;
		}
		return false;
	}

	private boolean descriptionIsEqualOrEmptyAndNull(String description1,
			String description2) {
		if ((description1 == null && description2.equals(""))
				|| description1.equals("") && description2 == null
				|| description1 == null && description2 == null
				|| description1.equals(description2)) {
			return true;
		} else
			return false;

	}

	/**
	 * @param isbn
	 *            the ISBN to search for
	 * @return any book with the given ISBN that is available for borrowing
	 * 
	 * @throws NoBookBorrowableException
	 *             if no book is available
	 */
	public Book findBorrowableBook(String isbn)
			throws NoBookBorrowableException {
		try {
			return (Book) em.createNamedQuery("findBorrowableBookByISBN")
					.setParameter("isbn", isbn).setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e) {
			throw new NoBookBorrowableException(isbn);
		}
	}

	/**
	 * @param email
	 *            the name of the borrowing user
	 * @return all books the given user is currently borrowing
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findAllBorrowBooksByBorrower(String email) {
		Query query = em.createNamedQuery("findAllBorrowedBooksByEmail");

		return query.setParameter("email", email).getResultList();
	}

	/**
	 * @return all books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {
		Query query = em.createNamedQuery("findAllBooks");
		return query.getResultList();
	}

}
