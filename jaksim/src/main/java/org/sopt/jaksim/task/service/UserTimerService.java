package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.global.common.DateUtil;
import org.sopt.jaksim.global.exception.InvalidValueException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.task.domain.UserTimer;
import org.sopt.jaksim.task.dto.StopTimerRequest;
import org.sopt.jaksim.task.dto.TotalTimeTodayResponse;
import org.sopt.jaksim.task.repository.UserTimerRepository;
import org.sopt.jaksim.user.domain.User;
import org.sopt.jaksim.user.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTimerService {
    private final UserTimerRepository userTimerRepository;
    private final UserFacade userFacade;

    public TotalTimeTodayResponse getTotalTimeToday(String targetDate) {
        if (!isToday(targetDate)) {
            throw new InvalidValueException(ErrorMessage.IS_NOT_TODAY);
        }
//        User user = userFacade.getUserByPrincipal();
        UserTimer userTimer = getUserTimerByUserIdAndTargetDate(1L, targetDate);
        if (userTimer == null) {
            userTimer = userTimerRepository.save(UserTimer.create(1L, targetDate));
        }
        return TotalTimeTodayResponse.of(targetDate, userTimer.getTargetTime());
    }

    public boolean isToday(String targetDate) {
        if (!LocalDate.now().equals(DateUtil.stringToDate(targetDate))) {
            return false;
        }
        return true;
    }

    public UserTimer getUserTimerByUserIdAndTargetDate(Long userId, String targetDate) {
        return userTimerRepository.findByUserIdAndTargetDate(userId, targetDate);
    }

    public void calculateUserTimerOnStop(StopTimerRequest stopTimerRequest) {
        User user = userFacade.getUserByPrincipal();
        UserTimer userTimer = userTimerRepository.findByUserId(user.getId());
        userTimer.setTargetTime(userTimer.getTargetTime() + stopTimerRequest.elapsedTime());
    }

}
