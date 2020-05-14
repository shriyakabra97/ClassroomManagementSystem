package com.spe.ClassroomManagementSystem.Utils;

import com.spe.ClassroomManagementSystem.Models.Request;
import com.spe.ClassroomManagementSystem.Models.RequestStatus;

import java.util.List;

public interface CheckFulfilledRequests {
//    List<Request> findByRequestStatus(RequestStatus requestStatus);

    void markFulfilled();
}
