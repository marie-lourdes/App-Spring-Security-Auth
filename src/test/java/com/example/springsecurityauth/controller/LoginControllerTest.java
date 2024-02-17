package com.example.springsecurityauth.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // simule les requete avec les classes necessaire pour les requete http des
						// route du LoginController
public class LoginControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		mvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void userLoginTest() throws Exception {
		mvc.perform(formLogin("/login").user("dbuser").password("user")).andExpect(authenticated());
	}

	@Test
	public void userLoginFailed() throws Exception {
		mvc.perform(formLogin("/login").user("user").password("wrongpassword")).andExpect(unauthenticated());
	}

	@Test
	@WithMockUser // mock un utilisateur authentifié et passe oute l etape d authenfication de
					// spring security pour executer les tests
	public void shouldReturnUserPage() throws Exception {
		mvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
	}
}
