package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.beneficiary.Beneficiary;

/**
 * Adds a beneficiary to the address book.
 */
public class AddBeneficiaryCommand extends Command {

    public static final String COMMAND_WORD = "addBeneficiary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a beneficiary to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Orphanage "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "Orphanage@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_SUCCESS = "New beneficiary added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This beneficiary already exists in the address book";

    private final Beneficiary toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Beneficiary}
     */
    public AddBeneficiaryCommand(Beneficiary beneficiary) {
        requireNonNull(beneficiary);
        toAdd = beneficiary;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasBeneficiary(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addBeneficiary(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddBeneficiaryCommand) other).toAdd));
    }
}