package suntivity_model;

/**
 * Represents the current state of a task within the Suntivity application.
 *
 * <p>A task progresses through different states as it is completed by a child
 * account and reviewed by a parent account.</p>
 */
public enum TaskStatus {

    /**
     * Indicates that the task has been created but has not yet been completed.
     */
    INCOMPLETE,

    /**
     * Indicates that the child has completed the task and it is waiting for
     * parent approval.
     */
    PENDING,

    /**
     * Indicates that the task has been approved and completed successfully.
     */
    COMPLETE
}
