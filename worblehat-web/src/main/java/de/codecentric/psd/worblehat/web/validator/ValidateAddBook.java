package de.codecentric.psd.worblehat.web.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.ISBNValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.codecentric.psd.worblehat.web.command.BookDataFormData;

/**
 * Validation for adding a book
 * 
 * @author mahmut.can
 * 
 */
public class ValidateAddBook implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BookDataFormData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (!supports(target.getClass()))
			throw new IllegalArgumentException("Validation of classs '"
					+ target.getClass() + "' is not supported");

		BookDataFormData cmd = (BookDataFormData) target;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "empty");

		checkThatYearIsFilledAndValid(errors, cmd);
		checkThatIsbnIsFilledAndValid(errors, cmd);
		checkThatEditionisFilledAndValid(errors, cmd);
		checkThatTitleisFilledAndValid(errors, cmd);
		checkThatAuthorisFilledAndValid(errors, cmd);
	}

	private void checkThatTitleisFilledAndValid(Errors errors,
			BookDataFormData cmd) {
		ValidationUtils.rejectIfEmpty(errors, "title", "empty");
		if (!errors.hasFieldErrors("title")) {
			cmd.getTitle().trim();
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "empty");
		}
	}

	private void checkThatAuthorisFilledAndValid(Errors errors,
			BookDataFormData cmd) {
		ValidationUtils.rejectIfEmpty(errors, "author", "empty");
		if (!errors.hasFieldErrors("author")) {
			cmd.getAuthor().trim();
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author",
					"author");
		}
	}

	private void checkThatEditionisFilledAndValid(Errors errors,
			BookDataFormData cmd) {
		ValidationUtils.rejectIfEmpty(errors, "edition", "empty");
		if (!errors.hasFieldErrors("edition")) {
			String edition = cmd.getEdition().trim();
			if (!StringUtils.isNumeric(edition)) {
				errors.rejectValue("edition", "notvalid");
			}
		}
	}

	private void checkThatIsbnIsFilledAndValid(Errors errors,
			BookDataFormData cmd) {
		ValidationUtils.rejectIfEmpty(errors, "isbn", "empty");
		if (!errors.hasFieldErrors("isbn")) {
			String isbn = cmd.getIsbn().trim();
			ISBNValidator isbnValidator = new ISBNValidator();
			if (!isbnValidator.isValid(isbn)) {
				errors.rejectValue("isbn", "notvalid");
			}
		}
	}

	private void checkThatYearIsFilledAndValid(Errors errors,
			BookDataFormData cmd) {
		ValidationUtils.rejectIfEmpty(errors, "year", "empty");
		if (!errors.hasFieldErrors("year")) {
			String year = cmd.getYear().trim();
			if (!StringUtils.isNumeric(year)) {
				errors.rejectValue("year", "notvalid");
			} else if (StringUtils.length(year) != 4) {
				errors.rejectValue("year", "invalid.length");
			}
		}
	}

}
