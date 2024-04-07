package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleMap;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    ModuleMap getModuleMap();

    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    boolean hasModule(ModuleCode code);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);


    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be
     * the same as another existing student in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Checks if student `s` has moduleCode `m`
     * @param s Student
     * @param m Module
     * @return Whether student has that module or not
     */
    boolean doesStudentHaveModule(Student s, ModuleCode m);

    /**
     * Adds module to the specified student
     * @param m Module to add
     * @param s Student to add module to
     */
    void addModuleToStudent(ModuleCode m, Student s);

    /**
     * Adds module to the specified student
     * @param m Module to add
     * @param s Student to add module to
     */
    void deleteModuleFromStudent(ModuleCode m, Student s);

    /**
     * Checks if student `s` has clashes with moduleTiming `t`
     * @param s Student
     * @param t ModuleTiming
     * @return Whether student has clash with `t`
     */
    boolean doesStudentModuleTimingClash(Student s, ModuleTiming t);

    /**
     * Checks if a given student has a specific module timing.
     *
     * @param s The student to check.
     * @param t The module timing to check.
     * @return True if the student has the module timing, false otherwise.
     */
    boolean doesStudentModuleTimingExist(Student s, ModuleTiming t);

    /**
     * Adds module timing to the specified student
     * @param t ModuleTiming to add
     * @param s Student to add module to
     */
    void addModuleTimingToStudent(ModuleTiming t, Student s);

    /**
     * Deletes the specified module timing from the given student.
     *
     * @param t The module timing to be deleted.
     * @param s The student from which the module timing should be deleted.
     */
    void deleteModuleTimingFromStudent(ModuleTiming t, Student s);
}
