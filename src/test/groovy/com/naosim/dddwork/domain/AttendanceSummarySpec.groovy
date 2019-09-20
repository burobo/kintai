package com.naosim.dddwork.domain


import io.vavr.collection.List
import spock.lang.Specification

class AttendanceSummarySpec extends Specification {
    def "AttendanceSummary-fired-case1"() {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019, 5, 1, 9, 1, 18, 0)
        def attendanceRecords = new AttendanceRecords().insert(attendanceRecord1)
        def attendanceSummaryCalculator = new AttendanceSummaryCalculator()

        when:
        def result = attendanceSummaryCalculator.calculate(attendanceRecords)

        then:
        result.isLeft() == true
    }

    def "AttendanceSummary-fired-case2"() {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019, 5, 1, 8, 59, 18, 0)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        def attendanceSummaryCalculator = new AttendanceSummaryCalculator()

        when:
        def result = attendanceSummaryCalculator.calculate(attendanceRecords)

        then:
        result.isLeft() == false
    }

    def "AttendanceSummary-total-working-hours1"() {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019, 5, 1, 9, 0, 24, 0)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        def attendanceSummaryCalculator = new AttendanceSummaryCalculator()

        when:
        def result = attendanceSummaryCalculator.calculate(attendanceRecords)

        then:
        result.right().get().getRegularWorkingDuration().getRegularWorkingDuration().toHours() == 8
        result.right().get().getOverTimeWorkingDuration().getOverTimeWorkingDuration().toHours() == 4
    }

    def "AttendanceSummary-total-working-hours2"() {
        setup:
        def attendanceRecord1 = FixtureAttendanceRecord.get(2019, 5, 1, 9, 0, 23, 30)
        List<AttendanceRecord> list = List.of(attendanceRecord1)
        def attendanceRecords = new AttendanceRecords(list)
        attendanceRecords.insert(attendanceRecord1)
        def attendanceSummaryCalculator = new AttendanceSummaryCalculator()

        when:
        def result = attendanceSummaryCalculator.calculate(attendanceRecords)

        then:
        result.right().get().getRegularWorkingDuration().getRegularWorkingDuration().toHours() == 8
        result.right().get().getRegularWorkingDuration().getRegularWorkingDuration().toMinutes() == 8 * 60

        result.right().get().getOverTimeWorkingDuration().getOverTimeWorkingDuration().toHours() == 3
        result.right().get().getOverTimeWorkingDuration().getOverTimeWorkingDuration().toMinutes() == 3 * 60 + 30
    }
}
