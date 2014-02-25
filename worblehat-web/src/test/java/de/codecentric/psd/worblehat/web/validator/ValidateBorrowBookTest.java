package de.codecentric.psd.worblehat.web.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import de.codecentric.psd.worblehat.web.command.BookBorrowFormData;

public class ValidateBorrowBookTest {

	private static final String INVALID_ISBN = "978-3492285100-22-123";
	private static final String VALID_ISBN = "978-3-455-50236-7";
	private static final String VALID_EMAIL = "valid.user@worblehat.de";

	private ValidateBorrowBook validateBorrowBook;

	private BookBorrowFormData cmd = new BookBorrowFormData(VALID_ISBN,
			VALID_EMAIL);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.validateBorrowBook = new ValidateBorrowBook();
	}

	@Test
	public void shouldFailForEmptyISBN() {
		cmd.setIsbn("         ");
		validateForOneError();
	}

	@Test
	public void shouldFailForNullISBN() {
		cmd.setIsbn(null);
		validateForOneError();
	}

	@Test
	public void shouldValidateISBN10() {
		String isbn10 = "90-70002-34-5";
		cmd.setIsbn(isbn10);
		Errors errors = new BindException(cmd, "bookBorrowCmd");
		validateBorrowBook.validate(cmd, errors);
		Object value = errors.getFieldValue("isbn");
		assertThat(errors.getErrorCount(), is(0));
		assertEquals(isbn10, value);
	}

	@Test
	public void shouldValidateISBN13() {
		String isbn13 = "978-3-455-50236-7";
		cmd.setIsbn(isbn13);
		Errors errors = new BindException(cmd, "bookBorrowCmd");
		validateBorrowBook.validate(cmd, errors);
		Object value = errors.getFieldValue("isbn");
		assertEquals(0, errors.getErrorCount());
		assertEquals(isbn13, value);
	}

	@Test
	public void shouldFailForInvalidISBN() {
		String isbn13 = INVALID_ISBN;
		cmd.setIsbn(isbn13);
		validateForOneError();
	}

	@Test
	public void shouldFailForEmptyEmail() {
		cmd.setEmail("         ");
		validateForOneError();
	}

	@Test
	public void shouldFailForInvalidEmail() {
		cmd.setEmail("Hello World");
		validateForOneError();
	}

	private void validateForOneError() {
		Errors errors = new BindException(cmd, "cmdBookdData");
		validateBorrowBook.validate(cmd, errors);
		assertThat(errors.getErrorCount(), is(1));
	}

}
