package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.AttendanceRecord
import com.naosim.dddwork.domain.date.Day
import com.naosim.dddwork.domain.date.Month
import com.naosim.dddwork.domain.date.WorkingDate
import com.naosim.dddwork.domain.date.Year
import com.naosim.dddwork.domain.time.EntryTime
import com.naosim.dddwork.domain.time.Hour
import com.naosim.dddwork.domain.time.Minute
import spock.lang.Specification

class AttendanceRecordRepositoryCSVSpec extends Specification {
    def "AttendanceRecordRepositoryCSV-read-test"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()
        attendanceRecordRepositoryCSV.delete()

        when:
        def attendanceRecords = attendanceRecordRepositoryCSV.load().getAttendanceRecords()

        then:
        attendanceRecords.size() == 0
    }

    def "AttendanceRecordRepository-save"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()

        when:
        attendanceRecordRepositoryCSV.delete()
        def attendanceRecords = attendanceRecordRepositoryCSV.load()
        def year = new Year(2019)
        def month = new Month(4)
        def day = new Day(1)
        def workingDate = new WorkingDate(year, month, day)
        def startHour = new Hour(8)
        def startMin = new Minute(50)
        def startTime = new EntryTime(startHour, startMin)
        def endHour = new Hour(18)
        def endMin = new Minute(20)
        def endTime = new EntryTime(endHour, endMin)
        def attendanceRecord = new AttendanceRecord(workingDate, startTime, endTime)
        attendanceRecords = attendanceRecords.insert(attendanceRecord)
        attendanceRecordRepositoryCSV.save(attendanceRecords)

        then:
        attendanceRecords.getAttendanceRecords().size() == 1
    }

    def "AttendanceRecordRepository-load"() {
        setup:
        def attendanceRecordRepositoryCSV = new AttendanceRecordRepositoryCSV()

        when:
        def attendanceRecords = attendanceRecordRepositoryCSV.load().getAttendanceRecords()
        def attendanceRecordResult2 = attendanceRecords.get(0)

        then:
        attendanceRecordResult2.toString() == "20190401 08:50-18:20"
    }
}
