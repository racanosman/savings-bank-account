package com.racan.api.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.racan.api.savings.dto.DepositWithdrawDTO;
import com.racan.api.savings.dto.enums.MessageEnums;
import com.racan.api.savings.dto.enums.SavingsAccountMessageEnum;
import com.racan.api.savings.models.User;
import com.racan.api.savings.services.SavingsService;
import com.racan.api.savings.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SavingsController.class)
public class SavingsControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavingsService savingsService;

    @MockBean
    private UserService userService;

    User user;

    @Before
    public void setUp() {
        String userName = "testUser";
        String password = "testPassword";
        user = new User(userName, password);
    }

    @Test
    public void givenUser_whenWithdrawMoney_thenStatus200() throws Exception {
        DepositWithdrawDTO depositWithdrawDTO = new DepositWithdrawDTO(10.0, "withdraw");
        MessageEnums successMessage = SavingsAccountMessageEnum.SUCCESSFUL_WITHDRAWAL;
        when(userService.getUserNameAndPassWord(user.getName(), user.getPassword())).thenReturn(Optional.of(user));
        when(savingsService.withdrawFromSavingsAccount(user, 2.0)).thenReturn(successMessage);
        mockMvc.perform(post("/api/v1/savingsApi?")
                .param("userName", user.getName())
                .param("password", user.getPassword())
                .contentType("application/json")
                .content(asJson(depositWithdrawDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the UserService and SavingsService were called with the correct parameters
        verify(userService, times(1)).getUserNameAndPassWord(user.getName(), user.getPassword());
        verify(savingsService, times(1)).withdrawFromSavingsAccount(user, 10.0);
    }

    @Test
    public void givenUser_whenDepositMoney_thenStatus200() throws Exception {
        DepositWithdrawDTO depositWithdrawDTO = new DepositWithdrawDTO(20, "deposit");
        MessageEnums successMessage = SavingsAccountMessageEnum.SUCCESSFUL_DEPOSIT;
        when(userService.getUserNameAndPassWord(user.getName(), user.getPassword())).thenReturn(Optional.of(user));
        when(savingsService.depositToSavingAccount(user, 20)).thenReturn(successMessage);
        mockMvc.perform(post("/api/v1/savingsApi?")
                        .param("userName", user.getName())
                        .param("password", user.getPassword())
                        .contentType("application/json")
                        .content(asJson(depositWithdrawDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the UserService and SavingsService were called with the correct parameters
        verify(userService, times(1)).getUserNameAndPassWord(user.getName(), user.getPassword());
        verify(savingsService, times(1)).depositToSavingAccount(user, 20);
    }

    @Test
    public void givenNewUser_whenCreateNewSavingsAccount_thenStatus200() throws Exception {
        when(userService.getUserNameAndPassWord(user.getName(), user.getPassword())).thenReturn(Optional.of(user));
        // Mock the SavingsService to return a success message
        MessageEnums successMessage = SavingsAccountMessageEnum.OPEN;
        when(savingsService.openSavingsAccount(user)).thenReturn(successMessage);
        // Perform the PUT request to the endpoint
        mockMvc.perform(put("/api/v1/savingsApi?")
                        .param("userName", user.getName())
                        .param("password", user.getPassword()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(successMessage.getMessage()))
                .andReturn();
        // Verify that the UserService and SavingsService were called with the correct parameters
        verify(userService, times(1)).getUserNameAndPassWord(user.getName(), user.getPassword());
        verify(savingsService, times(1)).openSavingsAccount(user);
    }

    @Test
    public void givenUser_whenGetAmount_thenStatus200() throws Exception {
        when(userService.getUserNameAndPassWord(user.getName(), user.getPassword())).thenReturn(Optional.of(user));
        when(savingsService.getSavingAccountBalanceByUser(user)).thenReturn(10.0);
        MvcResult result = mockMvc.perform(get("/api/v1/savingsApi?")
                        .param("userName", user.getName())
                        .param("password", user.getPassword())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(userService, times((1))).getUserNameAndPassWord(user.getName(), user.getPassword());
        verify(savingsService, times(1)).getSavingAccountBalanceByUser(user);
        String response = result.getResponse().getContentAsString();
        assertEquals(10.0, Double.parseDouble(response), 0.5);
    }

    /**
     * Convert an object into a JSON string.
     *
     * @param obj The object
     * @return The json string
     */
    private String asJson(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
