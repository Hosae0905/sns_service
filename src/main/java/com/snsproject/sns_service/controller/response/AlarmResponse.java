package com.snsproject.sns_service.controller.response;

import com.snsproject.sns_service.model.Alarm;
import com.snsproject.sns_service.model.AlarmArgs;
import com.snsproject.sns_service.model.AlarmType;
import com.snsproject.sns_service.model.User;
import com.snsproject.sns_service.model.entity.AlarmEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class AlarmResponse {
    private Integer id;
    private AlarmType alarmType;
    private AlarmArgs alarmArgs;
    private String text;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public static AlarmResponse fromAlarm(Alarm alarm) {
        return new AlarmResponse(
                alarm.getId(),
                alarm.getAlarmType(),
                alarm.getAlarmArgs(),
                alarm.getAlarmType().getAlarmText(),
                alarm.getRegisteredAt(),
                alarm.getUpdatedAt(),
                alarm.getDeletedAt()
        );
    }

}
