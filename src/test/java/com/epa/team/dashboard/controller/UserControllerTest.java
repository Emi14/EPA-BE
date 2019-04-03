package com.epa.team.dashboard.controller;

import com.epa.team.dashboard.resource.UserResource;
import com.epa.team.dashboard.service.UserService;
import com.epa.team.dashboard.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ionut Emanuel Mihailescu on 4/3/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController = new UserController();

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserResource> allUsers = TestUtils.getUsers();
        when(userService.getAllUserResources()).thenReturn(allUsers);

        mockMvc.perform(get("/api/user/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).getAllUserResources();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;

        when(userService.getUserResourceById(eq(userId))).thenReturn(TestUtils.getUserResourceById(userId));

        MvcResult result = mockMvc.perform(get("/api/user/getUser/"+ userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo("{\"id\":1,\"username\":\"User1\",\"password\":\"MASKED\",\"role\":\"USER\",\"daysOff\":0," +
                        "\"workFromHome\":false}");

        verify(userService, times(1)).getUserResourceById(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testDeleteUserById() throws Exception {
        Long userId = 1L;

        mockMvc.perform(delete("/api/user/delete/"+ userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUserById(userId);
        verifyNoMoreInteractions(userService);
    }

}
