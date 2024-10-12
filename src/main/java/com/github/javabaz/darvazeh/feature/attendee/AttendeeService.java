package com.github.javabaz.darvazeh.feature.attendee;

import com.github.javabaz.darvazeh.common.base.BaseService;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.user.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendeeService extends BaseService<Attendee,Long> {


    Attendee registerAttendance(Attendee attendee);

    List<Attendee> getAllByUser(UserEntity user);
    List<Attendee> getAllByEvent(Event event);

    Attendee getByUserAndEvent(UserEntity user, Event event);

    List<Attendee> getAllByEventAndAttendanceDateTimeAfter(Event event, LocalDateTime dateTime);
    List<Attendee> getAllByUserAndAttendanceDateTimeBefore(UserEntity user, LocalDateTime dateTime);

}
