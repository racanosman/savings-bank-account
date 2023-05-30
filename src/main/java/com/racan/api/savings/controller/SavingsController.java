package com.racan.api.savings.controller;

import com.racan.api.savings.dto.DepositWithdrawDTO;
import com.racan.api.savings.dto.enums.ControllerErrorMessageEnum;
import com.racan.api.savings.dto.enums.MessageEnums;
import com.racan.api.savings.dto.enums.UserMessage;
import com.racan.api.savings.models.User;
import com.racan.api.savings.services.SavingsService;
import com.racan.api.savings.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/savingsApi")
public class SavingsController {

    private final SavingsService savingsService;
    private final UserService userService;

    @Autowired
    public SavingsController(SavingsService savingsService, UserService userService) {
        this.savingsService = savingsService;
        this.userService = userService;
    }

    @GetMapping
    public double getAmountForUser(@RequestParam String userName, @RequestParam String password) {
        Optional<User> user = userService.getUserNameAndPassWord(userName, password);
        return user.map(savingsService::getSavingAccountBalanceByUser).orElse(0.0);
    }

    @PostMapping
    public MessageEnums widthdrawMoneyFromUserSavingAccount(@RequestParam String userName, @RequestParam String password, @RequestBody DepositWithdrawDTO dto) {
        Optional<User> user = userService.getUserNameAndPassWord(userName, password);
        if (StringUtils.isEmpty(dto.getType()) || StringUtils.isEmpty(dto.getAmount())) {
            return ControllerErrorMessageEnum.NOT_A_VALID_REQUEST_BODY;
        }
        if (user.isPresent()) {
            if (dto.getType().equals("withdraw")) {
                return savingsService.withdrawFromSavingsAccount(user.get(), dto.getAmount());
            } else if (dto.getType().equals("deposit")) {
                return savingsService.depositToSavingAccount(user.get(), dto.getAmount());
            }
        }
        return UserMessage.USER_DOES_NOT_EXIST;
    }

    @PutMapping
    public MessageEnums createNewSavingsAccount(@RequestParam String userName, @RequestParam String password) {
        Optional<User> user = userService.getUserNameAndPassWord(userName, password);
        return user.map(savingsService::openSavingsAccount).orElse(UserMessage.USER_DOES_NOT_EXIST);
    }

}
