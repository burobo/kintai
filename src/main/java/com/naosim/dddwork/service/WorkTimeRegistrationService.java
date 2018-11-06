package com.naosim.dddwork.service;


import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.DailyAttendance;
import com.naosim.dddwork.domain.LaborRegulations;
import com.naosim.dddwork.domain.LaborRegulationsRepository;
import com.naosim.dddwork.domain.use_case.WorkTimeRegistrationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkTimeRegistrationService {
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    LaborRegulationsRepository laborRegulationsRepository;

    public void input(WorkTimeRegistrationApplication workTimeRegistrationApplication) {

        LaborRegulations laborRegulations = laborRegulationsRepository.get(workTimeRegistrationApplication);

        attendanceRepository.register(
                new DailyAttendance(
                        workTimeRegistrationApplication,
                        laborRegulations
                )
        );
    }
}