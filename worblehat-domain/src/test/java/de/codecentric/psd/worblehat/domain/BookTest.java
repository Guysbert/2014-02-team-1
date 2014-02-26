package de.codecentric.psd.worblehat.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BookTest {

	@Test
	public void shouldAllowBorrowingAvailableBook()
			throws BookAlreadyBorrowedException {
		Book book = new Book("Title", "author", "2", "1234", 2002,
				"Test description 2");
		book.borrow("borrower@codecentric.local");

		assertNotNull(book.getCurrentBorrowing());
	}

	@Test(expected = BookAlreadyBorrowedException.class)
	public void shouldDenyBorrowingAlreadyBorrowedBook()
			throws BookAlreadyBorrowedException {
		Book book = new Book("Title", "author", "2", "1234", 2002,
				"Test Description 3");
		book.borrow("borrower@codecentric.local");
		book.borrow("borrower@codecentric.local");
	}

	@Test
	public void shouldStripDashesFromISBN() {
		Book aBook = new Book("Title", "author", "2", "12-34-45", 2002,
				"Test Description 3");
		assertEquals(aBook.getIsbn(), "123445");
	}
}
