package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final List<ModuleCode> modules = new ArrayList<>();
    private final List<ModuleTiming> moduleTimings = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<ModuleCode> modules,
                   List<ModuleTiming> moduleTimings) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.modules.addAll(modules);
        this.moduleTimings.addAll(moduleTimings);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns list of modules student is taking
     */
    public List<ModuleCode> getModules() {
        return modules;
    }

    /**
     * Returns list of module timings for the student
     */
    public List<ModuleTiming> getModuleTimings() {
        return moduleTimings;
    }

    /**
     * Checks if module `m` is held within the student
     * @param m Module to check
     * @return true if module is taken by student
     */
    public boolean hasModule(ModuleCode m) {
        return modules.contains(m);
    }

    /**
     * Returns true if the student has the specified module timing.
     *
     * @param t The module timing to check.
     * @return True if the student has the specified module timing, false otherwise.
     */
    public boolean hasModuleTiming(ModuleTiming t) {
        return moduleTimings.contains(t);
    }

    /**
     * Add module to student
     * @param m ModuleCode to add
     * @return true if added
     */
    public boolean addModule(ModuleCode m) {
        return modules.add(m);
    }

    /**
     * Remove module from student
     * @param m ModuleCode to remove
     * @return true if removed
     */
    public boolean deleteModule(ModuleCode m) {
        // Cascade delete related module timings
        moduleTimings.removeIf(moduleTiming -> moduleTiming.getModuleCode().equals(m));
        return modules.remove(m);
    }

    /**
     * Checks if module timing `t` clashes with the students current module timings
     * @param t ModuleTiming to check
     * @return true if module timing clashes
     */
    public boolean doesModuleTimingClash(ModuleTiming t) {
        for (ModuleTiming currentModuleTiming : moduleTimings) {
            if (t.doesModuleTimingClash(currentModuleTiming)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Add module timing to student
     * @param t ModuleTiming to add
     * @return true if added
     */
    public boolean addModuleTiming(ModuleTiming t) {
        return moduleTimings.add(t);
    }

    /**
     * Remove module timing from student
     * @param t ModuleTiming to remove
     * @return true if removed
     */
    public boolean deleteModuleTiming(ModuleTiming t) {
        return moduleTimings.remove(t);
    }


    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return name.equals(otherStudent.name)
                && phone.equals(otherStudent.phone)
                && email.equals(otherStudent.email)
                && address.equals(otherStudent.address)
                && tags.equals(otherStudent.tags)
                && modules.equals(otherStudent.modules)
                && moduleTimings.equals(otherStudent.moduleTimings);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, modules, moduleTimings);
    }

    /**
     * Returns a shallow copy of a student instance.
     * @return a shallow copy of a student instance
     */
    public Student copy() {
        return new Student(
                this.name,
                this.phone,
                this.email,
                this.address,
                this.tags,
                this.modules,
                this.moduleTimings
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("modules", modules)
                .add("moduleTimings", moduleTimings)
                .toString();
    }

}
