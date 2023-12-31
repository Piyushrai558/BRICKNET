package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckIn;

import java.util.Date;
import java.util.List;


public interface CheckInService {
    List<CheckIn> getAllCheckIns();
    CheckIn getCheckInById(Long id);
    CheckIn createCheckIn(CheckIn checkIn);
    CheckIn updateCheckIn(Long id, CheckIn checkIn);
    boolean deleteCheckIn(Long id);
    List<String> findFirstCheckInTimes(Long emp_id, List<String> dates);

}

