package com.github.javabaz.darvazeh.feature.attendee;

import com.github.javabaz.darvazeh.common.base.BaseRepository;
import com.github.javabaz.darvazeh.feature.event.Event;
import com.github.javabaz.darvazeh.feature.user.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendeeRepository extends BaseRepository<Attendee, Long> {

    List<Attendee> findAllByUser(UserEntity user);
    List<Attendee> findAllByEvent(Event event);

    Attendee findByUserAndEvent(UserEntity user, Event event);

    List<Attendee> findAllByEventAndAttendanceDateTimeAfter(Event event, LocalDateTime dateTime);
    List<Attendee> findAllByUserAndAttendanceDateTimeBefore(UserEntity user, LocalDateTime dateTime);

}
