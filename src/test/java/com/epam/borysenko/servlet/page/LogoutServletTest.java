package com.epam.borysenko.servlet.page;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.CURRENT_USER;
import static com.epam.borysenko.constants.PathConstants.PAGE_TEMPLATE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutServletTest {

    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private LogoutServlet logoutServlet;

    @Before
    public void setUp() throws Exception {
        when(req.getSession()).thenReturn(session);
    }


    @Test
    public void testDoGetMethodVerifyThatSessionWillCallInvalidateMethod() throws ServletException, IOException {
        logoutServlet.doGet(req,resp);
        verify(session).removeAttribute(CURRENT_USER);
    }
}