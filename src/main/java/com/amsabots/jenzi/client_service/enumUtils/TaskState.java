package com.amsabots.jenzi.client_service.enumUtils;

public class TaskState {
    public enum TaskStateEnum{
        COMPLETE, ONGOING, CANCELLED, PENDING
    }
    public enum PendingTaskStates {
        CANCEL_PENDING, COMPLETE_PENDING, ONGOING
    }
}
