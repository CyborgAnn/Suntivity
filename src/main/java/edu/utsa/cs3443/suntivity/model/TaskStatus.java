package edu.utsa.cs3443.suntivity.model;

/**
 * Represents the current state of a task.
 */
public enum TaskStatus {

    /**
     * Task created by parent and waiting for child completion.
     */
    INCOMPLETE,


    /**
     * Child marked task as finished and waiting for parent review.
     */
    PENDING,


    /**
     * Parent approved the completed task.
     */
    APPROVED,


    /**
     * Parent denied the completed task.
     */
    DENIED

}